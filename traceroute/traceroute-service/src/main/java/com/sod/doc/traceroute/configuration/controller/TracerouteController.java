package com.sod.doc.traceroute.configuration.controller;

import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.chenile.workflow.dto.StateEntityServiceResponse;
import com.sod.doc.traceroute.model.Traceroute;

@RestController
@ChenileController(value = "tracerouteService", serviceName = "_tracerouteStateEntityService_",
		healthCheckerName = "tracerouteHealthChecker")
public class TracerouteController extends ControllerSupport{
	
	@GetMapping("/traceroute/{id}")
	public ResponseEntity<GenericResponse<StateEntityServiceResponse<Traceroute>>> retrieve(
			HttpServletRequest httpServletRequest,
			@PathVariable String id){
		return process(httpServletRequest,id);
	}

	@PostMapping("/traceroute")
	public ResponseEntity<GenericResponse<StateEntityServiceResponse<Traceroute>>> create(
			HttpServletRequest httpServletRequest,
			@ChenileParamType(StateEntity.class)
			@RequestBody Traceroute entity){
		return process(httpServletRequest,entity);
	}

	
	@PutMapping("/traceroute/{id}/{eventID}")
	@BodyTypeSelector("tracerouteBodyTypeSelector")
	public ResponseEntity<GenericResponse<StateEntityServiceResponse<Traceroute>>> processById(
			HttpServletRequest httpServletRequest,
			@PathVariable String id,
			@PathVariable String eventID,
			@ChenileParamType(Object.class) 
			@RequestBody String eventPayload){
		return process(httpServletRequest,id,eventID,eventPayload);
	}


}
