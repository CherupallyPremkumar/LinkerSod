package com.sod.doc.chatapp.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;
@Component
public class CustomHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // Extract user ID from request parameters
        String userId = request.getURI().getQuery(); // Extract user ID from the query string

        // Store user ID in session attributes
        attributes.put("userId", userId.substring(7)); // Adjust as necessary to extract the correct value
        return true; // Allow the handshake to proceed
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        // No additional action needed after handshake
    }
}