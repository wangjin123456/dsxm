package com.mayikt.api.member.service.weixin.impl.mapper;



import com.mayikt.api.member.service.weixin.impl.entity.WechatKeyword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: KeywordMapper
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1018:28
 */

public interface KeywordMapper {
    @Select("SELECT  id as id ,keyword_name as keywordname,\n" +
            "keyword_value as keywordvalue,create_time as createtime,\n" +
            "update_time as updatetime ,version  as version\n" +
            "  FROM wechat_keywords where keyword_name=#{keyword};")
    WechatKeyword findByKeyword(String keyword);
}
