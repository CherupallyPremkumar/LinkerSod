package com.sod.doc.chatapp.configuration.dao;

import com.sod.doc.chatapp.model.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByEmail(String email);

    Optional<Users> findByUsername(String id);
}
