package com.sod.doc.chatapp.service.cmds.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sod.doc.chatapp.exception.UserServiceException;
import com.sod.doc.chatapp.model.domain.Friends;
import com.sod.doc.chatapp.model.domain.Message;

import com.sod.doc.chatapp.payload.FriendsResponsePayload;
import com.sod.doc.chatapp.payload.MessageResponseMessage;
import com.sod.doc.chatapp.payload.RequestPayload;
import com.sod.doc.chatapp.service.store.FriendsEntityStore;
import com.sod.doc.chatapp.state.ContentType;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class HandleConnectionService {

    private final FriendsEntityStore friendsEntityStore;
    private final SessionManagementService sessionManagementService;
    private final ObjectMapper objectMapper;

    public HandleConnectionService(FriendsEntityStore friendsEntityStore, SessionManagementService sessionManagementService, ObjectMapper objectMapper) {
        this.friendsEntityStore = friendsEntityStore;
        this.sessionManagementService = sessionManagementService;
        this.objectMapper = objectMapper;
    }

    public List<Friends> getConnectedFriends(String userId) {
        return friendsEntityStore.getByUserId(userId);
    }

    public void invoke(WebSocketSession session, TextMessage message) throws Exception {
        if (session.isOpen()) {
            try {
                RequestPayload requestPayload = objectMapper.readValue(message.getPayload(), RequestPayload.class);
                if (requestPayload == null) throw new UserServiceException(10001,"Request payload is null");

                switch (requestPayload.getType()) {
                    case "getFriends" -> handleGetFriendsRequest(session);
                    case "message" -> handleMessageRequest(session, requestPayload);
                    default -> System.out.println("Unsupported request type: " + requestPayload.getType());
                }
            } catch (UserServiceException | IOException e) {
                System.err.println("Error processing message: " + e.getMessage());
            }
        }
    }

    private void handleGetFriendsRequest(WebSocketSession session) throws Exception {
        String userId = (String) session.getAttributes().get("userId");
        List<Friends> connectedFriends = getConnectedFriends(userId);
        FriendsResponsePayload responsePayload = new FriendsResponsePayload();
        responsePayload.setType("friendsList");
        responsePayload.setFriends(connectedFriends);

        String responseJson = objectMapper.writeValueAsString(responsePayload);
        sessionManagementService.sendMessage(session, responseJson);
        System.out.println("Sent friends list: " + responseJson);
    }

    public void handleMessageRequest(WebSocketSession senderSession, RequestPayload requestPayload) throws Exception {
        String recipientUserId = requestPayload.getTo();
        String messageContent = requestPayload.getMessage();

        if (recipientUserId == null || messageContent == null) {
            throw new UserServiceException(10000,"Invalid message request: recipient or message content is null");
        }

        Message messages = new Message();
        messages.setContent_type(ContentType.TEXT);
        messages.setContent(messageContent);
        messages.setReceivedAt(new Date());
        messages.setReceiverId(senderSession.getId());

        MessageResponseMessage responseMessage = new MessageResponseMessage();
        responseMessage.setType("message");
        responseMessage.setMessages(List.of(messages));

        sendToRecipientSession(recipientUserId, responseMessage);
    }

    private void sendToRecipientSession(String recipientUserId, MessageResponseMessage responseMessage) throws Exception {
        for (WebSocketSession session : sessionManagementService.getSessions()) {
            if (Objects.equals(recipientUserId, session.getAttributes().get("userId"))) {
                String responseJson = objectMapper.writeValueAsString(responseMessage);
                sessionManagementService.sendMessage(session, responseJson);
                System.out.println("Sent message to user " + recipientUserId + ": " + responseJson);
            }
        }
    }
}