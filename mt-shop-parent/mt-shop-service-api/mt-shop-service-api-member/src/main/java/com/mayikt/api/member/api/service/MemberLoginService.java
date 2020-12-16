package com.mayikt.api.member.api.service;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.member.api.dto.req.UserLoginDto;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author taotao
 * @title: MemberLoginService
 * @description； 项目
 * @date 2020/12/10 15:29
 */
@Api(tags = "会员登录接口")
public interface MemberLoginService  {

    @PostMapping("/login")
    BaseResponse<JSONObject> login(@RequestBody UserLoginDto userLoginDto, @RequestHeader("X-Real-IP")
            String sourceIp, @RequestHeader("channel") String channel, @RequestHeader("deviceInfor") String deviceInfor);

}
