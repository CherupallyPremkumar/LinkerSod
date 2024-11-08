package com.sod.doc.chatapp.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class AddFriendUserPayload {
    private String userId;
    private String friendId;

    public AddFriendUserPayload() {
    }
}
