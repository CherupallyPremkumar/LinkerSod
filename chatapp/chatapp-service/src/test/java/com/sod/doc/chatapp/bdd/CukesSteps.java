package com.sod.doc.chatapp.bdd;

import com.sod.doc.chatapp.configuration.dao.WebSocketConnectionRepository;
import com.sod.doc.chatapp.model.domain.WebSocketConnection;
import com.sod.doc.chatapp.service.CustomStompSessionHandler;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.ActiveProfiles;
import com.sod.doc.chatapp.SpringTestConfig;

import cucumber.api.java.en.Given;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

/**
* This "steps" file's purpose is to hook up the SpringConfig to the test case.
* It does not contain any methods currently but can be used for writing your own custom BDD steps
* if required. In most cases people don't need additional steps since cucumber-utils provides for
* most of the steps. <br/>
* This class requires a dummy method to keep Cucumber from erring out. (Cucumber needs at least
* one step in a steps file)<br/>
*/
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,classes = SpringTestConfig.class)
@AutoConfigureMockMvc
@ActiveProfiles("unittest")
public class CukesSteps {
	@Given("dummy") public void dummy(){}


}
