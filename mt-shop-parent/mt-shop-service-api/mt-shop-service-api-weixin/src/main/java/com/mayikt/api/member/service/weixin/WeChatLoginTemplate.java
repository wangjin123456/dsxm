
package com.mayikt.api.member.service.weixin;

import com.mayikt.api.member.service.request.LoginTemplateDto;
import com.mayikt.base.BaseResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
*@title: WeChatLoginTemplate
*@description； 项目
*@author taotao
*@date 2020/12/16 15:39
*/  
@Api(tags="微信模板消息推送")
public interface WeChatLoginTemplate {
    @PostMapping("/sendLoginTemplate")
    BaseResponse<Object> sendLoginTemplate(@RequestBody LoginTemplateDto loginTemplateDto);
}