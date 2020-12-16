
package com.mayikt.api.member.service.weixin.impl.manage;

import com.mayikt.api.member.api.dto.resp.UserRespDto;
import com.mayikt.api.member.service.weixin.impl.feign.MemberInfoServiceFeign;
import com.mayikt.base.BaseResponse;
import com.mayikt.constant.HttpConstant;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
*@title: WxMpServiceManage
*@description； 项目
*@author taotao
*@date 2020/12/16 14:31
*/  

@Component
public class WxMpServiceManage {
    @Autowired
    private MemberInfoServiceFeign memberInfoServiceFeign;
    public WxMpXmlOutMessage handler(long userId,String openId){
        //根据userId查询是否已经关注
        BaseResponse<UserRespDto> userRespDtoBaseResponse=memberInfoServiceFeign.selectByOpenId(openId);
            if(userRespDtoBaseResponse.getCode().equals(HttpConstant.RPC_RESULT_SUCCESS)){
                  return  null;

            }

        //没关注的话update
             memberInfoServiceFeign.updateUseOpenId(userId,openId);
            return null;
    }
}
