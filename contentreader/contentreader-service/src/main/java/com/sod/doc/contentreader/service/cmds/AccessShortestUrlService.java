package com.sod.doc.contentreader.service.cmds;

import com.sod.doc.contentreader.model.Contentreader;
import org.chenile.stm.STMInternalTransitionInvoker;
import org.chenile.stm.State;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.model.Transition;

public class AccessShortestUrlService implements STMTransitionAction<Contentreader> {
    @Override
    public void doTransition(Contentreader contentreader, Object transitionParam, State startState, String eventId, State endState, STMInternalTransitionInvoker<?> stm, Transition transition) throws Exception {
        contentreader.setAccessCount(contentreader.getAccessCount()+1);
    }
}
