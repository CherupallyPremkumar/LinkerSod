package com.sod.doc.chatapp.payload;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class LoginPayload {
    private String email;
    private String password;

    public LoginPayload() {
    }
}
