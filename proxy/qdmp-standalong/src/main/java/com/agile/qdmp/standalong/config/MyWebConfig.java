package com.agile.qdmp.standalong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: wenbinglei
 * @Date: 2022/11/10 20:18
 * @Description:
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Autowired
    private SysConfig sysConfig;

    //添加文件服务器位置
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler(new String[]{serverPath + "/**"}).addResourceLocations("file:/" + realPath);
//        registry.addResourceHandler(new String[]{"/drawing/**"}).addResourceLocations("file:/" + sysConfig.getFileDirectory());
        registry.addResourceHandler("/f/**").addResourceLocations("file:/");
    }
}