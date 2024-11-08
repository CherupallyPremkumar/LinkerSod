package com.sod.doc.chatapp.service;

import com.sod.doc.chatapp.model.domain.Users;
import com.sod.doc.chatapp.payload.CreateUserPayload;

public interface CreateUserService {

    public Users createUser(CreateUserPayload users);
}
