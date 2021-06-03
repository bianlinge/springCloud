package com.dove.web.fileupdown;

import com.dove.web.excel.MakeExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@RestController
@Api(tags = "文件上传")
public class UploadController {
    @Value("${destdir}")
    private String destdir;

    @ApiOperation("单个文件上传到服务器")
    @RequestMapping(value = "/upload", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    public String upload(@RequestParam(value = "file", required = true) MultipartFile file) throws Exception {
        if (file == null) {
            throw new Exception("文件不存在");
        }
        if (!destdir.endsWith(File.separator)) {
            destdir = destdir + File.separator;
        }
        String filename = file.getOriginalFilename();
        String sufferName = filename.substring(filename.indexOf("."));
        String fileName = UUID.randomUUID() + sufferName;
        String filePath = destdir + fileName;
        file.transferTo(new File(filePath));
        return fileName;
    }


    @ApiOperation("传递字节流上传文件")
    @RequestMapping(value = "/uploadbytes", method = RequestMethod.POST)
    public String uploadFileByBytes(@RequestBody byte[] bytes) throws Exception {
        if (bytes==null||bytes.length<=0) {
            throw new Exception("字节流不存在");
        }
        if (!destdir.endsWith(File.separator)) {
            destdir = destdir + File.separator;
        }
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
        OutputStream out = new FileOutputStream(destdir+"hssfWorkbook_writeout.xls");
        hssfWorkbook.write(out);
        OutputStream out1 = new FileOutputStream(destdir+"IOUtils_write_hssfWorkbookBytes.xls");
        IOUtils.write(hssfWorkbook.getBytes(),out1);
        OutputStream out2 = new FileOutputStream(destdir+"hssfWorkbook_to_byteArrayOutputStream.xls");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        hssfWorkbook.write(byteArrayOutputStream);
        IOUtils.write(byteArrayOutputStream.toByteArray(),out2);
        out.close();
        out1.close();
        out2.close();
        byteArrayOutputStream.close();
        hssfWorkbook.close();
        /////////////
       /* OutputStream outputStream = new FileOutputStream(destdir + "test.xls");
        IOUtils.write(bytes, outputStream);*/
        return "上传成功";
    }

}
