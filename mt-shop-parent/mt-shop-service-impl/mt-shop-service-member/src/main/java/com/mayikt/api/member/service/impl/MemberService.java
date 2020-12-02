
package com.mayikt.api.member.service.impl;

import com.mayikt.api.member.service.fegin.WeiXinServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author taotao
 * @title: MemberService
 * @description； 项目
 * @date 2020/12/1 11:58
 */
@RestController
public class MemberService {
    @Autowired
    private WeiXinServiceFeign weiXinServiceFeign;

    @GetMapping("memberToWeiXin")
    public String memberToWeiXin(long userId ) {
        String msg="会员服务调用：" + weiXinServiceFeign.appinfo(userId);
        return  msg;
    }

}