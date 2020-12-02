package com.mayikt.api.member.service.fegin;

import com.mayikt.api.member.service.weixin.WeiXinService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;


/**
 * @author taotao
 * @title: WeiXinServiceFeign
 * @description； 项目
 * @date 2020/12/1 12:02
 */
@Component
@FeignClient(name="mayikt-weixin")
public interface WeiXinServiceFeign  extends WeiXinService {
   /* *//**
     * 微信接口
     * @return
     *//*
    @Override
    @GetMapping("appinfo")
    String appinfo(@RequestParam("userId") long userId );*/
}
