package com.sod.doc.traceroute.service.cmds;

import org.chenile.stm.STMInternalTransitionInvoker;
import org.chenile.stm.State;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.model.Transition;

import com.sod.doc.traceroute.model.AssignTraceroutePayload;
import com.sod.doc.traceroute.model.Traceroute;

public class AssignTracerouteAction implements STMTransitionAction<Traceroute>{

	@Override
	public void doTransition(Traceroute traceroute, Object transitionParam, State startState, String eventId,
			State endState, STMInternalTransitionInvoker<?> stm, Transition transition) throws Exception {
		AssignTraceroutePayload payload = (AssignTraceroutePayload) transitionParam;
		traceroute.assignee = payload.assignee;
		traceroute.assignComment = payload.getComment();
	}

}
