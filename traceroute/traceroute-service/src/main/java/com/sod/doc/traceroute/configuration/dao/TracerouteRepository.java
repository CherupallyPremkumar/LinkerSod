package com.sod.doc.traceroute.configuration.dao;

import com.sod.doc.traceroute.model.Traceroute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  public interface TracerouteRepository extends JpaRepository<Traceroute,String> {}
