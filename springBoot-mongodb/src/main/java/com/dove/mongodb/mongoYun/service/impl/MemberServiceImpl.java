package com.dove.mongodb.mongoYun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dove.mongodb.mongoYun.common.ResultMsg;
import com.dove.mongodb.mongoYun.entity.Member;
import com.dove.mongodb.mongoYun.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Override
    public ResultMsg<?> login(String userName, String password) {
        ResultMsg<Object> resultMsg = new ResultMsg<>();
        resultMsg.setCode(1);
        resultMsg.setMsg("login success");
        //登陆逻辑
        JSONObject jsonObject = new JSONObject();
        Member member = new Member();
        jsonObject.put("data",member);
        resultMsg.setData(jsonObject);
        return resultMsg;
    }

    @Override
    public ResultMsg<?> logout(Object o) {
        return null;
    }
}
