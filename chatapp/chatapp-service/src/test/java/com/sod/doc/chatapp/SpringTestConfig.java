package com.sod.doc.chatapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;


@Configuration
@PropertySource("classpath:com/sod/doc/chatapp/TestService.properties")
@SpringBootApplication(scanBasePackages = { "org.chenile.configuration","org.chenile.**", "com.sod.doc.chatapp.configuration" })
@ActiveProfiles("unittest")
public class SpringTestConfig extends SpringBootServletInitializer{


}

