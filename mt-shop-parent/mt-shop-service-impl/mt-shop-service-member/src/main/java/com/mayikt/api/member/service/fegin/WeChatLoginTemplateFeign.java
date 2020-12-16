package com.mayikt.api.member.service.fegin;

import com.mayikt.api.member.service.weixin.WeChatLoginTemplate;
import com.mayikt.base.BaseResponse;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * @author taotao
 * @title: WeChatLoginTemplateFeign
 * @description； 项目
 * @date 2020/12/16 16:42
 */

@FeignClient(name="mayikt-weixin")
public interface WeChatLoginTemplateFeign  extends WeChatLoginTemplate {
}
