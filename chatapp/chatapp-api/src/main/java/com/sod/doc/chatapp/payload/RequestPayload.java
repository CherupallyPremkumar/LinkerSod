package com.sod.doc.chatapp.payload;

public class RequestPayload {
    String type; // e.g., 'message' or 'userList'
    String from; // Sender's username (for messages)
    String to; // Recipient's username (for messages)
    String message; // The actual message content

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
