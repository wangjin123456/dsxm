
package com.mayikt.api.member.service.impl;

import com.mayikt.api.member.api.dto.req.UserLoginDto;
import com.mayikt.api.member.api.dto.resp.UserRespDto;
import com.mayikt.api.member.api.service.MemberInfoService;
import com.mayikt.api.member.service.entitydo.UserDo;
import com.mayikt.api.member.service.mapper.UserMapper;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.bean.MeiteBeanUtils;
import com.mayikt.utils.DesensitizationUtil;
import com.mayikt.utils.TokenUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author taotao
 * @title: MemberInfoServiceImpl
 * @description； 项目
 * @date 2020/12/10 17:06
 */
@RestController
public class MemberInfoServiceImpl extends BaseApiService implements MemberInfoService {
    @Autowired
    private TokenUtils tokenUtils;
     @Autowired
     private UserMapper userMapper;
    @Override
    public BaseResponse<UserLoginDto> getTokenUser(String token) {
        if(Strings.isBlank(token)){
            return  setResultError("token不能为空");
        }
        //从redis获取userID
        String redisValue = tokenUtils.getTokenValue(token);
        if(Strings.isBlank(redisValue)){
            return  setResultError("token已经过期或者错误");
        }
        //在根据userId 查询用户信息
        long userId = Long.parseLong(redisValue);
        UserDo userDo=userMapper.findByUser(userId);
        if(userDo==null){
            return  setResultError("token已经过期或者错误！");
        }
        UserRespDto userRespDto= MeiteBeanUtils.doToDto(userDo,UserRespDto.class);
          String mobile=userRespDto.getMobile();
          userRespDto.setMobile(DesensitizationUtil.mobileEncrypt(mobile ));
        //
        return setResultSuccess(userRespDto);
    }
}
