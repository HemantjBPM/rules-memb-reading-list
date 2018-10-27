package com.liferay.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	public RuleResponse applyRules(@RequestBody RuleDataModel ruleDataModel, HttpServletResponse response) throws Exception {
		return lifeRayService.fetchAndApplyRules(ruleDataModel);
	}

	@RequestMapping(value = LiferayConstant.UPDATERULES, method = RequestMethod.GET)
	public RuleResponse updateRules() {
		logger.info("Request received to update Rules.");
		return ruleService.update();
	}

}
