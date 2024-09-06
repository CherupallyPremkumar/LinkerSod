package com.sod.doc.contentreader.configuration.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.chenile.base.response.GenericResponse;
import org.chenile.http.annotation.BodyTypeSelector;
import org.chenile.http.annotation.ChenileController;
import org.chenile.http.annotation.ChenileParamType;
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
import com.sod.doc.contentreader.model.Contentreader;

@RestController
@ChenileController(value = "contentreaderService", serviceName = "_contentreaderStateEntityService_",
		healthCheckerName = "contentreaderHealthChecker")
public class ContentreaderController extends ControllerSupport{
	
	@GetMapping("/get/{id}")
	public ResponseEntity<GenericResponse<StateEntityServiceResponse<Contentreader>>> retrieve(
			HttpServletRequest httpServletRequest,
			@PathVariable String id){
		return process(httpServletRequest,id);
	}

	@PostMapping("/contentreader")
	public ResponseEntity<GenericResponse<StateEntityServiceResponse<Contentreader>>> create(
			HttpServletRequest httpServletRequest,
			@ChenileParamType(StateEntity.class)
			@RequestBody Contentreader entity){
		return process(httpServletRequest,entity);
	}

	
	@PutMapping("/contentreader/{id}/{eventID}")
	@BodyTypeSelector("contentreaderBodyTypeSelector")
	public ResponseEntity<GenericResponse<StateEntityServiceResponse<Contentreader>>> processById(
			HttpServletRequest httpServletRequest,
			@PathVariable String id,
			@PathVariable String eventID,
			@ChenileParamType(Object.class) 
			@RequestBody String eventPayload){
		return process(httpServletRequest,id,eventID,eventPayload);
	}
}
