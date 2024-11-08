package com.sod.doc.chatapp.service.cmds;


import com.sod.doc.chatapp.model.domain.Chatapp;
import com.sod.doc.chatapp.model.domain.Users;
import com.sod.doc.chatapp.payload.CreateUserPayload;
import com.sod.doc.chatapp.service.CreateUserService;
import org.chenile.stm.STMInternalTransitionInvoker;
import org.chenile.stm.State;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.model.Transition;


public class CreateUserInChatAppCommand implements STMTransitionAction<Chatapp> {


    private final CreateUserService createUserService;

    public CreateUserInChatAppCommand(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @Override
    public void doTransition(Chatapp chatapp, Object param, State startState, String eventId, State endState, STMInternalTransitionInvoker<?> stm, Transition transition) throws Exception {
        if(param instanceof CreateUserPayload payload) {
          Users users= createUserService.createUser(payload);
          chatapp.setUsers(users);
        }
    }




}

