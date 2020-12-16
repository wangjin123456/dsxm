package com.mayikt.api.member.api.service;

import com.alibaba.fastjson.JSONPObject;
import com.mayikt.api.member.api.dto.req.UserReqRegisterDto;
import com.mayikt.base.BaseResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author taotao
 * @title: MemberRegisterService
 * @description； 项目
 * @date 2020/12/10 10:46
 */
@Api(tags="会员注册服务接口")
public interface MemberRegisterService {
    /**
     * 注册接口
     * @return
     */
    @PostMapping("/register")
    BaseResponse<JSONPObject> register(@RequestBody UserReqRegisterDto userReqRegisterDto);

}
