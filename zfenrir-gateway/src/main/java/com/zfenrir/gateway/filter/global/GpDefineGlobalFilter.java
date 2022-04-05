package com.zfenrir.gateway.filter.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class GpDefineGlobalFilter implements GlobalFilter, Ordered{
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        pre(exchange, chain);
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            post(exchange, chain);
        }));
    }

    
    @Override
    public int getOrder() {
        return 0;
    }
    
    /**
     * 前置处理
     * @param exchange
     * @param chain
     */
    private  void  pre(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("pre GpDefineGlobalFilter");
    }
    
    
    /**
     * 后置处理
     * @param exchange
     * @param chain
     */
    private  void  post(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("post GpDefineGlobalFilter");
    }
}
