package com.sod.doc.chatapp.configuration.dao;


import com.sod.doc.chatapp.model.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Message, String> {

}
