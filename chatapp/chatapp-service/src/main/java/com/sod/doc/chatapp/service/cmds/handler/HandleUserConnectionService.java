package com.sod.doc.chatapp.service.cmds.handler;

import com.sod.doc.chatapp.exception.UserServiceException;
import com.sod.doc.chatapp.model.domain.Users;
import com.sod.doc.chatapp.service.store.UserEntityStore;
import com.sod.doc.chatapp.state.UserState;
import org.springframework.web.socket.WebSocketSession;

public class HandleUserConnectionService {

    private final UserEntityStore userEntityStore;
    private final SessionManagementService sessionManagementService;

    public HandleUserConnectionService(UserEntityStore userEntityStore, SessionManagementService sessionManagementService) {
        this.userEntityStore = userEntityStore;
        this.sessionManagementService = sessionManagementService;
    }

    private String getSessionId(WebSocketSession session) {
        return (String) session.getAttributes().get("userId");
    }

    public void beforeConnection(WebSocketSession socketSession) {
        Users users = userEntityStore.retrieve(getSessionId(socketSession));
        if (users != null) {
            if (users.getCurrentState().getStateId().equals(UserState.ACTIVE.toString())) {
                sessionManagementService.storeSession(socketSession);
                return;
            }
        }
        throw new UserServiceException(500, "User isn't active");
    }

    public void afterConnection(WebSocketSession socketSession) {
        Users users = userEntityStore.retrieve(getSessionId(socketSession));
        if (users != null) {
            if (users.getCurrentState().getStateId().equals(UserState.ACTIVE.toString())) {
                sessionManagementService.removeSession(socketSession);
                return; // Ensure that this return statement is included
            }
        }
        throw new UserServiceException(500, "User isn't active");
    }
}