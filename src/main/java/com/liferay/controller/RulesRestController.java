package com.liferay.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.liferay.models.RuleDataModel;
import com.liferay.models.RuleResponse;
import com.liferay.services.DataService;
import com.liferay.services.LifeRayService;
import com.liferay.services.RulesService;
import com.liferay.utils.LiferayConstant;

@RestController
public class RulesRestController {

	private static final Logger logger = Logger.getLogger(RulesRestController.class);

	@Autowired
	RulesService ruleService;

	@Autowired
	DataService dataService;
	
	@Autowired
	LifeRayService lifeRayService;

	@RequestMapping(value = LiferayConstant.APPLYRULES, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public RuleResponse applyRules(@RequestBody RuleDataModel ruleDataModel) throws Exception {
		logger.info("Request received to apply Rules. Input data is: " + ruleDataModel);
		return lifeRayService.fetchAndApplyRules(ruleDataModel);
	}

	@RequestMapping(value = LiferayConstant.UPDATERULES, method = RequestMethod.GET)
	public RuleResponse updateRules() {
		logger.info("Request received to update Rules.");
		return ruleService.update();
	}

	@RequestMapping(value = LiferayConstant.GETDATAANDAPPLYRULES, method = RequestMethod.GET)
	public RuleResponse getDataAndApplyRules() {
		logger.info("Request received to get data and apply Rules." );
		RuleResponse ruleResponse = dataService.getData();
		if (ruleResponse.isError())
			return ruleResponse;
		return ruleService.applyRules(ruleResponse.getData());
	}
	
	
	@RequestMapping(value = LiferayConstant.FETCHDATAANDAPPLYRULES, method = RequestMethod.GET)
	public RuleResponse fetchAndApplyRules() {
		logger.info("Request received to get data and apply Rules." );
		RuleResponse ruleResponse = dataService.getData();
		return ruleResponse;
	}

	@RequestMapping(value = LiferayConstant.FETCHDATAANDAPPLYRULESWITHRULEFLOWGROUPNAME, method = RequestMethod.GET)
	public RuleResponse getDataAndApplyRules(@PathVariable(value = "ruleFlowGroupName") String ruleFlowGroupName)
			throws Exception {
		logger.info("Request received to get data and apply Rules with Rule Flow Group." + ruleFlowGroupName );
		RuleResponse ruleResponse = dataService.getData();
		if (ruleResponse.isError())
			return ruleResponse;
		return ruleService.applyRules(ruleResponse.getData());
	}

}
