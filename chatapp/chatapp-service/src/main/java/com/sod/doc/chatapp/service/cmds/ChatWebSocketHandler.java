package com.sod.doc.chatapp.service.cmds;

import com.sod.doc.chatapp.service.cmds.handler.HandleConnectionService;
import com.sod.doc.chatapp.service.cmds.handler.HandleUserConnectionService;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final HandleConnectionService handleConnectionService;
    private final HandleUserConnectionService handleUserConnectionService;
    public ChatWebSocketHandler(HandleConnectionService handleConnectionService, HandleUserConnectionService handleUserConnectionService) {
        this.handleConnectionService = handleConnectionService;
        this.handleUserConnectionService = handleUserConnectionService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        handleUserConnectionService.beforeConnection(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        handleUserConnectionService.afterConnection(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            handleConnectionService.invoke(session, message);
        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }
}