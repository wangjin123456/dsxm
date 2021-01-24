
package com.mayikt.api.member.service.strategy.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.member.service.entitydo.MeiteUnionLogin;
import com.mayikt.api.member.service.entitydo.UserDo;
import com.mayikt.api.member.service.mapper.UserMapper;
import com.mayikt.api.member.service.strategy.UnionLoginStrategy;
import com.mayikt.base.BaseResponse;
import com.mayikt.http.HttpClientUtils;
import com.mayikt.utils.TokenUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
*@title: UnionLoginStrategyImpl4
*@description； 项目
*@author taotao
*@date 2021/1/4 12:40
*/  
@Component
public class QQUnionLoginStrategy implements UnionLoginStrategy {
    @Value("${mayikt.login.qq.accesstoken}")
    private String qqAccessTokenAddres;
    @Value("${mayikt.login.qq.openid}")
    private String qqOpenIdAddres;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private UserMapper userMapper;
    @Override
    public String unionLoginCallback(HttpServletRequest request,  MeiteUnionLogin meiteUnionLogin) {
        System.out.println("进入qq");
        String code= request.getParameter("code");

        if(Strings.isBlank(code)){
            return null;
        }
        //根据授权码获取accesstoken
        String newqqAccessTokenAddres = qqAccessTokenAddres.replace("{client_id}"
                , meiteUnionLogin.getAppId()).replace("{client_secret}", meiteUnionLogin.getAppKey()).
                replace("{code}", code).replace("{redirect_uri}", meiteUnionLogin.getRedirectUri()).replace("\"","");
        String resultAccessToken = HttpClientUtils.httpGetResultString(newqqAccessTokenAddres);
        boolean contains=resultAccessToken.contains("access_token=");
        if(!contains){
            return null;
        }
        String[] split=resultAccessToken.split("=");
        String accessToken=split[1];
        //2,根据accesstoken获取oppenid信息
        // 2.根据accessToken获取用户的openid
        String resultQQOpenId = HttpClientUtils.httpGetResultString(qqOpenIdAddres+accessToken);
        if (StringUtils.isEmpty(resultQQOpenId)) {
            return null;
        }
        boolean openid = resultQQOpenId.contains("openid");
        if (!openid) {
            return null;
        }

        String array[] = resultQQOpenId.replace("callback( {", "").
                replace("} );", "").split(",");
        String openId = array[1];
        // 3.将openid存入到redis中
        String token = tokenUtils.createToken("qq.openid.", openId);
        return token;


    }

    @Override
    public UserDo getUserId(String openid) {
        return userMapper.selectByQQOpenId(openid);
    }
}
