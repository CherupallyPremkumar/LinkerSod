package com.sod.doc.chatapp.configuration.dao;

import com.sod.doc.chatapp.model.domain.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends, String> {
    List<Friends> findByUserId(String userId);
}
