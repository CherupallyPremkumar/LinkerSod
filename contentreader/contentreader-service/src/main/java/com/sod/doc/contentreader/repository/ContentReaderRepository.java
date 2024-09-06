package com.sod.doc.contentreader.repository;

import com.sod.doc.contentreader.model.Contentreader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentReaderRepository extends JpaRepository<Contentreader,String> {
    Optional<Contentreader> findByShortestUrl(String shortestUrl);
}

