package com.mayikt.api.member.api.dto.req;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: UserReqRegisterDto
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1220:57
 */
@Data
@ApiModel
public class UserReqRegisterDto {
    /**
     * 手机号码
     * 密码
     * 短信验证码
     */
    /**
     * 手机号码
     */

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", name = "mobile", required = true)
    private String mobile;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "pass_word", required = true)
    private String pass_word;
//
//    private String smsCode;
}
