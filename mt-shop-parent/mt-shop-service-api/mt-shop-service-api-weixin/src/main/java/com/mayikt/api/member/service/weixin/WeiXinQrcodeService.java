package com.mayikt.api.member.service.weixin;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author taotao
 * @title: WeiXinQrcodeService
 * @description； 项目
 * @date 2020/12/16 12:30
 */
@Api(tags="根据userId生成微信二维码连接")
public interface WeiXinQrcodeService {

    /**
     * 根据userId 生成连接
     *
     * @param userId
     * @param
     * @return
     */
  @GetMapping("/getQrUrl")
    BaseResponse<JSONObject> getQrUrl(@RequestParam("userId")Long userId);
}
