
package com.mayikt.api.member.service.weixin;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
*@title: AppWeiXin
*@description； 项目
*@author taotao
*@date 2020/12/1 11:34
*/
@MapperScan("com.mayikt.api.member.service.weixin.impl.mapper")
@EnableSwagger2Doc
@SpringBootApplication
@EnableFeignClients
public class AppWeiXin {

    public static void main(String[] args) {
        SpringApplication.run(AppWeiXin.class);
    }
}
