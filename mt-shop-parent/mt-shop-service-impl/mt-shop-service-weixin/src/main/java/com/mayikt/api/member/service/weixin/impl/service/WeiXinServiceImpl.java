
package com.mayikt.api.member.service.weixin.impl.service;


import com.mayikt.api.member.service.weixin.WeiXinService;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
*@title: WeiXinServiceImpl
*@description； 项目
*@author taotao
*@date 2020/12/1 11:31
*/
@Slf4j
@RestController
public class WeiXinServiceImpl extends BaseApiService implements WeiXinService {

    private  String name;
   
    @Override
    public String appinfo(@RequestParam("userId") long userId ) {
        int id=1;
        return "微信接口"+userId+name;
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
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = { "application/json;charset=utf-8" })
    public String getWxUserInfo(HttpServletRequest request,
                                @RequestParam(required = false) String echostr,
                                @RequestParam(required = false) String signature,
                                @RequestParam(required = false) String timestamp,
                                @RequestParam(required =false) String nonce
    ) {
        try {
            //只需要把微信请求的 echostr, 返回给微信就可以了
           log.info("测试来过===================" + echostr);
           log.info("测试来过===================" + signature);
           log.info("测试来过===================" + timestamp);
           log.info("测试来过===================" + nonce);
            return echostr;
        } catch (Exception e) {
           log.info("测试微信公众号的接口配置信息发生异常：", e);
            return "错误！！！";
        }

    }

}
