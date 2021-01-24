
package com.mayikt.api.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.member.api.service.MemberOpenIdTokenLogin;
import com.mayikt.api.member.service.entitydo.UserDo;
import com.mayikt.api.member.service.mapper.UserMapper;
import com.mayikt.api.member.service.strategy.UnionLoginStrategy;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.utils.SpringContextUtils;
import com.mayikt.utils.TokenUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author taotao
 * @title: MemberOpenIdTokenLoginImpl
 * @description； 项目
 * @date 2021/1/24 11:01
 */
@RestController
public class MemberOpenIdTokenLoginImpl extends BaseApiService implements MemberOpenIdTokenLogin {
    @Autowired
    private TokenUtils tokenUtils;
   @Autowired
   private UserMapper userMapper;
    @Value("${mayikt.login.token.prefix}")
    private String loginTokenPrefix;

    @Override
    public BaseResponse<JSONObject> openIdLoginToken(String openToken) {
        if (Strings.isBlank(openToken)) {
            return null;
        }
        //1,根據openToken 獲取真實的openid
        String tookenvalue = tokenUtils.getTokenValue(openToken);
        if (Strings.isBlank(tookenvalue)) {
            return setResultError("流程失效或者token錯誤");
        }
        //2,根據openid 查詢是否關聯
        JSONObject jsonObject = JSON.parseObject(tookenvalue);
        String openId = jsonObject.getString("openId");
        String unionPublicId=jsonObject.getString("unionPublicId");

        UserDo userDo= null;
      switch (unionPublicId) {
            case "mayikt_qq":
                userDo = userMapper.selectByQQOpenId(openId);
                break;
            case "mayikt_weixin":
                userDo = userMapper.selectByOpenId(openId);
                break;
        }
        if(userDo==null){
            return  setResultError("當前用戶沒有關聯，跳轉到關聯頁面");
        }
        //3,如果已經關聯，自動實現登錄
        //獲取userID
         long userID=userDo.getUserId();
        String userToken=tokenUtils.createToken(loginTokenPrefix,userID+"");
        JSONObject data=new JSONObject();

        data.put("userToken", userToken);
        return setResultSuccess(data);

    }
}
