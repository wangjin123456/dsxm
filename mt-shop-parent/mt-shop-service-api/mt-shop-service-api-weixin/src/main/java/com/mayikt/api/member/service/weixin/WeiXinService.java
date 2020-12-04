package com.mayikt.api.member.service.weixin;


import com.mayikt.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author taotao
 * @title: WeiXinService
 * @description； 项目
 * @date 2020/12/1 11:21
 */
@Api(tags="微信基本服务接口")

public interface WeiXinService {

    /**
     * 微信接口
     * @return
     */
    @GetMapping("appinfo")
   @ApiOperation("appinfo接口")
    @ApiImplicitParam(name = "userId",value = "用户id",required = true)
    @ApiResponse(code = 200,message = "响应成功")
    String appinfo(@RequestParam("userId") long userId );

    /**
     * 添加我们的应用
     * @param appId
     * @param appPwd
     * @return
     */
   @GetMapping("/addApp")
     BaseResponse<String> addApp(@RequestParam("appId") String appId,@RequestParam("appPwd")  String appPwd);
}
