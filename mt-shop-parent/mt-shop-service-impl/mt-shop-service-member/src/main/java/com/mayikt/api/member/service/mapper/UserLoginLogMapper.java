package com.mayikt.api.member.service.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mayikt.api.member.service.entitydo.UserDo;
import com.mayikt.api.member.service.entitydo.UserLoginLog;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: UserLoginLogMapper
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1218:33
 */
public interface UserLoginLogMapper  extends BaseMapper<UserLoginLog> {

    @Select("\n" +
            "SELECT *  FROM user_login_log WHERE channel =#{channel} and user_id=#{userId}\n " +
            "  and is_availability=1;")
    UserLoginLog selectByUserIdAndLoginType(@Param("userId") Long userId, @Param("channel") String channel);

    @Update("update user_login_log set is_availability=0 where login_token=#{loginToken};")
    int updateUserTokenNotQuipment(@Param("loginToken") String loginToken);

    @Insert("\n" +
            "insert into  user_login_log values(null,#{user_id},now(),#{login_token},#{channel},#{equipment},1);\n")
    int insertUserLoginLog(UserLoginLog userLoginLogDo);
}
