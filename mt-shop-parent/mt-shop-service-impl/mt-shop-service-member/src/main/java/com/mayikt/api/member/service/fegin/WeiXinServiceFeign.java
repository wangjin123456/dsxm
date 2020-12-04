package com.mayikt.api.member.service.fegin;

import com.mayikt.api.member.service.weixin.WeiXinService;
import com.mayikt.base.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
