
package com.mayikt.api.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.member.api.dto.req.UserLoginDto;
import com.mayikt.api.member.api.service.MemberLoginService;
import com.mayikt.api.member.service.entitydo.UserDo;

import com.mayikt.api.member.service.manage.AsyncLoginManage;
import com.mayikt.api.member.service.mapper.UserLoginLogMapper;
import com.mayikt.api.member.service.mapper.UserMapper;
import com.mayikt.api.member.service.utils.ChannelUtils;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.utils.MD5Util;
import com.mayikt.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author taotao
 * @title: MemberLoginServiceImpl
 * @description； 项目
 * @date 2020/12/10 15:38
 */
@RestController
@Slf4j
public class MemberLoginServiceImpl extends BaseApiService implements MemberLoginService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AsyncLoginManage asyncLoginManage;

    @Autowired
    private TokenUtils tokenUtils;

    @Value("${spring.application.name}")
    private String applicationname;
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;

    @Autowired
    private ChannelUtils channelUtils;
    @Override
    public BaseResponse<JSONObject> login(UserLoginDto userLoginDto, String sourceIp
            , String channel, String deviceInfor
    ) {
        String mobile = userLoginDto.getMobile();

        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String passWord = userLoginDto.getPass_word();
        if (StringUtils.isEmpty(passWord)) {
            return setResultError("密码不能为空!");
        }
        String newPassword = MD5Util.MD5(passWord);
        UserDo loginUserdo = userMapper.login(mobile, newPassword);
        if (loginUserdo == null) {
            return setResultError("手机号码或者密码不正确");
        }
        //设备信息
        if (!channelUtils.existChannel(channel)) {
            return setResultError("渠道来源错误!");
        }
        String userToken;


        long userId = loginUserdo.getUserId();
        String name = applicationname.replace("-", "");


            userToken = tokenUtils.createToken(name, userId + "");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userToken", userToken);
        log.info(Thread.currentThread().getName() + ",userLoginLogDo:" + ",流程1");

        new Thread(new Runnable() {
            @Override
            public void run() {



                    asyncLoginManage.loginLog(userId, sourceIp, new Date(), userToken, channel, deviceInfor);

            }
        }).start();

        log.info(Thread.currentThread().getName() + ",userLoginLogDo:" + ",流程3");
        return setResultSuccess(jsonObject);

    }


}
