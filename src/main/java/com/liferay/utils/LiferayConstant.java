package com.liferay.utils;

public interface LiferayConstant {

	String GOALSETTINGID = "GoalSettingID";
	String TYPEOFGOAL = "TypeOfGoal";
	String CREATEDATE = "CreateDate";
	String GROUPID = "GroupID";
	String NAME = "Name";
	String AMOUNT = "Amount";
	String TOTAL = "Total";
	String DUEDATE = "DueDate";
	String LENGTHOFGOALS = "LenghthOfGoals";
	String GOALSTATUS = "GoalStatus";
	String USERID = "UserID";
	String COLOR = "Color";
	String COMPANYID = "companyId";
	String CREATEDBY = "CreatedBy";
	String MODIFIEDDATE = "ModifiedDate";
	String URL = "URL";
	String TYPE = "Type";
	String IMAGE = "Image";

	String DBURL = "db.url";
	String DRIVERCLASS = "db.driver.class";
	String USERNAME = "db.user";
	String PASSWORD = "db.password";
	String SELECTSQLGOAL = "sql.select.goals.settings";
	String SELECTCREATEGOAL = "sql.select.create.goals.settings";
	String SELECTARTICLE = "sql.select.article";
	
	String CREATEGOALMAPPING = "create.goal.mapping";
	
	String CREATEGOALLIMIT = "filter.create.goal.count";
	
	String CREATEGOAL = "CreateGoal";
	String ZONE1 = "Zone 1";
	String ZONE2 = "Zone 2";
	String ZONE3 = "Zone 3";
	String ZONE4 = "Zone 4";
	String ZONE = "Zone ";
	
	String DEFAULTZONE = "default.zone";
	String DEFAULTARTICLECOUNT = "default.count.articles";
	String DEFAULTZONECOUNT = "zone.size";
	
	String FILTEREARN = "filter.earn";
	String FILTERSAVE = "filter.save";
	String FILTERPROTECT = "filter.protect";
	String FILTERMANAGE = "filter.manage";
	String FILTERGROW = "filter.grow";
	
	boolean ASC = Boolean.TRUE;
	boolean DESC = Boolean.FALSE;
	
	String EARN = "Earn";
	String MANAGE = "Manage";
	String GROW = "Grow";
	String SAVE = "Save";
	String PROTECT = "Protect";
	
	String RULEFILELOCATION = "rule.file.location";
	String RULEPATHCONSTANT = "src/main/resources/";
	String DEFAULRULEFLOWGROUPNAME = "Members Ratio";
	
	String VIEWPREFIX = "/WEB-INF/views/";
	String VIEWSUFFIX = ".jsp";

	String DATEFORMAT = "dd-MM-yyyy";

	String HTTPGET = "GET";
	String HTTPPOST = "POST";
	String CONTENTTYPE = "application/json";

	String APPLYRULES = "/applyRules";
	String UPDATERULES = "/updateRules";
	String GETDATAANDAPPLYRULES = "/getAndApplyRules";
	String FETCHDATAANDAPPLYRULES = "/fetchAndApplyRules";
	String FETCHDATAANDAPPLYRULESWITHRULEFLOWGROUPNAME = "/fetchAndApplyRules/{ruleFlowGroupName}";

}
