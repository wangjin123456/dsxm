package com.mayikt.api.member.api.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author taotao
 * @title: MemberService
 * @description； 项目
 * @date 2020/12/2 11:21
 */
@Api(tags="会员基本服务接口")
public interface MemberService {
    @GetMapping("/memberToWeiXin")
    @ApiOperation("会员调用微信接口接口")
    @ApiImplicitParam(name = "userId", value = "用户的id", required = true)
    @ApiResponse(code = 200, message = "响应成功")
    String memberToWeiXin(Long userId);
}
