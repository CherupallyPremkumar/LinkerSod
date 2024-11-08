package com.sod.doc.chatapp.service.cmds.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SessionManagementService {
    private final List<WebSocketSession> sessions = Collections.synchronizedList(new ArrayList<>());

    public void storeSession(WebSocketSession socketSession) {
        sessions.add(socketSession);
    }

    public void removeSession(WebSocketSession socketSession) {
        sessions.remove(socketSession);
    }

    public List<WebSocketSession> getSessions() {
        return sessions;
    }

    public void sendMessage(WebSocketSession toSession, String message) throws Exception {
        if (toSession.isOpen()) {
            toSession.sendMessage(new TextMessage(message));
        }
    }

    public boolean checkSession(WebSocketSession socketSession) {
        return sessions.contains(socketSession);
    }
}