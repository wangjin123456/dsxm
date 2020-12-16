package com.mayikt.api.member.service.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mayikt.api.member.service.entitydo.UserDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @version V1.0
 * @description: 用户Mapper
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 * 私自分享视频和源码属于违法行为。
 */
@Mapper
public interface UserMapper  extends BaseMapper<UserDo> {

    @Insert("INSERT INTO   meite_user VALUES ('22','1', '1', NULL, 0, 0, now(), 1, NULL, NULL, NULL);")
    int register(@Param("mobile") String mobile);

    @Select("SELECT * from meite_user   where MOBILE=#{mobile}")
    UserDo login(String mobile, String passWord);

    @Select("SELECT * from meite_user  where MOBILE=#{mobile}")
    UserDo existMobile(String mobile);
    @Select("SELECT * from meite_user   where USER_ID=#{userId}")
    UserDo findByUser(Long userId);
}
