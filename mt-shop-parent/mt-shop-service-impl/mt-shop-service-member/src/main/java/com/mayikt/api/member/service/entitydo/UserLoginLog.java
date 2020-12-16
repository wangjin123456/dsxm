package com.mayikt.api.member.service.entitydo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: userLoginLog
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1218:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginLog {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private Date loginTime;

    private String loginToken;

    private String channel;

    private String equipment;

    private Integer isAvailability;

    private String loginip;

    public UserLoginLog(Long userId, Date loginTime, String loginToken, String channel, String equipmen,Integer isAvailability,String loginip) {
        this.userId = userId;
        this.loginTime = loginTime;
        this.loginToken = loginToken;
        this.channel = channel;
        this.equipment = equipment;
        this.isAvailability = isAvailability;
        this.loginip = loginip;
    }
}
