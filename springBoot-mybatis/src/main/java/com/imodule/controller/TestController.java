package com.imodule.controller;

import com.imodule.dao.entity.Category;
import com.imodule.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class TestController {
    @Autowired
    CourseService courseService;
    @GetMapping("/find")
    public Category findOne(@RequestParam(value = "id",required = true) String id){
        return courseService.findOne(id);
    }
    @GetMapping("/test")
    public String test(){
        return "111111111111111111111";
    }
}
