package com.sod.doc.chatapp.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.chenile.jpautils.entity.AbstractJpaStateEntity;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@Getter
@Setter
public class Users extends AbstractJpaStateEntity {


    private String username;
    private String password;
    private String email;
    private String fullName;
    private String avatarUrl;



    public Users() {

    }


}
