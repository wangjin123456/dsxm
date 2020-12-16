package com.mayikt.api.member.service.fegin;

import com.mayikt.api.member.service.weixin.WeiXinService;

import org.springframework.cloud.openfeign.FeignClient;



/**
 * @author taotao
 * @title: WeiXinServiceFeign
 * @description； 项目
 * @date 2020/12/1 12:02
 */

@FeignClient(name="mayikt-weixin")
public interface WeiXinServiceFeign  extends WeiXinService {

/*    @Override
    @GetMapping("appinfo")
    String appinfo(@RequestParam("userId") long userId );*/

}
