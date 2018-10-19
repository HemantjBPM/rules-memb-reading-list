package com.liferay.utils;

import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;

public class NewAgendaGroup extends DefaultAgendaEventListener{
	  public void matchCreated(MatchCreatedEvent event) {}
	  
	  public void matchCancelled(MatchCancelledEvent event) {}
	  
	  public void beforeMatchFired(BeforeMatchFiredEvent event) {}
	  
	  public void afterMatchFired(AfterMatchFiredEvent event) {}

}
