package com.sod.doc.chatapp.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.chenile.jpautils.entity.AbstractJpaStateEntity;

@Entity
@Table(name = "friends")
@Getter
@Setter
public class Friends extends AbstractJpaStateEntity {
    private String userId;
    private String friendId;


    public Friends(String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public Friends() {

    }
}
