package com.zfenrir.gateway.handler;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;

import java.nio.charset.StandardCharsets;

import reactor.core.publisher.Mono;
/**
 * 自定义限流处理器
 * @author zhuliang
 *
 * 2022-4-9
 */
public class GpSentinelGatewayBlockExceptionHandler implements WebExceptionHandler{

    private Mono<Void> writeResponse(ServerResponse response, ServerWebExchange exchange) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        serverHttpResponse.getHeaders()
        .add("Content-Type", "application/json;charset=UTF-8");;
        String message ="{\"code\":999,\"message\":\"请求过于频繁，请稍后充实\"}";
        return serverHttpResponse.writeWith(Mono.just(serverHttpResponse.bufferFactory().wrap(message.getBytes(StandardCharsets.UTF_8))));
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }
        // This exception handler only handles rejection by Sentinel.
        if (!BlockException.isBlockException(ex)) {
            return Mono.error(ex);
        }
        return handleBlockedRequest(exchange, ex)
            .flatMap(response -> writeResponse(response, exchange));
    }

    private Mono<ServerResponse> handleBlockedRequest(ServerWebExchange exchange, Throwable throwable) {
        return GatewayCallbackManager.getBlockHandler().handleRequest(exchange, throwable);
    }

}
