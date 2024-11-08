package com.sod.doc.chatapp.service;

import com.sod.doc.chatapp.configuration.dao.WebSocketConnectionRepository;
import com.sod.doc.chatapp.model.domain.WebSocketConnection;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;
import java.util.concurrent.BlockingQueue;

public class CustomStompSessionHandler extends StompSessionHandlerAdapter {

    private final WebSocketConnectionRepository webSocketConnectionRepository;
    private final BlockingQueue<String> messageQueue; // Used in tests to verify messages

    public CustomStompSessionHandler(WebSocketConnectionRepository webSocketConnectionRepository) {
        this.webSocketConnectionRepository = webSocketConnectionRepository;
        this.messageQueue = null; // No queue by default
    }

    // Overloaded constructor for testing to capture messages in a queue
    public CustomStompSessionHandler(WebSocketConnectionRepository webSocketConnectionRepository, BlockingQueue<String> messageQueue) {
        this.webSocketConnectionRepository = webSocketConnectionRepository;
        this.messageQueue = messageQueue;
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("Connected to WebSocket with session ID: " + session.getSessionId());

        // Save connection details
        WebSocketConnection webSocketConnection = webSocketConnectionRepository.findBySessionId(session.getSessionId());
        if (webSocketConnection == null) {
            webSocketConnection = new WebSocketConnection();
            webSocketConnection.setSessionId(session.getSessionId());
            webSocketConnection.setConnected(true);
            webSocketConnection.setClientIp(connectedHeaders.getReceiptId());
            webSocketConnectionRepository.save(webSocketConnection);
        }
    }

    @Override
    public void handleFrame(StompHeaders stompHeaders, Object payload) {
        String message = (String) payload;
        System.out.println("Received message: " + message);

        // For testing, store received messages in the messageQueue
        if (messageQueue != null) {
            messageQueue.offer(message);
        }
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return String.class; // Adjust based on message type (String in this case)
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        System.out.println("WebSocket error: " + exception.getMessage());

        // Update connection status in the database on error
        WebSocketConnection connection = webSocketConnectionRepository.findBySessionId(session.getSessionId());
        if (connection != null) {
            connection.setConnected(false);
            webSocketConnectionRepository.save(connection);
        }
    }

    // Handle other transport events, such as disconnect, if necessary
}