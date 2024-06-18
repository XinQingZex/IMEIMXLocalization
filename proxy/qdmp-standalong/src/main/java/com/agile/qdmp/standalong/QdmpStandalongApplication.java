package com.agile.qdmp.standalong;

import com.agile.tem.common.swagger.annotation.EnableHyzhSwagger2;
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@EnableHyzhSwagger2
@EnableAsync
@EnableScheduling
public class QdmpStandalongApplication {

    public static void main(String[] args) {
//        SpringApplication.run(QdmpStandalongApplication.class, args);

        SpringApplicationBuilder builder = new SpringApplicationBuilder(QdmpStandalongApplication.class);
        builder.headless(false).run(args);
    }

}
