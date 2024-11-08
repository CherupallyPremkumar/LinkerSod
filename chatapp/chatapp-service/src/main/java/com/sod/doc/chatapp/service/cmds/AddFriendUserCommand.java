package com.sod.doc.chatapp.service.cmds;

import com.sod.doc.chatapp.model.domain.Chatapp;
import com.sod.doc.chatapp.model.domain.Friends;
import com.sod.doc.chatapp.payload.AddFriendUserPayload;
import com.sod.doc.chatapp.service.AddFriendUserService;
import com.sod.doc.chatapp.service.cmds.impl.AddFriendUserServiceImpl;
import org.chenile.stm.STMInternalTransitionInvoker;
import org.chenile.stm.State;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.model.Transition;

public class AddFriendUserCommand implements STMTransitionAction<Chatapp> {
    private final AddFriendUserService addFriendUserService;

    public AddFriendUserCommand(AddFriendUserService addFriendUserService) {
        this.addFriendUserService = addFriendUserService;
    }

    @Override
    public void doTransition(Chatapp chatApp, Object param, State startState, String eventId, State endState, STMInternalTransitionInvoker<?> stm, Transition transition) throws Exception {
        if(param instanceof AddFriendUserPayload payload){
           Friends friends= addFriendUserService.addFriendUser(payload);
           chatApp.getFriends().add(friends);
        }
    }
}
