package com.agile.qdmp.standalong.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: wenbinglei
 * @Date: 2022/6/22 18:53
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "sys")
public class SysConfig {
    private String fileDirectory;
    private String serverUri;
    private String userName;
    private String password;
}
