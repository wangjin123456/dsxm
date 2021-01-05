package com.mayikt.api.member.api.service;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author taotao
 * @title: MemberUnionLoginService
 * @description； 项目
 * @date 2021/1/4 10:25
 */
@Api(tags="联合登录接口")
public interface MemberUnionLoginService {

    /**
     * 根据不同的联合登录id
     * @param unionPublicId
     * @return
     */
    @GetMapping("/unionLogin")
    BaseResponse<String> unionLogin(@RequestParam("unionPublicId") String unionPublicId);

    /**
     * 联合登录回调接口
     * @param unionPublicId
     * @return
     */

    @GetMapping("/login/oauth/callback")
    BaseResponse<JSONObject> unionLoginCallback(@RequestParam("unionPublicId") String unionPublicId);

}
