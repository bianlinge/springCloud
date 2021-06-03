package com.dove.pattern.adapter;

/**
 * 适配模式 不改变原有的
 * 1.采用继承原有的接口
 * 2.接口形式
 * 3.注入原有功能接口
 */
public class NewLoginService extends LoginService{

    public Result loginForWeChat(String openId) {
        Result msg = super.sign(openId, null);
        User user = new User();
        user.setOpenId(msg.getData());
        Result login = super.login(user);
        return login;

    }

    public Result loginForPhone(String phone,String code) {

        return null;

    }
}
