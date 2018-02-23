package com.rueggerllc.bookstore.utils;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.log4j.Logger;

public class LifeCycleListener implements PhaseListener {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LifeCycleListener.class);

	public void afterPhase(PhaseEvent event) {
		// logger.info("-------afterPhase----- " + event.getPhaseId()); 
		
	}

	public void beforePhase(PhaseEvent event) {
		// logger.info("-------beforePhase----- " + event.getPhaseId());
		
	}

	public PhaseId getPhaseId() {
		// logger.info("-------getPhaseId-----");
		return PhaseId.ANY_PHASE;
	}
	
}
