package com.sod.doc.chatapp.service;

import com.sod.doc.chatapp.model.domain.Friends;
import com.sod.doc.chatapp.model.domain.Users;
import com.sod.doc.chatapp.payload.AddFriendUserPayload;

public interface AddFriendUserService {
    public Friends addFriendUser(AddFriendUserPayload users);
}
