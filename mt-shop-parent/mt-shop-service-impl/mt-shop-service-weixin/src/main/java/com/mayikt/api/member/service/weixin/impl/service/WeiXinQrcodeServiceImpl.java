
package com.mayikt.api.member.service.weixin.impl.service;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.member.service.weixin.WeiXinQrcodeService;
import com.mayikt.api.member.service.weixin.impl.constant.WeiXinConstant;
import com.mayikt.api.member.service.weixin.mp.config.WxMpConfiguration;
import com.mayikt.api.member.service.weixin.mp.config.WxMpProperties;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpQrcodeService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
*@title: WeiXinQrcodeServiceImpl
*@description； 项目
*@author taotao
*@date 2020/12/16 12:33
*/  
@RestController
public class WeiXinQrcodeServiceImpl  extends BaseApiService implements WeiXinQrcodeService {
   @Autowired
   private WxMpProperties wxMpProperties;
    @Override
    public BaseResponse<JSONObject> getQrUrl(Long userId) {
        if(userId == null){
           return  setResultError("userId不能为空");
        }
        //https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFy8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyUXByN1FjLUlkODAxM09HMWh3Y28AAgTyndlfAwQAjScA
        //获取配置第一个appId
        String appid=wxMpProperties.getConfigs().get(0).getAppId();
        //根据appid获取对应的 WxMpQrcodeService
        WxMpQrcodeService qrcodeService= WxMpConfiguration.getMpServices().get(appid).getQrcodeService();
        try {
            WxMpQrCodeTicket wxMpQrCodeTicket=qrcodeService.qrCodeCreateTmpTicket(userId+"", WeiXinConstant.QR_CODE_EXPIRE_SECONDS);
           String ticket=wxMpQrCodeTicket.getTicket();
           if(Strings.isBlank(ticket)){
               return  setResultError("生成二维码失败");
           }
           return  setResultSuccess(URLEncoder.encode(ticket,"utf-8"));

        } catch (WxErrorException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return null;
    }
}
