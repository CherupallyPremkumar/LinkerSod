package com.sod.doc.chatapp.service.cmds.impl;

import com.sod.doc.chatapp.exception.UserServiceException;
import com.sod.doc.chatapp.model.domain.Friends;
import com.sod.doc.chatapp.state.FriendsState;
import com.sod.doc.chatapp.state.UserState;
import com.sod.doc.chatapp.model.domain.Users;
import com.sod.doc.chatapp.payload.AddFriendUserPayload;
import com.sod.doc.chatapp.service.AddFriendUserService;
import com.sod.doc.chatapp.service.store.FriendsEntityStore;
import com.sod.doc.chatapp.service.store.UserEntityStore;
import org.chenile.stm.State;

public class AddFriendUserServiceImpl implements AddFriendUserService {

    private final UserEntityStore userEntityStore;

    private final FriendsEntityStore friendsEntityStore;

    public AddFriendUserServiceImpl(UserEntityStore userEntityStore, FriendsEntityStore friendsEntityStore) {
        this.userEntityStore = userEntityStore;
        this.friendsEntityStore = friendsEntityStore;
    }

    @Override
    public Friends addFriendUser(AddFriendUserPayload payload) {
        this.validateBeforeAdding(payload);
        Users requestedUser = userEntityStore.retrieve(payload.getUserId());
        Users approveUser = userEntityStore.retrieve(payload.getFriendId());
        Friends friends = new Friends();
        if (requestedUser != null && approveUser != null) {
            String cState = approveUser.getCurrentState().getStateId();
            String aState = requestedUser.getCurrentState().getStateId();
            if (cState.equals(UserState.ACTIVE.toString()) && aState.equals(UserState.ACTIVE.toString())) {
                this.makeFriends(friends, payload);
            }
        }
        return friends;
    }

    private void makeFriends(Friends friends, AddFriendUserPayload payload) {
        friends.setUserId(payload.getUserId());
        friends.setFriendId(payload.getFriendId());
        friends.setCurrentState(new State(FriendsState.REQUESTED.toString(), "friends-flow"));
    }

    private void validateBeforeAdding(AddFriendUserPayload payload) {
        if (payload.getFriendId() == null || payload.getFriendId().isEmpty() || payload.getUserId() == null || payload.getUserId().isEmpty()) {
            throw new UserServiceException(400, 10001, "One of the value is null or empty");
        }
    }
}
