package com.mayikt.api.member.service.entitydo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: UserEntity
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1220:46
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("meite_user")
public class UserDo {

    /**
     * userid
     */
    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Long userId;
    /**
     * 手机号码
     */

    private String mobile;
    /**
     * 邮箱
     */

    private String email;
    /**
     * 密码
     */

    private String pass_word;
    /**
     * 用户名称
     */

    private String userName;
    /**
     * 性别 0 男 1女
     */

    private int sex;
    /**
     * 年龄
     */

    private int age;
    /**
     * 注册时间
     */

    private Date CREATE_TIME;
    /**
     * 修改时间
     */

    private Date updateTime;
    /**
     * 账号是否可以用 1 正常 0冻结
     */

    private int isnormal;
    /**
     * 用户头像
     */

    private String PIC_IMG;
    /**
     * 用户关联 QQ 开放ID
     */

    private String QQ_OPENID;
    /**
     * 用户关联 微信 开放ID
     */
    private String WX_OPENID;


}
