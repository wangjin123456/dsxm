package com.mayikt.api.member.api.service;

import com.mayikt.api.member.api.dto.req.UserLoginDto;
import com.mayikt.base.BaseResponse;
import feign.Param;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author taotao
 * @title: MemberInfoService
 * @description； 项目
 * @date 2020/12/10 17:00
 */
@Api(tags = "会员服务基本信息接口")
public interface MemberInfoService {
    /**
     * 根据用户token查看用户信息
     * @param token
     * @return
     */
    @GetMapping("getTokenUser")
    BaseResponse<UserLoginDto> getTokenUser(@RequestParam("token") String token);
}
