package com.sod.doc.chatapp;

import com.sod.doc.chatapp.configuration.dao.WebSocketConnectionRepository;
import com.sod.doc.chatapp.model.domain.WebSocketConnection;
import com.sod.doc.chatapp.service.CustomStompSessionHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.Arrays.asList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebSocketTwoClientsIntegrationTest {

    @LocalServerPort
    private Integer port;
    static final String WEBSOCKET_ENDPOINT = "/app/sendMessage";
    static final String WEBSOCKET_PRIVATE = "/queue/sendMessage";

    @Autowired
    private WebSocketConnectionRepository connectionRepository;

    private WebSocketStompClient stompClient;
    private WebSocketStompClient stompClient2;
    private StompSession session;
    private StompSession session2;
    private BlockingQueue<String> messageQueue;

    private String getWsPath() {
        return String.format("ws://localhost:%d/realtimedata", port);
    }

    @BeforeEach
    public void setup() throws InterruptedException, ExecutionException, TimeoutException {
        stompClient = new WebSocketStompClient(new SockJsClient(asList(new WebSocketTransport(new StandardWebSocketClient()))));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        // Initialize a message queue for capturing messages
        messageQueue = new LinkedBlockingDeque<>();

        // Set up session 1 and session 2 with custom handlers
        session = stompClient.connectAsync(getWsPath(), new CustomStompSessionHandler(connectionRepository)).get(1, TimeUnit.SECONDS);
        stompClient2 = new WebSocketStompClient(new SockJsClient(List.of(new WebSocketTransport(new StandardWebSocketClient()))));
        stompClient2.setMessageConverter(new MappingJackson2MessageConverter());
        session2 = stompClient2.connectAsync(getWsPath(), new CustomStompSessionHandler(connectionRepository, messageQueue)).get(1, TimeUnit.SECONDS);

        // Subscribe session2 to listen for private messages
        session2.subscribe(WEBSOCKET_PRIVATE, new CustomStompSessionHandler(connectionRepository, messageQueue));

        if (session == null || session2 == null) {
            throw new RuntimeException("Failed to establish WebSocket sessions");
        }

        Thread.sleep(2000); // Allow some time for connection setup
    }

    @Test
    public void testConnectionDetailsStoredInDB() {
        String sessionId = session.getSessionId();
        WebSocketConnection connection = connectionRepository.findBySessionId(sessionId);
        Assertions.assertNotNull(connection, "Connection details should be stored in the database");
        Assertions.assertTrue(connection.isConnected(), "Connection should be marked as connected");
    }

    @Test
    public void testSendMessageToSession() throws InterruptedException {
        String sessionId = session.getSessionId();
        WebSocketConnection connection = connectionRepository.findBySessionId(sessionId);

        // Session 1 sends a message to the private queue
        session.send(WEBSOCKET_PRIVATE, "Hi");

        // Check if session2 received the message
        String receivedMessage = messageQueue.poll(5, TimeUnit.SECONDS);
        Assertions.assertNotNull(receivedMessage, "Message should be received by session2");
        Assertions.assertEquals("Hi", receivedMessage, "The received message should match the sent message");
    }
}