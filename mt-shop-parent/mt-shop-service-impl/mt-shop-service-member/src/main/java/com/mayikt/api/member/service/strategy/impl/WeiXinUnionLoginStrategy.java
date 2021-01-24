
package com.mayikt.api.member.service.strategy.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.member.service.entitydo.MeiteUnionLogin;
import com.mayikt.api.member.service.entitydo.UserDo;
import com.mayikt.api.member.service.mapper.UserMapper;
import com.mayikt.api.member.service.strategy.UnionLoginStrategy;
import com.mayikt.http.HttpClientUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
*@title: WeiXinUnionLoginStrategy
*@description； 项目
*@author taotao
*@date 2021/1/24 9:07
*/  
@Component
public class WeiXinUnionLoginStrategy implements UnionLoginStrategy {
    @Value("${mayikt.login.wx.accesstoken}")
    private String weixinAccessTokenAddres;
    @Autowired
    private UserMapper userMapper;
    @Override
    public String unionLoginCallback(HttpServletRequest request, MeiteUnionLogin meiteUnionLogin) {
         String code=request.getParameter("code");
         if(Strings.isBlank(code)){
             return  null;
         }
         //獲取微信地址
        String newWeixinAccessTokenAddress=weixinAccessTokenAddres.replace("APPID",meiteUnionLogin.getAppId()+"").replace("SECRET",meiteUnionLogin.getAppKey()).replace("CODE",code);

        JSONObject jsonObject= HttpClientUtils.httpGet(newWeixinAccessTokenAddress);
        if(jsonObject==null){
            return  null;
        }
        //獲取oppenid
        String openid=jsonObject.getString("openid");
        if(Strings.isBlank(openid)){
            return  null;
        }

        return openid;
    }

    @Override
    public UserDo getUserId(String openid) {
        return  userMapper.selectByOpenId(openid);
    }
}
