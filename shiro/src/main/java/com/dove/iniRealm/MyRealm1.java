package com.dove.iniRealm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;


public class MyRealm1 implements Realm {

    @Override
    public String getName() {
        return "myrealm1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken 类型的token
        return token instanceof UsernamePasswordToken;
//        return false;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //用户名
        String username = (String) token.getPrincipal();
        //密码
        char[] credentials = (char[]) token.getCredentials();
        String password = String.valueOf(credentials);
        if (!"dove".equals(username)) {
            throw new UnknownAccountException("用户名不存在");
        }
        if (!"123456".equals(password)) {
            throw new IncorrectCredentialsException("密码错误");
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；

       AuthenticationInfo authenticationInfo =  new SimpleAuthenticationInfo(username,password,getName());
        return authenticationInfo;
    }
}
