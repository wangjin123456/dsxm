
package com.mayikt.api.member.service.impl;

import com.mayikt.api.member.api.dto.resp.UserRespDto;
import com.mayikt.api.member.api.service.MemberInfoService;
import com.mayikt.api.member.service.entitydo.UserDo;
import com.mayikt.api.member.service.mapper.UserMapper;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.bean.MeiteBeanUtils;
import com.mayikt.utils.DesensitizationUtil;
import com.mayikt.utils.TokenUtils;
import org.apache.commons.lang.StringUtils;
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
    public BaseResponse<UserRespDto> getTokenUser(String token) {
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

    @Override
    public BaseResponse<Object> updateUseOpenId(Long userId, String openId) {
        int reuslt = userMapper.updateUseOpenId(userId, openId);
        return setResultDb(reuslt, "关联成功", "关联失败");
    }

    @Override
    public BaseResponse<UserRespDto> selectByOpenId(String openId) {
        UserDo userDo = userMapper.selectByOpenId(openId);
        if (userDo == null) {
            return setResultError("根据openId查询该用户没有关注过");
        }
        // 需要将do转换成dto
        UserRespDto userRespDto = MeiteBeanUtils.doToDto(userDo, UserRespDto.class);
        String mobile = userRespDto.getMobile();
        userRespDto.setMobile(DesensitizationUtil.mobileEncrypt(mobile));
        return setResultSuccess(userRespDto);
    }

    @Override
    public BaseResponse<Object> cancelFollowOpenId(String openId) {
        if (StringUtils.isEmpty(openId)) {
            return setResultError("openId不能为空");
        }
        UserDo userDo = userMapper.selectByOpenId(openId);
        if (userDo == null) {
            return setResultError("根据openId查询该用户没有关注过");
        }
        // 已经关注过，则将对应的用户的openid 变为空
        int result = userMapper.cancelFollowOpenId(openId);
        return setResultDb(result, "取消关注成功", "取消关注成功失败!");
    }
}
