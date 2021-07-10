package com.dove;

import com.dove.dao.entity.CashValue;
import com.dove.dao.entity.CashValueApplay;
import com.dove.dao.mapper.CashValueMapper;
import com.dove.seriviceImpl.CashValueImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan({"com.dove.dao.mapper"})
public class CashValueTest {
   /* @Autowired
    CashValueMapper cashValueMapper;*/
  /*  @Test
    public void searchfor6(){
        CashValue cashValue = new CashValue();
        cashValue.setiPageSize("10");
        cashValue.setiStartNo("0");
        cashValue.setPlcNo("1111111");
        List<CashValueApplay> cashValueApplays = cashValueMapper.searchCashValueFor6(cashValue);

        for (CashValueApplay cashValueApplay : cashValueApplays) {
            System.out.println(cashValueApplay);
        }
    }*/

  @Autowired
    CashValueImpl cashValueImpl;
  @Test
    public void test(){
    List<CashValueApplay> cashValueApplays = cashValueImpl.seachCashValue();
    System.out.println(cashValueApplays.get(0));
  }
}
