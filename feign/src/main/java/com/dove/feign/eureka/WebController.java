package com.dove.feign.eureka;


import com.dove.MakeExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WebController {
    @Autowired
    WebService webService;

    @PostMapping("/upload")
    public String TestFeign(@RequestParam(value = "file") MultipartFile file) throws Exception {
        String fileid = webService.upload(file);
        return fileid;
    }

    @PostMapping("/uploadbytes")
    public String uploadFileByBytes() throws Exception {
        //////生成Excel文档////////
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
        HSSFWorkbook hssfWorkbook = MakeExcelUtil.createHSSFWorkbook(list, headerList, title);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        hssfWorkbook.write(byteArrayOutputStream);
        String fileid = webService.uploadFileByBytes(byteArrayOutputStream.toByteArray());
        return fileid;
    }
}
