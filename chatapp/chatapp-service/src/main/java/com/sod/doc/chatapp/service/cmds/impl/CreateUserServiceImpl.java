package com.sod.doc.chatapp.service.cmds.impl;

import com.sod.doc.chatapp.exception.UserServiceException;
import com.sod.doc.chatapp.state.UserState;
import com.sod.doc.chatapp.model.domain.Users;
import com.sod.doc.chatapp.payload.CreateUserPayload;
import com.sod.doc.chatapp.service.CreateUserService;
import com.sod.doc.chatapp.service.store.UserEntityStore;
import org.chenile.stm.State;


public class CreateUserServiceImpl implements CreateUserService {


    private final UserEntityStore userEntityStore;

    public CreateUserServiceImpl(UserEntityStore userEntityStore) {

        this.userEntityStore = userEntityStore;
    }

    @Override
    public Users createUser(CreateUserPayload user) {
        this.validateBeforeStore(user);
        Users user1 = new Users();
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setUsername(user.getUsername());
        user1.setAvatarUrl(user.getAvatarUrl());
        user1.setFullName(user.getFullName());
        State state = new State();
        state.setStateId(UserState.ACTIVE.toString());
        state.setFlowId("users-flow");
        user1.setCurrentState(state);
        userEntityStore.store(user1);
        return user1;
    }

    private void validateBeforeStore(CreateUserPayload payload) {
        if (userEntityStore.retrieveByEmail(payload.getEmail()) != null) {
            throw new UserServiceException(500, 10000, "Email already exist");
        }
        ;
    }
}
