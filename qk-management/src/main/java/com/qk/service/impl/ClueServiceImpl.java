package com.qk.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.dto.ClueQueryDto;
import com.qk.entity.Business;
import com.qk.entity.Clue;
import com.qk.entity.ClueTrackRecord;
import com.qk.mapper.BusinessMapper;
import com.qk.mapper.ClueTrackRecordMapper;
import com.qk.mapper.CuleMapper;
import com.qk.service.CuleService;
import com.qk.utils.CurrentUserHolder;
import org.springframework.beans.BeanInfoFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ClueServiceImpl extends ServiceImpl<CuleMapper, Clue> implements CuleService{
   @Autowired
    private ClueTrackRecordMapper clueTrackRecordMapper;
   @Autowired
   private  BusinessMapper businessMapper;
    @Override
    public PageResult<Clue> listSelect(ClueQueryDto clueQueryDto) {
        //开启分页 使用MybatisPlus封装的ServiceImpl的page方法，page使用之前必须创建对象
        Page<Clue> cluePage = new Page<>(clueQueryDto.getPage(), clueQueryDto.getPageSize());
        //调用baseMapper的自定义方法listselect方法进行查询，返回的是查询到的Clue对象列表
        IPage<Clue> page = baseMapper.listselect(cluePage,clueQueryDto);
        //将查询到的列表的总数和列表返回给controller
        return new PageResult<>(page.getTotal(),page.getRecords());
    }
/*根据id查询-回显*/
    @Override
    public Clue selectById(Integer id) {
        Clue clue=baseMapper.selectByidd(id);
        return clue;
    }
/*线索跟进  更新线索信息   新增跟进记录*/
    //该方法设计两条数据库的操作，以防第一条执行成功，第二条未成功，导致的数据不一致
    //我们添加事物管理功能，加注解@Transactional 默认是有运行时异常时就算第一条执行成功 也会立即进行撤销
    //rollbackFor = Exception.class的作用是 更改未有异常就会立即进行撤销
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void trackClue(Clue clue) {
        //1：更新线索信息：
        //一旦有人跟进 线索状态就会改变为跟进中（3）
        clue.setStatus(3);
        //只要更新,更新时间就要变为现在
        clue.setUpdateTime(LocalDateTime.now());
        //调用Mybatis-plus的basemapper内置的修改方法
        baseMapper.updateById(clue);

        //2:新增跟进记录
        //创建跟进记录实体类的对象
        ClueTrackRecord clueTrackRecord = new ClueTrackRecord();
        clueTrackRecord.setClueId(clue.getId());
        //当前用户是从CurrentUserHolder工具类上获取，类内是ThreadLocal的方法
        //从拦截器解析令牌时候把用户id上传到该工具类上，现在使用再从该类进行获取
        clueTrackRecord.setUserId(CurrentUserHolder.getCurrentUser());//跟进人设置为当前跟进人
        clueTrackRecord.setSubject(clue.getSubject());//把subject修改为前端传来的
        clueTrackRecord.setLevel(clue.getLevel());
        clueTrackRecord.setRecord(clue.getRecord());
        clueTrackRecord.setNextTime(clue.getNextTime());
        clueTrackRecord.setCreateTime(LocalDateTime.now());
        //调用clueTrackRecordMapper层的Mybatis-plus内置的insert增加方法
        clueTrackRecordMapper.insert(clueTrackRecord);

    }
/*转商机  修改(线索)+增加(商机)*/
    //添加事务
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void toBusiness(Integer clueid) {
        //1:修改线索
        Clue clue = baseMapper.selectById(clueid);
        clue.setStatus(5);//把状态修改为商机
        clue.setUpdateTime(LocalDateTime.now());//把修改时间改为当前
        //调用Mybatis-plus的mapper层进行修改
        baseMapper.updateById(clue);

        //2:增加商机
        Business business = new Business();
        //参数太多 借助spring的beanUntil工具类进行拷贝  BeanUtils.copyProperties(源文件，目标文件);
        BeanUtils.copyProperties(clue,business);
        //拷贝是以实体类的参数进行对应的，若对应不上则会忽略，我们要检查 对应不上的手动对应
        business.setId(null);//id使用数据库默认自增 不使用拷贝
        business.setUserId(null);//设置商机归属人为null，刚转到商机肯定未分配
        business.setNextTime(null);//下次联系实际设置为null，归属人还未分配
        business.setStatus(1);//商机状态设置为待分配
        business.setClueId(clue.getId());//设置归属线索ID为线索的ID
        business.setCreateTime(LocalDateTime.now());//设置创建时间为现在
        business.setUpdateTime(LocalDateTime.now());//设置修改时间为当前
        //调用Mybatis-plus的insert类进行添加商机
        businessMapper.insert(business);

    }
}
