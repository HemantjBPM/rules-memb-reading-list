package com.liferay.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.liferay.models.RuleDataModel;
import com.liferay.models.RuleResponse;
import com.liferay.utils.CommonUtils;
import com.liferay.utils.LiferayConstant;

@Service
public class DataService {

	private static final Logger logger = Logger.getLogger(DataService.class);

	@Autowired
	private Environment env;

	public RuleResponse getData() {
		RuleDataModel ruleDataModel = new RuleDataModel();
		try (Connection connection = CommonUtils.getDBConnection(env.getProperty(LiferayConstant.DRIVERCLASS),
				env.getProperty(LiferayConstant.DBURL), env.getProperty(LiferayConstant.USERNAME),
				env.getProperty(LiferayConstant.PASSWORD)); Statement statement = connection.createStatement();) {
			ResultSet rs = statement.executeQuery(env.getProperty(LiferayConstant.SELECTSQLGOAL));
			while (rs.next()) {
				ruleDataModel.setGoalsSettingID(rs.getInt(LiferayConstant.GOALSETTINGID));
				ruleDataModel.setTypeOfGoals(rs.getString(LiferayConstant.TYPEOFGOAL));
				ruleDataModel.setName(rs.getString(LiferayConstant.NAME));
				ruleDataModel.setAmount(rs.getDouble(LiferayConstant.AMOUNT));
				ruleDataModel.setTotal(rs.getDouble(LiferayConstant.TOTAL));
				ruleDataModel.setDueDate(rs.getDate(LiferayConstant.DUEDATE));
				ruleDataModel.setLenghthOfGoals(rs.getString(LiferayConstant.LENGTHOFGOALS));
				ruleDataModel.setGoalStatus(rs.getInt(LiferayConstant.GOALSTATUS));
				ruleDataModel.setUserId(rs.getLong(LiferayConstant.USERID));
				ruleDataModel.setColor(rs.getString(LiferayConstant.COLOR));
				ruleDataModel.setCompanyId(rs.getInt(LiferayConstant.COMPANYID));
				ruleDataModel.setCreatedBy(rs.getString(LiferayConstant.CREATEDBY));
				ruleDataModel.setModifiedDate(rs.getDate(LiferayConstant.MODIFIEDDATE));
				ruleDataModel.setCreatedDate(rs.getDate(LiferayConstant.CREATEDATE));
				ruleDataModel.setGroupId(rs.getInt(LiferayConstant.GROUPID));
				logger.info("Data fetch from db. Respose object is: " + ruleDataModel);
				return new RuleResponse("", false, ruleDataModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception in Data fetch from db. Exception is: " + e.getMessage());
			return new RuleResponse("Exception while fetching data from db. Exception: " + e.toString(), true,
					ruleDataModel);
		}
		return null;
	}
	
	public RuleResponse getLatestTypeOfGoal(long userID) {
		logger.info("Input paramter is: " + userID);
		RuleDataModel ruleDataModel = new RuleDataModel();
		try (Connection connection = CommonUtils.getDBConnection(env.getProperty(LiferayConstant.DRIVERCLASS),
				env.getProperty(LiferayConstant.DBURL), env.getProperty(LiferayConstant.USERNAME),
				env.getProperty(LiferayConstant.PASSWORD)); 
				PreparedStatement preparedStatement = connection.prepareStatement(env.getProperty(LiferayConstant.SELECTCREATEGOAL));) {
			System.out.println("Query is: " + env.getProperty(LiferayConstant.SELECTCREATEGOAL));
			preparedStatement.setLong(1, userID);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ruleDataModel.setTypeOfGoals(rs.getString(LiferayConstant.TYPEOFGOAL));
				logger.info("Data fetch from db. Respose object is: " + ruleDataModel);
				return new RuleResponse("", false, ruleDataModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception in Data fetch from db. Exception is: " + e.getMessage());
			return new RuleResponse("Exception while fetching data from db. Exception: " + e.toString(), true,
					ruleDataModel);
		}
		return null;
	}
	
	public List<Map<String, Object>> fetchArticles(String articleType, int limit) throws Exception{
		logger.info("*** Fetching article based on article Type: " + articleType + " Number of records to fetch: " + limit);
		List<Map<String, Object>> articlesData = new ArrayList<>();
		try (Connection connection = CommonUtils.getDBConnection(env.getProperty(LiferayConstant.DRIVERCLASS),
				env.getProperty(LiferayConstant.DBURL), env.getProperty(LiferayConstant.USERNAME),
				env.getProperty(LiferayConstant.PASSWORD)); 
				PreparedStatement preparedStatement = connection.prepareStatement(env.getProperty(LiferayConstant.SELECTARTICLE));) {
			System.out.println("Query is: " + env.getProperty(LiferayConstant.SELECTARTICLE));
			preparedStatement.setString(1, "%" + articleType + "%");
			preparedStatement.setInt(2, limit);
			ResultSet rs = preparedStatement.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next()) {
				Map<String, Object> dataMap = new LinkedHashMap<>();
				for(int i = 1; i <= metaData.getColumnCount(); i++){
					dataMap.put(metaData.getColumnName(i), rs.getObject(i));
				}
				articlesData.add(dataMap);
			}
			logger.info("Data fetch from db. Respose object is: Updated" + articlesData);
			return articlesData;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception in Data fetch from db. Exception is: " + e.getMessage());
			throw e;
		}
	}
}
