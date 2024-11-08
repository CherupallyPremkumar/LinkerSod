package com.sod.doc.chatapp.configuration;

import com.sod.doc.chatapp.service.MyWebSocketHandler;
import com.sod.doc.chatapp.service.cmds.ChatWebSocketHandler;
import com.sod.doc.chatapp.service.cmds.handler.HandleConnectionService;
import com.sod.doc.chatapp.service.cmds.handler.HandleUserConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

// by this class
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue");  // Topic for public, Queue for private
        config.setUserDestinationPrefix("/user");       // Prefix for private messages
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/realtimedata").withSockJS();
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        // Optional: Configure WebSocket transport if needed
    }
}