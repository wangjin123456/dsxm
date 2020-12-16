package com.mayikt.api.member.api.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mayikt.api.member.api.dto.resp.UserRespDto;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: MemberInfoService
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1222:23
 */
@Api(tags = "会员服务基本信息接口")
public interface MemberInfoService {

    /**
     * 根据用户token查询用户的信息
     *
     * @param token
     * @return
     */
    @GetMapping("/getTokenUser")
    @ApiOperation("根据token查看用户信息")
    @ApiImplicitParam(name = "token", value = "token", required = true)
    BaseResponse<UserRespDto> getTokenUser(@RequestParam("token") String token);

    /**
     * 关联用户的openid
     *
     * @param userId
     * @param openId
     * @return
     */
    @PostMapping("/updateUseOpenId")
    @ApiOperation("关联用户的openid")
    BaseResponse<Object> updateUseOpenId(@RequestParam("userId") Long userId,
                                         @RequestParam(name = "openId", required = false) String openId);

    /**
     * 根据openid  查询用户信息
     *
     * @param openId
     * @return
     */
    @GetMapping("/selectByOpenId")
    @ApiOperation("根据openid 查询用户信息")
    BaseResponse<UserRespDto> selectByOpenId(
            @RequestParam("openId") String openId);


    /**
     * 取消关注
     *
     * @param openId
     * @return
     */
    @GetMapping("/cancelFollowOpenId")
    BaseResponse<Object> cancelFollowOpenId(@RequestParam("openId") String openId);
}
