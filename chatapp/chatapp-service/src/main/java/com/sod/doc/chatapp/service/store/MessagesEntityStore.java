package com.sod.doc.chatapp.service.store;

import com.sod.doc.chatapp.configuration.dao.MessagesRepository;
import com.sod.doc.chatapp.model.domain.Message;
import org.chenile.utils.entity.service.EntityStore;
import org.springframework.data.jpa.repository.JpaRepository;

public class MessagesEntityStore implements EntityStore<Message> {

    private final MessagesRepository messagesRepository;

    public MessagesEntityStore(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public void store(Message entity) {
      messagesRepository.save(entity);
    }

    @Override
    public Message retrieve(String id) {
       return messagesRepository.findById(id).orElse(null);
    }
}
