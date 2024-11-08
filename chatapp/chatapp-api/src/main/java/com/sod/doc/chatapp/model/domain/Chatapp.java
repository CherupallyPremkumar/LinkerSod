package com.sod.doc.chatapp.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.chenile.jpautils.entity.AbstractJpaStateEntity;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chatapp")
@Getter
@Setter
public class Chatapp extends AbstractJpaStateEntity{
    private static final long serialVersionUID = 1L;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users users;

    // Assuming a Chatapp can have multiple friends (either same entity or a different one)
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Friends> friends = new HashSet<>();

    // Assuming a Chatapp can have multiple messages
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Message> messages = new HashSet<>();


}
