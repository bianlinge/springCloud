package com.dove.mongodb.mongoYun.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:explorer-config.properties")
@ConfigurationProperties(prefix = "explorer")//explorer-config.properties属性的前缀
@Data
public class ExplorerConfig {
    private String filesDepotDir;
    private String rootDir;
    private String usersRootDir;
    private String tempRootDir;
    private String publicRootDir;
    private String port;
}
