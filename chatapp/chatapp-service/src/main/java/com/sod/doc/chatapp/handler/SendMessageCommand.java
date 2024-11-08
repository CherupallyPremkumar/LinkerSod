package com.sod.doc.chatapp.handler;

import com.sod.doc.chatapp.command.Command;
import com.sod.doc.chatapp.service.cmds.ChatWebSocketHandler;

public class SendMessageCommand implements Command {
    private ChatWebSocketHandler handler;
    private String message;
    private String recipient;

    public SendMessageCommand(ChatWebSocketHandler handler, String message, String recipient) {
        this.handler = handler;
        this.message = message;
        this.recipient = recipient;
    }

    @Override
    public void execute() {
        // Logic to send the message through the handler
    }
}
