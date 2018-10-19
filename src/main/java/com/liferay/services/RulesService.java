package com.liferay.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.liferay.models.RuleDataModel;
import com.liferay.models.RuleResponse;
import com.liferay.utils.CommonUtils;
import com.liferay.utils.LiferayConstant;
import com.liferay.utils.TrackingAgendaEventListener;

@Service
public class RulesService {

	private static final Logger logger = Logger.getLogger(RulesService.class);

	@Autowired
	private Environment env;

	public RuleResponse applyRules(RuleDataModel ruleDataModel) {
		logger.info("In applyRules Method, applying rules for data object:  " + ruleDataModel);
		KieSession kSession;
		try {
			kSession = CommonUtils.createKession(env.getProperty(LiferayConstant.RULEFILELOCATION));
			if (kSession != null) {
				if (null != ruleDataModel) {
					TrackingAgendaEventListener trackingAgendaEventListener = new TrackingAgendaEventListener();
					kSession.addEventListener(trackingAgendaEventListener);
					if (null != ruleDataModel.getRuleFlowGroup())
						kSession.getAgenda().getAgendaGroup(ruleDataModel.getRuleFlowGroup()).setFocus();
					else {
						logger.info("Setting default rule group." + LiferayConstant.DEFAULRULEFLOWGROUPNAME);
						kSession.getAgenda().getAgendaGroup(LiferayConstant.DEFAULRULEFLOWGROUPNAME).setFocus();
					}
					kSession.insert(ruleDataModel);
					kSession.fireAllRules();
					kSession.dispose();
					ruleDataModel.setMatchedZones(trackingAgendaEventListener.matchRules());
					logger.info("Rules applied successfully. Update data object:  " + ruleDataModel);
					return new RuleResponse("", false, ruleDataModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception occured while applying rules. Exception: " + e.getMessage());
			return new RuleResponse("Exception while updating rules. Exception: " + e.toString(), true, ruleDataModel);
		}
		return null;
	}

	public Map<String, Integer> applyRulesToFindZones(RuleDataModel ruleDataModel, int zoneCount) {
		logger.info("In applyRulesToFindZones Method, applying rules for data object:  " + ruleDataModel);
		Map<String, Integer> ruleCountMap = new HashMap<>();
		for (int i = 1; i <= zoneCount; i++) {
			KieSession kSession = null;
			try {
				kSession = CommonUtils.createKession(env.getProperty(LiferayConstant.RULEFILELOCATION));
				if (kSession != null) {
					if (null != ruleDataModel) {
						TrackingAgendaEventListener trackingAgendaEventListener = new TrackingAgendaEventListener();
						kSession.addEventListener(trackingAgendaEventListener);
						logger.info("Setting rule flow group." + LiferayConstant.ZONE + i);
						kSession.getAgenda().getAgendaGroup(LiferayConstant.ZONE + i).setFocus();
						kSession.insert(ruleDataModel);
						kSession.fireAllRules();
						kSession.dispose();
						ruleCountMap.put(LiferayConstant.ZONE + i, trackingAgendaEventListener.matchRules().size());
						logger.info("Rules fired for zone: " + LiferayConstant.ZONE + i + ".Matched count: " + trackingAgendaEventListener.matchRules().size());
						logger.info("Rules applied successfully. Update data object:  " + ruleDataModel);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("Exception occured while applying rules. Exception: " + e.getMessage());
			}
		}
		return ruleCountMap;
	}

	/*
	 * public RuleResponse applyRules(RuleDataModel ruleDataModel, String
	 * ruleFlowGroup) {
	 * logger.info("In applyRules Method, applying rules for data object:  " +
	 * ruleDataModel + " Rule flow group is: " + ruleFlowGroup); KieSession
	 * kSession; try { kSession =
	 * CommonUtils.createKession(env.getProperty(LiferayConstant.
	 * RULEFILELOCATION)); if (kSession != null) { if (null != ruleDataModel) {
	 * kSession.getAgenda().getAgendaGroup(ruleFlowGroup).setFocus();
	 * kSession.insert(ruleDataModel); kSession.fireAllRules();
	 * kSession.dispose();
	 * logger.info("Rules applied successfully. Update data object:  " +
	 * ruleDataModel); return new RuleResponse("", false, ruleDataModel); } } }
	 * catch (Exception e) { e.printStackTrace();
	 * logger.info("Exception occured while applying rules. Exception: " +
	 * e.getMessage()); return new
	 * RuleResponse("Exception while updating rules. Exception: " +
	 * e.toString(), true, ruleDataModel); } return null; }
	 */
	public RuleResponse update() {
		try {
			CommonUtils.initialize(env.getProperty(LiferayConstant.RULEFILELOCATION));
			logger.info("Rules updated successfully.");
			return new RuleResponse("Rules updated Successfully!", false, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception occured while updating rules. Exception: " + e.toString());
			return new RuleResponse("Exception while updating rules. Exception: " + e.toString(), true, null);
		}
	}

}
