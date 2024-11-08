package com.sod.doc.chatapp.payload;

import com.sod.doc.chatapp.model.domain.Friends;
import com.sod.doc.chatapp.model.domain.Message;


import java.util.List;

public class MessageResponseMessage {
    String type;
    String chatId;
    List<Message> messages;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
