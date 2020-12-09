package com.mayikt.api.member.service.weixin.mp.handler;


import com.mayikt.api.member.service.weixin.impl.entity.WechatKeyword;
import com.mayikt.api.member.service.weixin.impl.mapper.KeywordMapper;
import com.mayikt.api.member.service.weixin.mp.builder.TextBuilder;
import com.mayikt.api.member.service.weixin.mp.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
@RefreshScope
public class MsgHandler extends AbstractHandler {
    @Value("${mayikt.wx.defaultMsg}")
    private String defaultMsg;
    @Autowired
    private KeywordMapper keywordMapper;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }


        //TODO 组装回复消息
        String content = wxMessage.getContent();
        //1,先匹配数据库，
       WechatKeyword wechatKeyword= keywordMapper.findByKeyword(content);
       if(wechatKeyword!=null){
           String keywordValue=wechatKeyword.getKeywordValue();
           return new TextBuilder().build(StringUtils.isAllBlank(keywordValue)?defaultMsg:keywordValue, wxMessage, weixinService);
       }
        //2查询调用第三方天气预报接口查询
        //3 回复默认消息

        return new TextBuilder().build(content, wxMessage, weixinService);

    }

}
