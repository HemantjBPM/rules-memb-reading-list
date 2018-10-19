package com.liferay.services;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.liferay.models.RuleDataModel;
import com.liferay.models.RuleResponse;
import com.liferay.utils.CommonUtils;
import com.liferay.utils.LiferayConstant;

@Service
public class LifeRayService {

	@Autowired
	DataService dataService;
	
	@Autowired
	RulesService ruleService;

	@Autowired
	private Environment env;

	private static final Logger logger = Logger.getLogger(LifeRayService.class);

	public RuleResponse fetchAndApplyRules(RuleDataModel ruleDataModel){
		logger.info("Request received for Fetch and Apply rules with input data: " + ruleDataModel);
		Map<String, List<Map<String, Object>>> articleMap = new TreeMap<>();
		logger.info("Fetching for Create Goal: " + ruleDataModel);
		try{
			RuleResponse dataServiceResponse = dataService.getLatestTypeOfGoal(ruleDataModel.getUserId());
			
			List<String> createGoalMapping = Arrays.asList(env.getProperty(LiferayConstant.CREATEGOALMAPPING).split(","));
			System.out.println(createGoalMapping);
			String tyOfGoal = null;
			List<Map<String, Object>> createGoalArticles = null;
			if(createGoalMapping.contains(dataServiceResponse.getData().getTypeOfGoals())){
				logger.info("Fetching type of Goal from property: " + ruleDataModel);
				tyOfGoal = env.getProperty(dataServiceResponse.getData().getTypeOfGoals());
			}else
				tyOfGoal = dataServiceResponse.getData().getTypeOfGoals();
			createGoalArticles = dataService.fetchArticles(tyOfGoal, Integer.parseInt(env.getProperty(LiferayConstant.CREATEGOALLIMIT)));
			articleMap.put(LiferayConstant.CREATEGOAL, createGoalArticles);
			
			logger.info("After Create Goal article is: " + articleMap);
			logger.info("Applying rules for Members ratio: " + ruleDataModel);
			
			Map<String, Integer> ruleServiceResponseMap = CommonUtils.sortByComparator(ruleService.applyRulesToFindZones(ruleDataModel,Integer.parseInt(env.getProperty(LiferayConstant.DEFAULTZONECOUNT))),LiferayConstant.DESC);
			logger.info("Rules applied for zones. Output Map is: " + ruleServiceResponseMap);
			ruleDataModel.setZoneFiredCount(ruleServiceResponseMap.toString());
			List<String> list = new LinkedList<String>(ruleServiceResponseMap.keySet());
			
			if(ruleServiceResponseMap.get(list.get(0)).equals(ruleServiceResponseMap.get(list.get(1))))
				ruleDataModel.setZones(env.getProperty(LiferayConstant.DEFAULTZONE));
			 else
				 ruleDataModel.setZones(list.get(0));
			
			logger.info("Zone found in rule engine: " + ruleDataModel.getZones());
			RuleResponse ruleServiceResponse = ruleService.applyRules(ruleDataModel);
			logger.info("Rules matched are: " + ruleServiceResponse.getData().getMatchedZones());
			
			if(ruleServiceResponse.isError()){
				return ruleServiceResponse;
			}else{
					logger.info("Fetching Article for rule match for Zones " + ruleDataModel.getZones() + "Fetching Article categories: "+ ruleServiceResponse.getData().getMatchedZones().get(0));
						if(ruleServiceResponse.getData().getEarn() > 0){
							List<Map<String, Object>> earnArticles = dataService.fetchArticles(env.getProperty(LiferayConstant.FILTEREARN), ruleServiceResponse.getData().getEarn());
							articleMap.put(LiferayConstant.EARN, earnArticles);						
						} 
						if(ruleServiceResponse.getData().getManage() > 0){
							List<Map<String, Object>> earnArticles = dataService.fetchArticles(env.getProperty(LiferayConstant.FILTERMANAGE), ruleServiceResponse.getData().getManage());
							articleMap.put(LiferayConstant.MANAGE, earnArticles);
						}
						if(ruleServiceResponse.getData().getSave() > 0){
							List<Map<String, Object>> earnArticles = dataService.fetchArticles(env.getProperty(LiferayConstant.FILTERSAVE), ruleServiceResponse.getData().getSave());
							articleMap.put(LiferayConstant.SAVE, earnArticles);
						}
						if(ruleServiceResponse.getData().getProtect() > 0){
							List<Map<String, Object>> earnArticles = dataService.fetchArticles(env.getProperty(LiferayConstant.FILTERPROTECT), ruleServiceResponse.getData().getProtect());
							articleMap.put(LiferayConstant.PROTECT, earnArticles);
						}
						if(ruleServiceResponse.getData().getGrow() > 0){
							List<Map<String, Object>> earnArticles = dataService.fetchArticles(env.getProperty(LiferayConstant.FILTERGROW), ruleServiceResponse.getData().getGrow());
							articleMap.put(LiferayConstant.GROW, earnArticles);
						}
				ruleServiceResponse.setArticleMap(articleMap);
			}
			return ruleServiceResponse;
		}catch(Exception e){
			logger.info("Exception in fetchAndApplyRules . Exception is: " + e.getMessage());
			return new RuleResponse(e.getMessage(), true, null);
		}
	}
}
