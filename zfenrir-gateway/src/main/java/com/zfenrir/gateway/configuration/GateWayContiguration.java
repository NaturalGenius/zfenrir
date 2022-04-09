package com.zfenrir.gateway.configuration;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.zfenrir.gateway.handler.GpSentinelGatewayBlockExceptionHandler;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class GateWayContiguration {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter() ;
    }
    
    //注入限流处理器
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GpSentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new GpSentinelGatewayBlockExceptionHandler();
    }
    
   // @PostConstruct
    public void doinit() {
        initGatewayRules();
    }
    private void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        GatewayFlowRule gatewayFlowRule = new GatewayFlowRule("zfenrir-user-api").setCount(1).setIntervalSec(1);
        rules.add(gatewayFlowRule);
        GatewayRuleManager.loadRules(rules);
    }
}
