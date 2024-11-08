package com.sod.doc.chatapp.configuration;

import com.sod.doc.chatapp.service.impl.ChatAppStateServiceImpl;
import org.chenile.stm.STM;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.impl.BeanFactoryAdapter;
import org.chenile.stm.impl.STMActionsInfoProvider;
import org.chenile.stm.impl.STMFlowStoreImpl;
import org.chenile.stm.impl.STMImpl;
import org.chenile.stm.impl.XmlFlowReader;
import org.chenile.stm.spring.SpringBeanFactoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.chenile.utils.entity.service.EntityStore;
import org.chenile.workflow.service.stmcmds.BaseTransitionAction;
import org.chenile.workflow.service.stmcmds.GenericEntryAction;
import org.chenile.workflow.service.stmcmds.GenericExitAction;
import org.chenile.workflow.service.stmcmds.StmBodyTypeSelector;
import com.sod.doc.chatapp.model.domain.Chatapp;
import com.sod.doc.chatapp.service.healthcheck.ChatappHealthChecker;
import com.sod.doc.chatapp.service.store.ChatappEntityStore;
import org.chenile.workflow.api.WorkflowRegistry;

/**
 This is where you will instantiate all the required classes in Spring

*/
@Configuration
public class ChatappConfiguration {
	private static final String FLOW_DEFINITION_FILE = "com/sod/doc/chatapp/states.xml";

	
	@Bean BeanFactoryAdapter chatappBeanFactoryAdapter() {
		return new SpringBeanFactoryAdapter();
	}
	
	@Bean STMFlowStoreImpl chatappFlowStore(@Qualifier("chatappBeanFactoryAdapter") BeanFactoryAdapter chatappBeanFactoryAdapter) throws Exception{
		STMFlowStoreImpl stmFlowStore = new STMFlowStoreImpl();
		stmFlowStore.setBeanFactory(chatappBeanFactoryAdapter);
		return stmFlowStore;
	}
	
	@Bean @Autowired STM<Chatapp> chatappEntityStm(@Qualifier("chatappFlowStore") STMFlowStoreImpl stmFlowStore) throws Exception{
		STMImpl<Chatapp> stm = new STMImpl<>();		
		stm.setStmFlowStore(stmFlowStore);
		return stm;
	}
	
	@Bean @Autowired STMActionsInfoProvider chatappActionsInfoProvider(@Qualifier("chatappFlowStore") STMFlowStoreImpl stmFlowStore) {
		STMActionsInfoProvider provider =  new STMActionsInfoProvider(stmFlowStore);
        WorkflowRegistry.addSTMActionsInfoProvider("chatapp",provider);
        return provider;
	}
	
	@Bean EntityStore<Chatapp> chatappEntityStore() {
		return new ChatappEntityStore();
	}
	
	@Bean @Autowired
	ChatAppStateServiceImpl _chatappStateEntityService_(
			@Qualifier("chatappEntityStm") STM<Chatapp> stm,
			@Qualifier("chatappActionsInfoProvider") STMActionsInfoProvider chatappInfoProvider,
			@Qualifier("chatappEntityStore") EntityStore<Chatapp> entityStore){
		return new ChatAppStateServiceImpl(stm, chatappInfoProvider, entityStore);
	}
	
	// Now we start constructing the STM Components 
	
	@Bean @Autowired GenericEntryAction<Chatapp> chatappEntryAction(@Qualifier("chatappEntityStore") EntityStore<Chatapp> entityStore,
			@Qualifier("chatappActionsInfoProvider") STMActionsInfoProvider chatappInfoProvider){
		return new GenericEntryAction<Chatapp>(entityStore,chatappInfoProvider);
	}
	
	@Bean GenericExitAction<Chatapp> chatappExitAction(){
		return new GenericExitAction<Chatapp>();
	}
	
	@Bean @Autowired StmBodyTypeSelector chatappBodyTypeSelector(@Qualifier("chatappActionsInfoProvider") STMActionsInfoProvider chatappInfoProvider) {
		return new StmBodyTypeSelector(chatappInfoProvider);
	}
	
	@Bean STMTransitionAction<Chatapp> chatappBaseTransitionAction(){
		return new BaseTransitionAction<>();
	}


	@Bean
	XmlFlowReader chatappFlowReader(@Qualifier("chatappFlowStore") STMFlowStoreImpl flowStore) throws Exception {
		XmlFlowReader flowReader = new XmlFlowReader(flowStore);
		flowReader.setFilename(FLOW_DEFINITION_FILE);
		return flowReader;
	}
	

	@Bean ChatappHealthChecker chatappHealthChecker(){
    	return new ChatappHealthChecker();
    }

}
