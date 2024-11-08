package com.sod.doc.traceroute.configuration;

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
import org.springframework.context.annotation.Profile;

import org.chenile.utils.entity.service.EntityStore;
import org.chenile.workflow.service.impl.StateEntityServiceImpl;
import org.chenile.workflow.service.stmcmds.BaseTransitionAction;
import org.chenile.workflow.service.stmcmds.GenericEntryAction;
import org.chenile.workflow.service.stmcmds.GenericExitAction;
import org.chenile.workflow.service.stmcmds.StmBodyTypeSelector;
import com.sod.doc.traceroute.model.Traceroute;
import com.sod.doc.traceroute.service.cmds.AssignTracerouteAction;
import com.sod.doc.traceroute.service.cmds.CloseTracerouteAction;
import com.sod.doc.traceroute.service.cmds.ResolveTracerouteAction;
import com.sod.doc.traceroute.service.healthcheck.TracerouteHealthChecker;
import com.sod.doc.traceroute.service.store.TracerouteEntityStore;
import org.chenile.workflow.api.WorkflowRegistry;

/**
 This is where you will instantiate all the required classes in Spring

*/
@Configuration
public class TracerouteConfiguration {
	private static final String FLOW_DEFINITION_FILE = "com/sod/doc/traceroute/states.xml";
	
	@Bean BeanFactoryAdapter tracerouteBeanFactoryAdapter() {
		return new SpringBeanFactoryAdapter();
	}
	
	@Bean STMFlowStoreImpl tracerouteFlowStore(@Qualifier("tracerouteBeanFactoryAdapter") BeanFactoryAdapter tracerouteBeanFactoryAdapter) throws Exception{
		STMFlowStoreImpl stmFlowStore = new STMFlowStoreImpl();
		stmFlowStore.setBeanFactory(tracerouteBeanFactoryAdapter);
		return stmFlowStore;
	}
	
	@Bean @Autowired STM<Traceroute> tracerouteEntityStm(@Qualifier("tracerouteFlowStore") STMFlowStoreImpl stmFlowStore) throws Exception{
		STMImpl<Traceroute> stm = new STMImpl<>();		
		stm.setStmFlowStore(stmFlowStore);
		return stm;
	}
	
	@Bean @Autowired STMActionsInfoProvider tracerouteActionsInfoProvider(@Qualifier("tracerouteFlowStore") STMFlowStoreImpl stmFlowStore) {
		STMActionsInfoProvider provider =  new STMActionsInfoProvider(stmFlowStore);
        WorkflowRegistry.addSTMActionsInfoProvider("traceroute",provider);
        return provider;
	}
	
	@Bean EntityStore<Traceroute> tracerouteEntityStore() {
		return new TracerouteEntityStore();
	}
	
	@Bean @Autowired StateEntityServiceImpl<Traceroute> _tracerouteStateEntityService_(
			@Qualifier("tracerouteEntityStm") STM<Traceroute> stm,
			@Qualifier("tracerouteActionsInfoProvider") STMActionsInfoProvider tracerouteInfoProvider,
			@Qualifier("tracerouteEntityStore") EntityStore<Traceroute> entityStore){
		return new StateEntityServiceImpl<>(stm, tracerouteInfoProvider, entityStore);
	}
	
	// Now we start constructing the STM Components 
	
	@Bean @Autowired GenericEntryAction<Traceroute> tracerouteEntryAction(@Qualifier("tracerouteEntityStore") EntityStore<Traceroute> entityStore,
			@Qualifier("tracerouteActionsInfoProvider") STMActionsInfoProvider tracerouteInfoProvider){
		return new GenericEntryAction<Traceroute>(entityStore,tracerouteInfoProvider);
	}
	
	@Bean GenericExitAction<Traceroute> tracerouteExitAction(){
		return new GenericExitAction<Traceroute>();
	}
	
	@Bean @Autowired StmBodyTypeSelector tracerouteBodyTypeSelector(@Qualifier("tracerouteActionsInfoProvider") STMActionsInfoProvider tracerouteInfoProvider) {
		return new StmBodyTypeSelector(tracerouteInfoProvider);
	}
	
	@Bean @Autowired STMTransitionAction<Traceroute> tracerouteBaseTransitionAction(){
		return new BaseTransitionAction<>();
	}
	
	@Bean AssignTracerouteAction assignTraceroute() {
		return new AssignTracerouteAction();
	}
	
	@Bean ResolveTracerouteAction resolveTraceroute() {
		return new ResolveTracerouteAction();
	}
	
	@Bean CloseTracerouteAction closeTraceroute() {
		return new CloseTracerouteAction();
	}

	@Bean
	XmlFlowReader tracerouteFlowReader(@Qualifier("tracerouteFlowStore") STMFlowStoreImpl flowStore) throws Exception {
		XmlFlowReader flowReader = new XmlFlowReader(flowStore);
		flowReader.setFilename(FLOW_DEFINITION_FILE);
		return flowReader;
	}
	

	@Bean TracerouteHealthChecker tracerouteHealthChecker(){
    	return new TracerouteHealthChecker();
    }

}
