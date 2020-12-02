
package com.mayikt.api.member.service.weixin.api;


import com.mayikt.api.member.service.weixin.WeiXinService;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
*@title: WeiXinServiceImpl
*@description； 项目
*@author taotao
*@date 2020/12/1 11:31
*/  
@RestController
public class WeiXinServiceImpl extends BaseApiService implements WeiXinService {
    @Override
    public String appinfo(@RequestParam("userId") long userId ) {
        int id=1;
        return "微信接口"+userId;
    }

    @Override
    public BaseResponse<String> addApp(String appId, String appPwd) {
        if(StringUtils.isEmpty(appId)){
            return  setResultError("appid不能为空!");
        }
        if(StringUtils.isEmpty(appPwd)){
            return  setResultError("appPwd不能为空!");
        }
        return setResultSuccess("访问成功");
    }


}
