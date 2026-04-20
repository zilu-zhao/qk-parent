package com.qk.controller;

import com.qk.common.Result;
import com.qk.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController

public class UploadController {

    /*    @PostMapping("/upload")
         public Result upload(MultipartFile file) throws Exception {
            *//*存放在本地的方式*//*
        //上传文件开始
        log.info("上传文件开始+{}",file.getOriginalFilename());
        //第一步：获取传来的文件名
        String name=file.getOriginalFilename();
        //第二步：获取文件名后缀（以.进行分割）
        String suffix = name.substring(name.lastIndexOf("."));
        //第三步：获取随机字符拼接上刚刚获取到的后缀suffix设置为新文件名，随机字符使用uuid
        String newFileName = UUID.randomUUID().toString() + suffix;
        //第四步：保存文件
      file.transferTo(new File("D:\\Pictures\\Saved Pictures"+newFileName));
        return  Result.success();
     }*/
    /*在企业项目开发中 不能存放在本地，需要存放到oss云服务器上
     * 第一步：获取原始文件名
     * 第二步：重命名
     * 第三步 使用自己封装的 AliyunOSSOperator工具类，将文件传到阿里云OSS，
     * 然后得到url，把url返回给前端，添加用户的时候提交时将url一起提交到后端存入数据库*/
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception {
//第一步：获取传来的文件名
        String name = image.getOriginalFilename();
        //第二步：获取文件名后缀（以.进行分割）
        String suffix = name.substring(name.lastIndexOf("."));
        //第三步：获取随机字符拼接上刚刚获取到的后缀suffix设置为新文件名，随机字符使用uuid
        String newFileName = UUID.randomUUID().toString() + suffix;
//第四步：使用自己封装的 AliyunOSSOperator工具类的upload方法，将文件传到阿里云OSS，然后返回url
        String url = aliyunOSSOperator.upload(image.getBytes(), newFileName);
        //把url返回到前端
        return Result.success(url);
    }
}
