package com.dove.iniRealm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class ShiroMyRealmTest {
    public static void main(String[] args) {
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        SecurityManager securityManager = iniSecurityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("dove", "123456");
        try {
            subject.login(token);
            System.out.println("登陆成功");
        } catch (AuthenticationException a) {
            a.printStackTrace();
            System.out.println("用户名或密码不正常，身份验证失败，请重新登录");
        }
        subject.logout();
    }
}
