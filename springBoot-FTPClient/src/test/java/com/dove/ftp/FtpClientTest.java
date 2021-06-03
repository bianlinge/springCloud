package com.dove.ftp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * FtpClient测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FtpClientTest {
    /**
     * 测试上传
     */
    @Test
    public void uploadFile(){
        boolean flag = FTPUtils.uploadFile("Python.md", "D:\\Backup\\桌面\\Python.md");
        Assert.assertEquals(true, flag);
    }

}
