
package com.mayikt.swagger.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
*@title: MayiktGatewayFilter
*@description； 项目
*@author taotao
*@date 2020/12/14 8:55
*/  
@Component
public class MayiktGatewayFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
      String sourceIp= exchange.getRequest().getHeaders().getFirst("X-Real-IP");
        if (StringUtils.isEmpty(sourceIp)) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", "500");
            jsonObject.put("msg", "sourceIp is null");
            DataBuffer buffer = response.bufferFactory().wrap(jsonObject.toJSONString().getBytes());
            return response.writeWith(Mono.just(buffer));
        }
        // 使用网关过滤
        return chain.filter(exchange);

    }
}
