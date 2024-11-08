package com.sod.doc.chatapp.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class CreateUserPayload {

    private String email;
    private String username;
    private String password;
    private String fullName;
    private String avatarUrl;
    public CreateUserPayload() {
    }

}
