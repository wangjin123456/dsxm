package com.mayikt.api.member.service.weixin.impl.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: WechatKeyword
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1018:26
 */
@Data
public class WechatKeyword {
    private Long id;
    private String keywordName;
    private String keywordValue;
    private Date createTime;
    private Date updateTime;
    private Long version;
}
