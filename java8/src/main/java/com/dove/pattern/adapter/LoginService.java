package com.dove.pattern.adapter;

public class LoginService {

    public Result sign(String  openId,String password){
        User user = new User();
        user.setOpenId(openId);
        user.setPassword(null);
        Result result = new Result();
        result.setCode("200");
        result.setMsg("注册成功");
        result.setData(openId);
        return result;
    }
    public Result login(User user){
        Result result = new Result();
        result.setMsg("登录成功");
        result.setCode("200");
        return result ;
    }

}
