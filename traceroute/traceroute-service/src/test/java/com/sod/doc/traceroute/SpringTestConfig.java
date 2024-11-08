package com.sod.doc.traceroute;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

import org.chenile.utils.entity.service.EntityStore;
import com.sod.doc.traceroute.model.Traceroute;


@Configuration
@PropertySource("classpath:com/sod/doc/traceroute/TestService.properties")
@SpringBootApplication(scanBasePackages = { "org.chenile.configuration", "com.sod.doc.traceroute.configuration" })
@ActiveProfiles("unittest")
public class SpringTestConfig extends SpringBootServletInitializer{
	
}

