package com.dove.dao.mapper;

import com.dove.dao.entity.CashValue;
import com.dove.dao.entity.CashValueApplay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CashValueMapper {
    List<CashValueApplay> searchCashValueFor6(CashValue cashValue);
}
