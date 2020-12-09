
package com.mayikt.api.member.service.impl;

import com.mayikt.api.member.api.service.MemberService;
import com.mayikt.api.member.service.fegin.WeiXinServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author taotao
 * @title: MemberService
 * @description； 项目
 * @date 2020/12/1 11:58
 */
@RestController
public class MemberServiceimpl implements MemberService {
    @Autowired
    private WeiXinServiceFeign weiXinServiceFeign;
@Value("${taotao.name}")
private  String name;

    @Override
    public String memberToWeiXin(Long userId) {
        String msg="会员服务调用：" + weiXinServiceFeign.appinfo(userId)+name;
        return  msg;
    }
    @GetMapping("/index")
    public  String index(){
        return name;
    }
}
