package com.sod.doc.chatapp.service.store;

import org.chenile.utils.entity.service.EntityStore;
import com.sod.doc.chatapp.model.domain.Chatapp;
import org.springframework.beans.factory.annotation.Autowired;
import org.chenile.base.exception.NotFoundException;
import com.sod.doc.chatapp.configuration.dao.ChatappRepository;
import java.util.Optional;

public class ChatappEntityStore implements EntityStore<Chatapp>{
    @Autowired private ChatappRepository chatappRepository;

	@Override
	public void store(Chatapp entity) {
        chatappRepository.save(entity);
	}

	@Override
	public Chatapp retrieve(String id) {
        Optional<Chatapp> entity = chatappRepository.findById(id);
        if (entity.isPresent()) return entity.get();
        throw new NotFoundException(1500,"Unable to find Chatapp with ID " + id);
	}


}
