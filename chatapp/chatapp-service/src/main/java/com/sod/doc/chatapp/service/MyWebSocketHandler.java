package com.sod.doc.chatapp.service;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

public class MyWebSocketHandler extends WebSocketHandlerDecorator {

    public MyWebSocketHandler(WebSocketHandler delegate) {
        super(delegate);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // Logic to execute when WebSocket connection is established
        System.out.println("WebSocket connection established: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        // Logic to execute when WebSocket connection is closed
        System.out.println("WebSocket connection closed: " + session.getId());
        // You can also clean up or store details about the disconnection, such as session ID, time, reason, etc.
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // Logic for handling messages received via WebSocket
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // Logic to handle errors in the WebSocket connection
        System.out.println("Error in WebSocket connection: " + exception.getMessage());
    }
}