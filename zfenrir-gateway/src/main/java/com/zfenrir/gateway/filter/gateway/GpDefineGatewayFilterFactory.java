package com.zfenrir.gateway.filter.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

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
