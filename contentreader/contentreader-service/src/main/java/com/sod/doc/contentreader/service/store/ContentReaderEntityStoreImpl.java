package com.sod.doc.contentreader.service.store;

import com.sod.doc.contentreader.model.Contentreader;
import com.sod.doc.contentreader.repository.ContentReaderRepository;

import java.util.Optional;

public class ContentReaderEntityStoreImpl extends ContentreaderEntityStore{
//    public ContentReaderEntityStoreImpl(ContentreaderRepository contentreaderRepository) {
//        super(contentreaderRepository);
//    }

    @Override
    public Contentreader findByShortUrl(String sUrl) {
        Optional<Contentreader> result= super.contentreaderRepository.findByShortestUrl(sUrl);
        return result.orElse(null);
    }
}
