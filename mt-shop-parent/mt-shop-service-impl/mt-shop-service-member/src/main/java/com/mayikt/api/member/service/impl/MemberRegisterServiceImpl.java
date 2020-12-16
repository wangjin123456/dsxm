
package com.mayikt.api.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mayikt.api.member.service.entitydo.UserDo;
import com.mayikt.api.member.service.mapper.UserMapper;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.alibaba.fastjson.JSONPObject;
import com.mayikt.api.member.api.dto.req.UserReqRegisterDto;
import com.mayikt.api.member.api.service.MemberRegisterService;
import com.mayikt.base.BaseResponse;
import com.mayikt.bean.MeiteBeanUtils;
import com.mayikt.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author taotao
 * @title: MemberRegisterServiceImpl
 * @description； 项目
 * @date 2020/12/10 10:50
 */
@RestController
public class MemberRegisterServiceImpl extends BaseApiService implements MemberRegisterService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponse<JSONPObject> register(@RequestBody UserReqRegisterDto userReqRegisterDto) {
        String mobile = userReqRegisterDto.getMobile();
        if (StringUtils.isAllBlank(mobile)) {
            return setResultError("mobile参数不能为空!");
        }
        String passworld = userReqRegisterDto.getPass_word();
        if (StringUtils.isAllBlank(passworld)) {
            return setResultError("password参数不能为空!");
        }
        //先检查号码是否存在
        UserDo userDbDo = userMapper.existMobile(mobile);
        if (userDbDo != null) {
            return setResultError("该手机号码已经存在");
        }
        String newpassword= MD5Util.MD5(passworld);
        userReqRegisterDto.setPass_word(newpassword);
        //dto转换：
        UserDo userDo = dtoDo(userReqRegisterDto, UserDo.class);
        System.out.println(userDo.toString()+"**");
        int register = userMapper.insert(userDo);
        //插入数据库
        //密码加密md5
        return setResult(register,"注册成功","注册失败");
    }

}
