package com.sod.doc.contentreader.service.impl;

import com.sod.doc.contentreader.model.Contentreader;
import com.sod.doc.contentreader.service.store.ContentreaderEntityStore;
import org.chenile.stm.STM;
import org.chenile.stm.impl.STMActionsInfoProvider;
import org.chenile.utils.entity.service.EntityStore;
import org.chenile.workflow.dto.StateEntityServiceResponse;
import org.chenile.workflow.service.impl.StateEntityServiceImpl;
import org.springframework.beans.factory.annotation.Value;

public class ContentReaderServiceImpl extends StateEntityServiceImpl<Contentreader> {


    @Value("${url.prefix.link}")
    private String HTTP_PREFIX;
    private ContentreaderEntityStore contentreaderEntityStore;
    public ContentReaderServiceImpl(STM<Contentreader> stm, STMActionsInfoProvider stmActionsInfoProvider, EntityStore<Contentreader> entityStore) {
        super(stm, stmActionsInfoProvider, entityStore);
        contentreaderEntityStore= (ContentreaderEntityStore) entityStore;
    }

    @Override
    public StateEntityServiceResponse<Contentreader> processById(String id, String event, Object payload) {
        Contentreader contentreader = null;
        if(id.equals("0")){
           contentreader= super.create(new Contentreader()).getMutatedEntity();
        }
        assert contentreader != null;
        return super.processById(contentreader.getId(), event, payload);
    }

    @Override
    public StateEntityServiceResponse<Contentreader> retrieve(String id) {
      Contentreader contentreader= contentreaderEntityStore.findByShortUrl(HTTP_PREFIX+id);
       return super.processById(contentreader.getId(),"access",null);
    }
}
