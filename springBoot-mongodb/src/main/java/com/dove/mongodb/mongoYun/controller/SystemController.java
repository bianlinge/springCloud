package com.dove.mongodb.mongoYun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dove.mongodb.mongoYun.common.ResultMsg;
import com.dove.mongodb.mongoYun.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/login.json")
    public ResponseEntity login(@RequestParam(value = "userName") String userName,
                                @RequestParam(value = "password") String password,
                                @RequestParam(value = "iframe") String iframe,
                                @RequestParam(value = "callback") String callback,
                                @RequestParam(value = "jumpto") String jumpto) {

        ResultMsg<?> loginRes = memberService.login(userName, password);

        HttpHeaders headers = new HttpHeaders();
        //不使用缓存
        headers.add("Cache-Contorl", "no-cache,no-store,must-revalidate");
        // 兼容HTTP/1.0 和Cache-Contorl 一样
        headers.add("Pragma", "no-cache");
        // 表示存在时间，允许客户端在这个时间之前不去检查（发请求）
        headers.add("Expires", "0");
        String jsonString = JSON.toJSONString(loginRes);
        if (StringUtils.isEmpty(jumpto)) {
            JSONObject jsonObject = JSON.parseObject(jsonString);
            JSONObject data = jsonObject.getJSONObject("data");
            data.put("jumpto", jumpto);
            jsonString = jsonObject.toString();
        }


        if ("1".equals(iframe)) {
            StringBuffer returnStr = new StringBuffer();
            returnStr.append("window.parent." + (StringUtils.isEmpty(callback) ? "callback" : callback) + "(" + jsonString + ")");
            returnStr.insert(0, "<script type=\"text/javascript\">").append("</script>");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("text/html"))
                    .body(returnStr.toString());
        } else {
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/json"))
                    .body(StringUtils.isEmpty(callback) ? jsonString : (callback + "(" + jsonString + ")"));
        }
    }


    @GetMapping("/logout.json")
    public Mono<Object> logout(){
        ResultMsg<?> result = memberService.logout(null);
        return Mono.just(result);
    }

    public static void main(String[] args) {
        String callback = "www.baidu.com";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "success");
        System.out.println(jsonObject.toJSONString());
        System.out.println(jsonObject.toString());
        System.out.println(jsonObject);
        StringBuffer returnStr = new StringBuffer();
        returnStr.append("window.parent.open" + (StringUtils.isEmpty(callback) ? "callback" : callback) + "(" + jsonObject.toJSONString() + ")");
        returnStr.insert(0, "<script type=\"text/javascript\">").append("</script>");
        System.out.println(returnStr.toString());

    }
}
