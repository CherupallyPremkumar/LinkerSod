package com.sod.doc.chatapp.configuration.dao;

import com.sod.doc.chatapp.model.domain.Chatapp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  public interface ChatappRepository extends JpaRepository<Chatapp,String> {}
