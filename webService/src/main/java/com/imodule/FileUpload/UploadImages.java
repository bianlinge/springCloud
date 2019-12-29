package com.imodule.FileUpload;

import io.swagger.annotations.Api;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Api(tags = "文件上传")
@RestController
@RequestMapping("/upload")
public class UploadImages {
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @RequestMapping(value = "/images",method = {RequestMethod.GET,RequestMethod.POST})
    public String uploadImages() throws IOException {
        File file = new File("F:/图片/test.txt");
        FileWriter fileWriter = new FileWriter(new File("F:/图片/test1.txt"));
        //FileReader fileReader = new FileReader(file);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis, "GBK");
        try {
            int len = 0;
            while ((len=isr.read())!=-1){
                fileWriter.write(len);
            }
            System.out.println("success");
            return "复制成功";
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            isr.close();
            fileWriter.flush();
            fileWriter.close();
        }
        //FileWriter fileWriter = new FileWriter(new File("F:/图片/test.txt"));
            return "fail......";

    }
}
