
package com.mayikt.api.member.service;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
*@title: AppMember
*@description； 项目
*@author taotao
*@date 2020/12/1 12:07
*/
@EnableSwagger2Doc
@SpringBootApplication
@EnableFeignClients

public class AppMember {

    public static void main(String[] args) {
        SpringApplication.run(AppMember.class);
    }
}
