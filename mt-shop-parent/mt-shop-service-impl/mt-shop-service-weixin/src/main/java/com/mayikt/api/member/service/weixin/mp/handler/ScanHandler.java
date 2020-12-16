package com.mayikt.api.member.service.weixin.mp.handler;

import java.util.Map;

import com.mayikt.api.member.service.weixin.impl.manage.WxMpServiceManage;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class ScanHandler extends AbstractHandler {
@Autowired
private WxMpServiceManage wxMpServiceManage;
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        // 扫码事件处理

        String eventKey=wxMpXmlMessage.getEventKey();


        if(!Strings.isBlank(eventKey)){
            String qrscene= eventKey.replace("qrscene_","");
            long userId= Long.parseLong(qrscene);
            //根据userId查询是否已经关注
            String openId=wxMpXmlMessage.getFromUser();
            wxMpServiceManage.handler(userId,openId);
            //先genju openID查询是否已经关联

        }

        return null;
    }
}
