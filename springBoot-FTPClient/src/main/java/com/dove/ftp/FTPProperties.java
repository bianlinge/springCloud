package com.dove.ftp;

import lombok.Data;
import org.apache.commons.net.ftp.FTP;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * FTP 服务器配置文件
 * 加载配置内容到Spring中
 */
@Data
@Component
@PropertySource("classpath:ftp.properties")
@ConfigurationProperties(prefix = "ftp")
public class FTPProperties {
    private String username;

    private String password;

    private String host;

    private Integer port;

    private String baseUrl;

    private Integer passiveMode = FTP.BINARY_FILE_TYPE;

    private String encoding="UTF-8";

    private int clientTimeout=120000;

    private int bufferSize;

    private int transferFileType=FTP.BINARY_FILE_TYPE;

    private boolean renameUploaded;

    private int retryTime;
}
