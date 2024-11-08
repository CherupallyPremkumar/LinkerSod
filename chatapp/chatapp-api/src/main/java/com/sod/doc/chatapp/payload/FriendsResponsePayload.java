package com.sod.doc.chatapp.payload;

import com.sod.doc.chatapp.model.domain.Friends;

import java.util.List;

public class FriendsResponsePayload {
    String type;
    List<Friends> friends;

    public List<Friends> getFriends() {
        return friends;
    }

    public void setFriends(List<Friends> friends) {
        this.friends = friends;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
