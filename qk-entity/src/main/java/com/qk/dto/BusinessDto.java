package com.qk.dto;

import lombok.Data;
import org.springframework.objenesis.instantiator.perc.PercInstantiator;
@Data
public class BusinessDto {
private Integer businessId;//商机ID
private  String name;//客户姓名
private  String phone;//手机号
private  Integer status;//状态
private String assignName;//归属人姓名
private  Integer page =1;//页码 默认为1
private  Integer pageSize=10;//分页每页记录数，默认为10
}
