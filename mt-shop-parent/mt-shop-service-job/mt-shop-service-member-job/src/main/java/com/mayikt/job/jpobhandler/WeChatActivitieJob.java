
package com.mayikt.job.jpobhandler;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mayikt.job.entitydo.UserDo;
import com.mayikt.job.mapper.UserMapper;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.util.ShardingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
*@title: WeChatActivitieJob
*@description； 项目
*@author taotao
*@date 2020/12/23 10:59
*/

@Component
@Slf4j
public class WeChatActivitieJob {

    @Autowired
    private UserMapper userMapper;
    @Value("${mayikt.member.job.WeChatActivitiePageSize}")
    private Integer pageSize;

    /**
     * 改任务的名称
     * @param param
     * @return
     */
    @XxlJob("weChatActivitieJobHandler")
    public ReturnT<String> weChatActivitieJobHandler(String param){
           try {
               // 获取分片的数量
               ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
               int index = shardingVO.getIndex();
               int startIndex=((index+1)-1)*pageSize;

               List<UserDo> pageResult = userMapper.selectByOpenIdNotIsNull(startIndex,pageSize);
               // log.info(">>>定时任务开始出发<<<param:{},index:{},pageSize:{}", param, index, pageSize);
            log.info("userDos:"+ JSONObject.toJSONString(pageResult));
           }catch (Exception e){
               System.out.println(e);
               e.getCause();
           }
        return  ReturnT.SUCCESS;
    }

}
