package com.sod.doc.chatapp.service.impl;

import com.sod.doc.chatapp.model.domain.Chatapp;
import com.sod.doc.chatapp.payload.LoginPayload;
import com.sod.doc.chatapp.service.cmds.impl.LoginValidationService;
import jakarta.transaction.Transactional;
import org.chenile.stm.STM;
import org.chenile.stm.impl.STMActionsInfoProvider;
import org.chenile.utils.entity.service.EntityStore;
import org.chenile.workflow.dto.StateEntityServiceResponse;
import org.chenile.workflow.service.impl.StateEntityServiceImpl;

public class ChatAppStateServiceImpl extends StateEntityServiceImpl {

    private LoginValidationService loginValidationService;
    public ChatAppStateServiceImpl(STM stm, STMActionsInfoProvider stmActionsInfoProvider, EntityStore entityStore) {
        super(stm, stmActionsInfoProvider, entityStore);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public StateEntityServiceResponse processById(String id, String event, Object payload) {
        Chatapp chatapp = null;
        if(id.equals("0"))
        {
         chatapp= (Chatapp) super.create(new Chatapp()).getMutatedEntity();  
        }else {
            chatapp= (Chatapp) entityStore.retrieve(id);
        }

        return super.process(chatapp, event, payload);
    }


}
