package com.dove.mongodb.mongoYun.service;

import com.dove.mongodb.mongoYun.common.ResultMsg;

public interface MemberService {
    ResultMsg<?> login(String userName,String password);

    ResultMsg<?> logout(Object o);
}
