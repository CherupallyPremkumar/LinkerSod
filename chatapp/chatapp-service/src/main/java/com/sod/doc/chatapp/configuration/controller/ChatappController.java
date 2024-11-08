package com.sod.doc.chatapp.configuration.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.chenile.base.response.GenericResponse;
import org.chenile.http.annotation.BodyTypeSelector;
import org.chenile.http.annotation.ChenileController;
import org.chenile.http.annotation.ChenileParamType;
import org.chenile.http.annotation.InterceptedBy;
import org.chenile.http.handler.ControllerSupport;
import org.springframework.http.ResponseEntity;

import org.chenile.stm.StateEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.chenile.workflow.dto.StateEntityServiceResponse;
import com.sod.doc.chatapp.model.domain.Chatapp;

@RestController
@ChenileController(value = "chatappService", serviceName = "_chatappStateEntityService_",
		healthCheckerName = "chatappHealthChecker")
public class ChatappController extends ControllerSupport{
	
	@GetMapping("/chatapp/{id}")
//	@InterceptedBy({"securityInterceptor"})
//	@SecurityConfig(authorities = {"test.premium", "test.normal"})
	public ResponseEntity<GenericResponse<StateEntityServiceResponse<Chatapp>>> retrieve(
			HttpServletRequest httpServletRequest,
			@PathVariable String id){
		return process(httpServletRequest,id);
	}

	@PostMapping("/chatapp")
	public ResponseEntity<GenericResponse<StateEntityServiceResponse<Chatapp>>> create(
			HttpServletRequest httpServletRequest,
			@ChenileParamType(StateEntity.class)
			@RequestBody Chatapp entity){
		return process(httpServletRequest,entity);
	}

	
	@PutMapping("/chatapp/{id}/{eventID}")
//	@BodyTypeSelector("chatappBodyTypeSelector")
//	@InterceptedBy({"securityInterceptor"})
	public ResponseEntity<GenericResponse<StateEntityServiceResponse<Chatapp>>> processById(
			HttpServletRequest httpServletRequest,
			@PathVariable String id,
			@PathVariable String eventID,
			@ChenileParamType(Object.class) 
			@RequestBody String eventPayload){
		return process(httpServletRequest,id,eventID,eventPayload);
	}


}
