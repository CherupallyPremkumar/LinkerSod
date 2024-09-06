package com.sod.doc.contentreader.service.store;

import org.chenile.utils.entity.service.EntityStore;
import com.sod.doc.contentreader.model.Contentreader;
import org.chenile.base.exception.NotFoundException;
import com.sod.doc.contentreader.repository.ContentReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

public abstract class ContentreaderEntityStore implements EntityStore<Contentreader> {

    @Autowired
    @Qualifier("contentReaderRepository")
    protected ContentReaderRepository contentreaderRepository;


//    protected ContentreaderEntityStore(ContentreaderRepository contentreaderRepository) {
//        this.contentreaderRepository = contentreaderRepository;
//    }

    @Override
    public void store(Contentreader entity) {
        contentreaderRepository.save(entity);
    }

    @Override
    public Contentreader retrieve(String id) {
        Optional<Contentreader> entity = contentreaderRepository.findById(id);
        if (entity.isPresent()) return entity.get();
        throw new NotFoundException(1500, "Unable to find Contentreader with ID " + id);
    }

    public abstract Contentreader findByShortUrl(String sUrl);
}
