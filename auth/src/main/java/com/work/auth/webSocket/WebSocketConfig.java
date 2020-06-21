package com.work.auth.webSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * 首先注入一个ServerEndpointExporterBean,该Bean会自动注册使用@ServerEndpoint注解申明的websocket endpoint
 *
 * @author wanghao
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    /**
     * WebSocket 处理器
     */
    @Resource
    private MyWebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler,
                "/websocket/{ID}")
                .setAllowedOrigins("*")
                .addInterceptors(new WebSocketInterceptor());
    }
}