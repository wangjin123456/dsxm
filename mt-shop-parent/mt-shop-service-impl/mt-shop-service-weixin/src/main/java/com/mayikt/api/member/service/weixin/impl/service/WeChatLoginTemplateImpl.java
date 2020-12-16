
package com.mayikt.api.member.service.weixin.impl.service;

import com.mayikt.api.member.service.request.LoginTemplateDto;
import com.mayikt.api.member.service.weixin.WeChatLoginTemplate;
import com.mayikt.api.member.service.weixin.mp.config.WxMpConfiguration;
import com.mayikt.api.member.service.weixin.mp.config.WxMpProperties;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.utils.SimpleDateFormatUtils;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
*@title: WeChatLoginTemplateImpl
*@description； 项目
*@author taotao
*@date 2020/12/16 15:42
*/  
@RestController
public class WeChatLoginTemplateImpl  extends BaseApiService implements WeChatLoginTemplate {
    @Autowired
    private WxMpProperties wxMpProperties;

    @Value("${mayikt.wx.loginTemplateId}")
    private String loginTemplateId;

    @Override
    public BaseResponse<Object> sendLoginTemplate(LoginTemplateDto loginTemplateDto) {

        String phone = loginTemplateDto.getPhone();
        if (StringUtils.isEmpty(phone)) {
            return setResultError("phone参数不能为空!");
        }
        String loginIp = loginTemplateDto.getLoginIp();
        if (StringUtils.isEmpty(phone)) {
            return setResultError("loginIp参数不能为空!");
        }
        Date loginTime =
                loginTemplateDto.getLoginTime();
        if (loginTime == null) {
            return setResultError("loginTime参数不能为空!");
        }
        String openId = loginTemplateDto.getOpenId();
        if (StringUtils.isEmpty(openId)) {
            return setResultError("loginIp参数不能为空!");
        }
        String equipment = loginTemplateDto.getEquipment();
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(loginTemplateId);
        wxMpTemplateMessage.setToUser(openId);
        List<WxMpTemplateData> data = new ArrayList<>();
        data.add(new WxMpTemplateData("first", phone));
        data.add(new WxMpTemplateData("keyword1", SimpleDateFormatUtils.getFormatStrByPatternAndDate(loginTime)));
        data.add(new WxMpTemplateData("keyword2", loginIp));
        data.add(new WxMpTemplateData("keyword3", equipment));
        wxMpTemplateMessage.setData(data);
        try {
            String appId = wxMpProperties.getConfigs().get(0).getAppId();
            WxMpTemplateMsgService templateMsgService = WxMpConfiguration.getMpServices().get(appId).getTemplateMsgService();
            templateMsgService.sendTemplateMsg(wxMpTemplateMessage);
            return setResultSuccess();
        } catch (Exception e) {
            return setResultError("发送失败");
        }

    }


}


