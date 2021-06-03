package com.dove.jdbcRealm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;

/**
 * jdbc 自动验证
 * 表结构必须一致 表名必须一致
 *
 */
public class Shiro_JDBCReal_Test {
    public static void main(String[] args) {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        String iniPath = "classpath:shiro-jdbc-realm.ini";
        Factory<SecurityManager> iniSecurityManagerFactory = new IniSecurityManagerFactory(iniPath);

        //2、得到SecurityManager实例 并绑定给SecurityUtils，这是一个全局设置，设置一次即可
        org.apache.shiro.mgt.SecurityManager securityManager = iniSecurityManagerFactory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("dove","123");
        String username = usernamePasswordToken.getUsername();
        char[] password = usernamePasswordToken.getPassword();
        System.out.println(username+String.valueOf(password));
        //4、登录，即身份验证
        try {
            subject.login(usernamePasswordToken);
            System.out.println(usernamePasswordToken.getPrincipal()+"登陆成功");
        } catch (AuthenticationException e) {
            // 如果身份验证失败请捕获 AuthenticationException 或其子类，
            // 常见的如： DisabledAccountException（禁用的帐号）、
            // LockedAccountException（锁定的帐号）、
            // UnknownAccountException（错误的帐号）、
            // ExcessiveAttemptsException（登录失败次数过多）、
            // IncorrectCredentialsException （错误的凭证）、
            // ExpiredCredentialsException（过期的凭证）等，
            // 具体请查看其继承关系；对于页面的错误消息展示，
            // 最好使用如 “用户名 / 密码错误” 而不是 “用户名错误”/“密码错误”，防止一些恶意用户非法扫描帐号库；
            e.printStackTrace();
            //5、身份验证失败
            System.out.println("用户名或密码不正常，身份验证失败，请重新登录");
        }
        Assert.assertEquals(true,subject.isAuthenticated());
        // 6. 退出
        subject.logout();

        System.out.println("用户已登陆,自动退出");
    }

}
