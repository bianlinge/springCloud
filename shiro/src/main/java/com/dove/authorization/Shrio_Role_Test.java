package com.dove.authorization;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

public class Shrio_Role_Test {
    public static void main(String[] args) {
        String inipath = "classpath:shiro-role.ini";
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> iniSecurityManagerFactory = new IniSecurityManagerFactory(inipath);

        //2、得到SecurityManager实例 并绑定给SecurityUtils，这是一个全局设置，设置一次即可
        org.apache.shiro.mgt.SecurityManager securityManager = iniSecurityManagerFactory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("dove", "123456");

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
        PrincipalCollection principals = subject.getPrincipals();
        System.out.println("============================");
        List list = principals.asList();
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println("登陆者："+o);
        }

//        身份验证后进行角色验证：

       /* boolean admin = subject.hasRole("admin");
        //Assert.assertEquals(true,admin);
        boolean[] result = subject.hasRoles(Arrays.asList("admin", "user","user1"));
        Assert.assertEquals(true,result[0]);
        Assert.assertEquals(true,result[1]);
//        Assert.assertEquals(true,result[2]);
        boolean b = subject.hasAllRoles(Arrays.asList("admin", "user"));
        Assert.assertEquals(true,b);*/

//        身份验证完后进行权限验证
        boolean delete = subject.isPermitted("table:delete");
        Assert.assertEquals(true,delete);
//        subject.checkPermission("table:preview");
       // subject.checkPermissions("table:update","table:add");
//        subject.checkPermissions("system:table:preview");
        // 6. 退出
        subject.logout();

        System.out.println("用户退出");

    }
}
