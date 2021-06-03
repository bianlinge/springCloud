package com.dove.seriviceImpl;

import com.dove.dao.entity.CashValue;
import com.dove.dao.entity.CashValueApplay;
import com.dove.dao.mapper.CashValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashValueImpl {
    @Autowired
    private CashValueMapper cashValueMapper;
    public List<CashValueApplay> seachCashValue(){
        CashValue cashValue = new CashValue();
        cashValue.setiPageSize("10");
        cashValue.setiStartNo("0");
        cashValue.setPlcNo("1111111");
        List<CashValueApplay> cashValueApplays = cashValueMapper.searchCashValueFor6(cashValue);
        return cashValueApplays;
    }
}
