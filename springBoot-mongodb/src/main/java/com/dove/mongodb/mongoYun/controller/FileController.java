package com.dove.mongodb.mongoYun.controller;

import com.alibaba.fastjson.JSON;
import com.dove.mongodb.mongoYun.common.ResultMsg;
import com.dove.mongodb.mongoYun.common.constant.ExplorerConstants;
import com.dove.mongodb.mongoYun.entity.Progress;
import com.dove.mongodb.mongoYun.service.IUFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web")
public class FileController {
    @Autowired
    IUFileService fileService;

    @RequestMapping("/upload/progress.json")
    public ResponseEntity progress(@RequestParam(value = "X-Progress-ID") String progressId,
                                   @RequestParam(value = "callback") String callback) {

        Progress progress = new Progress();

        progress.setFinish(1);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache,no-store,must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        String json = JSON.toJSONString(progress);

        ResponseEntity<String> body = ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/json"))
                .body((callback == null) ? json : (callback + "(" + json + ")"));
        return body;
    }

    @GetMapping(value = "/preview/{id:\\w+}.file")
    public ResponseEntity preview(@PathVariable(name = "id") String id){
        ResultMsg<?> resultMsg = fileService.download(ExplorerConstants.ANONYMOUS,id);
        return null;
    }
}
