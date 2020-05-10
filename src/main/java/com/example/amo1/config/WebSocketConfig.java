package com.example.amo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *  开启WebSocket支持
 * @author
 * @create 2019/01/06 13:06:36
 *
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {  
        return new ServerEndpointExporter();  
    } 
}
