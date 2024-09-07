package com.sod.doc.contentreader.configuration.controller;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;
import org.chenile.base.response.GenericResponse;
import org.chenile.http.annotation.BodyTypeSelector;
import org.chenile.http.annotation.ChenileController;
import org.chenile.http.annotation.ChenileParamType;
import org.chenile.http.handler.ControllerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.chenile.stm.StateEntity;

import org.springframework.web.bind.annotation.*;
import org.chenile.workflow.dto.StateEntityServiceResponse;
import com.sod.doc.contentreader.model.Contentreader;

import java.net.URI;

@RestController
@CrossOrigin(origins = "*") // Allows all origins, use with caution
@ChenileController(value = "contentreaderService", serviceName = "_contentreaderStateEntityService_",
        healthCheckerName = "contentreaderHealthChecker")
public class ContentreaderController extends ControllerSupport {
    @GetMapping("/{id}")
    public ResponseEntity<Object> retrieve(
            HttpServletRequest httpServletRequest,
            @PathVariable String id) {

        GenericResponse<StateEntityServiceResponse<Contentreader>> serviceResponse = (GenericResponse<StateEntityServiceResponse<Contentreader>>) process(httpServletRequest, id).getBody();
        assert serviceResponse != null;
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(serviceResponse.getData().getMutatedEntity().originalUrl))
                .build();
    }

    @PostMapping("/contentreader")
    public ResponseEntity<GenericResponse<StateEntityServiceResponse<Contentreader>>> create(
            HttpServletRequest httpServletRequest,
            @ChenileParamType(StateEntity.class)
            @RequestBody Contentreader entity) {
        return process(httpServletRequest, entity);
    }


    @PutMapping("/contentreader/{id}/{eventID}")
    @BodyTypeSelector("contentreaderBodyTypeSelector")
    public ResponseEntity<GenericResponse<StateEntityServiceResponse<Contentreader>>> processById(
            HttpServletRequest httpServletRequest,
            @PathVariable String id,
            @PathVariable String eventID,
            @ChenileParamType(Object.class)
            @RequestBody String eventPayload) {
        return process(httpServletRequest, id, eventID, eventPayload);
    }
}
