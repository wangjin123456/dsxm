
package com.mayikt.api.member.service.manage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mayikt.api.member.service.entitydo.UserLoginLog;

import com.mayikt.api.member.service.fegin.WeChatLoginTemplateFeign;
import com.mayikt.api.member.service.mapper.UserLoginLogMapper;
import com.mayikt.api.member.service.request.LoginTemplateDto;
import com.mayikt.utils.DesensitizationUtil;
import com.mayikt.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author taotao
 * @title: AsyncLoginManage
 * @description； 项目
 * @date 2020/12/10 20:17
 */
@Component
@Slf4j
public class AsyncLoginManage {

    @Autowired
    private UserLoginLogMapper userLoginLogMapper;
    @Autowired
    private TokenUtils tokenUtils;
@Autowired
private WeChatLoginTemplateFeign weChatLoginTemplateFeign;
    @Async
    public void loginLLog(String openId, String mobile, Long user_id, String login_ip, Date login_time, String login_token, String channel, String equipment) {

    //   log.info( userLoginLogMapper.selectByUserIdAndLoginType(user_id, channel).toString()+"********");
        //1,根据当前的渠道+ 查询 该用户是否已经登陆过userid+可用
        QueryWrapper<UserLoginLog> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",user_id);
        queryWrapper.eq("channel",channel);
        queryWrapper.eq("is_availability",1);
        UserLoginLog userLoginLogDo = userLoginLogMapper.selectOne(queryWrapper);
        //2,如果没有查询到记录的情况下，
        if (userLoginLogDo != null) {
            String oldloginToken = userLoginLogDo.getLoginToken();
            //更新数据库的token状态
            userLoginLogMapper.updateUserTokenNotQuipment(oldloginToken);
            //从redis删除该token
            tokenUtils.delToken(oldloginToken);

        }

        //插入最新的token
        UserLoginLog newuserLoginLogDo = new UserLoginLog(user_id,  login_time, login_token, channel, equipment,1,login_ip);
        log.info(Thread.currentThread().getName() + ",userLoginLogDo:" + newuserLoginLogDo.toString() + ",流程2");
        userLoginLogMapper.insert(newuserLoginLogDo);
        // 3. 调用微信接口发送消息模版
        if (!StringUtils.isEmpty(openId)) {
            LoginTemplateDto loginTemplateDto = new
                    LoginTemplateDto(DesensitizationUtil.mobileEncrypt(mobile),
                    login_time, login_ip, equipment, openId);
            weChatLoginTemplateFeign.sendLoginTemplate(loginTemplateDto);
        }
        //4,關聯我們的openid




    }

}
