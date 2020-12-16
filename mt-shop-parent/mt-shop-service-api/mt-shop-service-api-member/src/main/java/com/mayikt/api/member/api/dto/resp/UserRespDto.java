package com.mayikt.api.member.api.dto.resp;

import lombok.Data;

import java.util.Date;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: UserRespDto
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1222:24
 */
@Data
public class UserRespDto {
  
    /**
     * 手机号码
     */

    private String mobile;
    /**
     * 邮箱
     */

    private String email;

    /**
     * 用户名称
     */

    private String USER_NAME;
    /**
     * 性别 0 男 1女
     */

    private char sex;
    /**
     * 年龄
     */

    private Long age;


    /**
     * 用户头像
     */

    private String PIC_IMG;


}
