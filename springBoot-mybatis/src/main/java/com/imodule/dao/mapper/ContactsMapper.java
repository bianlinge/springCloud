package com.imodule.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ContactsMapper {
    //添加个人信息标签
    public int addPriInfoLabel( List<Map<String,Object>> lables);


}
