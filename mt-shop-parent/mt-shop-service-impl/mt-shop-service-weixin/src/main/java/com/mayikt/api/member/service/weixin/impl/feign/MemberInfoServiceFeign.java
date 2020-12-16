package com.mayikt.api.member.service.weixin.impl.feign;

import com.mayikt.api.member.api.service.MemberInfoService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author taotao
 * @title: MemberInfoServiceFeign
 * @description； 项目
 * @date 2020/12/16 14:55
 */
@FeignClient(name = "mayikt-member")
public interface MemberInfoServiceFeign extends MemberInfoService {
}
