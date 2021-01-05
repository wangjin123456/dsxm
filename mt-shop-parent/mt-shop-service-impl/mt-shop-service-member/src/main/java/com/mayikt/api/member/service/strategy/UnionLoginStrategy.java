package com.mayikt.api.member.service.strategy;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseResponse;

/**
 * @author taotao
 * @title: UnionLoginStrategy
 * @description； 项目
 * @date 2021/1/4 12:39
 */

public interface UnionLoginStrategy {
   String unionLoginCallback(String unionPublicId);

}