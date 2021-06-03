package com.dove.controller;

import com.dove.dao.entity.CashValueApplay;
import com.dove.dao.entity.Category;
import com.dove.seriviceImpl.CashValueImpl;
import com.dove.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
public class TestController {
    @Autowired
    CourseService courseService;
    @Autowired
    CashValueImpl cashValueImpl;
    @GetMapping("/find")
    public Category findOne(@RequestParam(value = "id",required = true) String id){
        return courseService.findOne(id);
    }
    @GetMapping("/test")
    public String test(){
        return "111111111111111111111";
    }

    @GetMapping("/cashvalue")
    public List<CashValueApplay> cashvalue(){
        List<CashValueApplay> cashValueApplays = cashValueImpl.seachCashValue();
        return cashValueApplays;
    }
}
