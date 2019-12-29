package com.imodule.downExcel;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(tags = "Excel下载")
@Controller
public class downController {
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    @ResponseBody
    public String download(){
        File destFile = new File("G:/test.xls");
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("name","张三");
        map1.put("age","23");
        map1.put("address","深圳");
        map1.put("sex","男");

        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("name","李四");
        map2.put("age","20");
        map2.put("address","西安");
        map2.put("sex","女");
        list.add(map1);
        list.add(map2);
        List<String> headerList = new ArrayList<>();
        headerList.add("name");
        headerList.add("age");
        headerList.add("address");
        headerList.add("sex");
        String title = "学生信息表";
        try {
            MakeExcelUtil.createExcelFile(list,destFile,headerList,title);
            return "success.....";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail......";
        }
    }
}
