package com.sod.doc.chatapp.configuration.dao;


import com.sod.doc.chatapp.model.domain.WebSocketConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebSocketConnectionRepository extends JpaRepository<WebSocketConnection, Long> {
    WebSocketConnection findBySessionId(String sessionId);
    // Custom query methods if needed
}
