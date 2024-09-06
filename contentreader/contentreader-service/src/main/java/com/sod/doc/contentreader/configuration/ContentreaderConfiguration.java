package com.sod.doc.contentreader.configuration;

import com.sod.doc.contentreader.service.cmds.AccessShortestUrlService;
import com.sod.doc.contentreader.service.cmds.CreateShortestUrlService;
import com.sod.doc.contentreader.service.cmds.ValidateUrlLifeSpan;
import com.sod.doc.contentreader.service.impl.ContentReaderServiceImpl;
import com.sod.doc.contentreader.service.store.ContentReaderEntityStoreImpl;
import org.chenile.stm.STM;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.impl.BeanFactoryAdapter;
import org.chenile.stm.impl.STMActionsInfoProvider;
import org.chenile.stm.impl.STMFlowStoreImpl;
import org.chenile.stm.impl.STMImpl;
import org.chenile.stm.impl.XmlFlowReader;
import org.chenile.stm.spring.SpringBeanFactoryAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.chenile.utils.entity.service.EntityStore;
import org.chenile.workflow.service.impl.StateEntityServiceImpl;
import org.chenile.workflow.service.stmcmds.BaseTransitionAction;
import org.chenile.workflow.service.stmcmds.GenericEntryAction;
import org.chenile.workflow.service.stmcmds.GenericExitAction;
import org.chenile.workflow.service.stmcmds.StmBodyTypeSelector;
import com.sod.doc.contentreader.model.Contentreader;
import com.sod.doc.contentreader.service.healthcheck.ContentreaderHealthChecker;
import org.chenile.workflow.api.WorkflowRegistry;

/**
 * This is where you will instantiate all the required classes in Spring
 */
@Configuration
public class ContentreaderConfiguration {
    Logger logger=LoggerFactory.getLogger(ContentreaderConfiguration.class);
    private static final String FLOW_DEFINITION_FILE = "com/sod/doc/contentreader/states.xml";

    @Bean
    BeanFactoryAdapter contentreaderBeanFactoryAdapter() {
        return new SpringBeanFactoryAdapter();
    }

    @Bean
    STMFlowStoreImpl contentreaderFlowStore(@Qualifier("contentreaderBeanFactoryAdapter") BeanFactoryAdapter contentreaderBeanFactoryAdapter) throws Exception {
        STMFlowStoreImpl stmFlowStore = new STMFlowStoreImpl();
        stmFlowStore.setBeanFactory(contentreaderBeanFactoryAdapter);
        return stmFlowStore;
    }

    @Bean
    @Autowired
    STM<Contentreader> contentreaderEntityStm(@Qualifier("contentreaderFlowStore") STMFlowStoreImpl stmFlowStore) throws Exception {
        STMImpl<Contentreader> stm = new STMImpl<>();
        stm.setStmFlowStore(stmFlowStore);
        return stm;
    }

    @Bean
    @Autowired
    STMActionsInfoProvider contentreaderActionsInfoProvider(@Qualifier("contentreaderFlowStore") STMFlowStoreImpl stmFlowStore) {
        STMActionsInfoProvider provider = new STMActionsInfoProvider(stmFlowStore);
        WorkflowRegistry.addSTMActionsInfoProvider("contentreader", provider);
        return provider;
    }

    @Bean
    EntityStore<Contentreader> contentreaderEntityStore( ) {
        logger.info("entered EntityStore");
        return new ContentReaderEntityStoreImpl();
    }

    @Bean
    @Autowired
    StateEntityServiceImpl<Contentreader> _contentreaderStateEntityService_(
            @Qualifier("contentreaderEntityStm") STM<Contentreader> stm,
            @Qualifier("contentreaderActionsInfoProvider") STMActionsInfoProvider contentreaderInfoProvider,
            @Qualifier("contentreaderEntityStore") EntityStore<Contentreader> entityStore) {
        logger.info("entered StateEntityServiceImpl");
        return new ContentReaderServiceImpl(stm, contentreaderInfoProvider, entityStore);
    }

    @Bean
    CreateShortestUrlService createShortestUrlService() {
        return new CreateShortestUrlService();
    }
    // Now we start constructing the STM Components

    @Bean
    @Autowired
    GenericEntryAction<Contentreader> contentreaderEntryAction(@Qualifier("contentreaderEntityStore") EntityStore<Contentreader> entityStore,
                                                               @Qualifier("contentreaderActionsInfoProvider") STMActionsInfoProvider contentreaderInfoProvider) {
        return new GenericEntryAction<Contentreader>(entityStore, contentreaderInfoProvider);
    }

    @Bean
    GenericExitAction<Contentreader> contentreaderExitAction() {
        return new GenericExitAction<Contentreader>();
    }

    @Bean
    @Autowired
    StmBodyTypeSelector contentreaderBodyTypeSelector(@Qualifier("contentreaderActionsInfoProvider") STMActionsInfoProvider contentreaderInfoProvider) {
        return new StmBodyTypeSelector(contentreaderInfoProvider);
    }

    @Bean
    STMTransitionAction<Contentreader> contentreaderBaseTransitionAction() {
        return new BaseTransitionAction<>();
    }

    @Bean
    AccessShortestUrlService accessShortestUrlService(){
        return new AccessShortestUrlService();
    }

    @Bean
    ValidateUrlLifeSpan validateUrlLifeSpan(){
        return new ValidateUrlLifeSpan();
    }
    @Bean
    XmlFlowReader contentreaderFlowReader(@Qualifier("contentreaderFlowStore") STMFlowStoreImpl flowStore) throws Exception {
        XmlFlowReader flowReader = new XmlFlowReader(flowStore);
        flowReader.setFilename(FLOW_DEFINITION_FILE);
        return flowReader;
    }


    @Bean
    ContentreaderHealthChecker contentreaderHealthChecker() {
        return new ContentreaderHealthChecker();
    }

}
