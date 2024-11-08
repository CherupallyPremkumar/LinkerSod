package com.sod.doc.chatapp.service.store;

import com.sod.doc.chatapp.configuration.dao.FriendsRepository;
import com.sod.doc.chatapp.model.domain.Friends;
import com.sod.doc.chatapp.model.domain.Users;
import org.chenile.base.exception.NotFoundException;
import org.chenile.utils.entity.service.EntityStore;

import java.util.List;
import java.util.Optional;

public class FriendsEntityStore implements EntityStore<Friends> {

    private final  FriendsRepository friendsRepository;

    public FriendsEntityStore(FriendsRepository friendsRepository) {
        this.friendsRepository = friendsRepository;
    }

    @Override
    public void store(Friends entity) {
        friendsRepository.save(entity);
    }

    @Override
    public Friends retrieve(String id) {
        Optional<Friends> entity = friendsRepository.findById(id);
        if (entity.isPresent()) return entity.get();
        throw new NotFoundException(1500,"Unable to find User with ID " + id);
    }

    public List<Friends> getByUserId(String userId) {
        List<Friends> friends=friendsRepository.findByUserId(userId);
        return friends;
    }
}
