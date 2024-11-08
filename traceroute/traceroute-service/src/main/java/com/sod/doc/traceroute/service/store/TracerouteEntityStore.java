package com.sod.doc.traceroute.service.store;

import java.util.HashMap;
import java.util.Map;

import org.chenile.utils.entity.service.EntityStore;
import com.sod.doc.traceroute.model.Traceroute;
import org.springframework.beans.factory.annotation.Autowired;
import org.chenile.base.exception.NotFoundException;
import com.sod.doc.traceroute.configuration.dao.TracerouteRepository;
import java.util.Optional;

public class TracerouteEntityStore implements EntityStore<Traceroute>{
    @Autowired private TracerouteRepository tracerouteRepository;

	@Override
	public void store(Traceroute entity) {
        tracerouteRepository.save(entity);
	}

	@Override
	public Traceroute retrieve(String id) {
        Optional<Traceroute> entity = tracerouteRepository.findById(id);
        if (entity.isPresent()) return entity.get();
        throw new NotFoundException(1500,"Unable to find Traceroute with ID " + id);
	}

}
