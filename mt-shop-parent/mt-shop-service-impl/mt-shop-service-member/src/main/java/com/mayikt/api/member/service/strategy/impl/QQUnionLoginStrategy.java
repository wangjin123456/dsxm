
package com.mayikt.api.member.service.strategy.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.member.service.strategy.UnionLoginStrategy;
import com.mayikt.base.BaseResponse;
import org.springframework.stereotype.Component;

/**
*@title: UnionLoginStrategyImpl4
*@description； 项目
*@author taotao
*@date 2021/1/4 12:40
*/  
@Component
public class QQUnionLoginStrategy implements UnionLoginStrategy {
    @Override
    public String unionLoginCallback(String unionPublicId) {
        System.out.println("进入qq");
        return null;
    }
}
