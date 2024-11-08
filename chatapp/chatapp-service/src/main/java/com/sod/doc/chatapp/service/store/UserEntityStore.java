package com.sod.doc.chatapp.service.store;

import com.sod.doc.chatapp.configuration.dao.UserRepository;
import com.sod.doc.chatapp.model.domain.Chatapp;
import com.sod.doc.chatapp.model.domain.Users;
import org.chenile.base.exception.NotFoundException;
import org.chenile.utils.entity.service.EntityStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


public class UserEntityStore implements EntityStore<Users> {


    private final UserRepository userRepository;

    public UserEntityStore(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void store(Users entity) {
         userRepository.save(entity);
    }

    @Override
    public Users retrieve(String id) {
        Optional<Users> entity = userRepository.findByUsername(id);
        if (entity.isPresent())
            return entity.get();
        else
            throw new NotFoundException(1500,"Unable to find User with ID " + id);
    }

    public Users retrieveByEmail(String email) {
       Optional<Users> users= userRepository.findByEmail(email);
        return users.orElse(null);
    }
}
