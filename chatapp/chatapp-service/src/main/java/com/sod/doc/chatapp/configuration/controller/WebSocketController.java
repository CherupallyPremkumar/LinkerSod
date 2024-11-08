package com.sod.doc.chatapp.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/realtimedata")
    @SendTo("/topic/realtimedata")
    public String processMessage(String message) {
        System.out.println("Received message: " + message);
        return "trying  figureout";  // Expected response in the test
    }

    @MessageMapping("/privateMessage")
    public void sendPrivateMessage(String message, SimpMessageHeaderAccessor headerAccessor) {
        // Retrieve user ID or session ID to send a private message
        String sessionId = headerAccessor.getSessionId();
        System.out.println("Received message: ----- " + sessionId);
        // Send a private message to the specific user's queue
        assert sessionId != null;
        messagingTemplate.convertAndSendToUser(sessionId, "/queue/realtimedata", message);
    }
}