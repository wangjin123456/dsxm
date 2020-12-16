
package com.mayikt;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
*@title: AppMember
*@description； 项目
*@author taotao
*@date 2020/12/1 12:07
*/
@EnableSwagger2Doc
@SpringBootApplication
@EnableFeignClients
@EnableAsync
@MapperScan("com.mayikt.api.member.service.mapper")
public class AppMember {

    public static void main(String[] args) {
        SpringApplication.run(AppMember.class);
    }
}
