package com.dove.zk;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @RequestMapping("/get")
    public JSONObject getString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", 123456);
        return jsonObject;
    }
    @RequestMapping("/getHtml")
    public String getHtml(){
        return "success";
    }

}
