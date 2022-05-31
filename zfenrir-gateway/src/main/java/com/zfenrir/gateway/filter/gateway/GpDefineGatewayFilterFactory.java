package com.zfenrir.gateway.filter.gateway;

import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;

import java.nio.charset.StandardCharsets;

import reactor.core.publisher.Mono;
/**
 * 自定义路由级过滤器
 * @author zhuliang
 *
 * 2022-4-5
 */
@Component
public class GpDefineGatewayFilterFactory extends AbstractGatewayFilterFactory<GpDefineGatewayFilterFactory.GpConfig> {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    public GpDefineGatewayFilterFactory() {
        super(GpConfig.class);
    }
    
    public GatewayFilter apply(GpConfig config) {
        return ((exchange, chain) -> {
            pre(exchange, chain);
            if (CollectionUtils.isEmpty(exchange.getRequest().getQueryParams().get("auth"))) {
                ServerHttpResponse response = exchange.getResponse();
                JSONObject message = new JSONObject();
                message.put("status", -1);
                message.put("data", "鉴权失败");
                byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = response.bufferFactory().wrap(bits);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                //指定编码，否则在浏览器中会中文乱码
                response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
                return response.writeWith(Mono.just(buffer));
            }
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                post(exchange, chain);
            }));
        });
    }

    /**
     * 前置处理
     * @param exchange
     * @param chain
     */
    private  void  pre(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("pre  GpDefineGatewayFilterFactory");
    }
    
    
    /**
     * 后置处理
     * @param exchange
     * @param chain
     */
    private  void  post(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("post GpDefineGatewayFilterFactory");
    }
    
    public static class GpConfig {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
