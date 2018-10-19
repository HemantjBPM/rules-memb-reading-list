package com.liferay.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RuleDataModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String zones;
	private double netWorth;
	private double liquidity;
	private double savings;
	private double expenses;
	private double housingDebt;
	private double nonHousingDebt;
	private String outcome;
	private long goalsSettingID;
	private String name;
	private double amount;
	private double total;
	private Date dueDate;
	private String lenghthOfGoals;
	private int goalStatus;
	private long userId;
	private String typeOfGoals;
	private String color;
	private int groupId;
	private int companyId;
	private String createdBy;
	private Date createdDate;
	private Date modifiedDate;
	private int earn;
	private int manage;
	private int save;
	private int grow;
	private int protect;
	private List<String> matchedZones;
	private String ruleFlowGroup;
	private String zoneFiredCount;

	public boolean savingsBetween(String savings) {
		if (savings != null) {
			List<String> inBetweenValues = Arrays.asList(savings.split(","));
			double lower = Double.parseDouble(inBetweenValues.get(0).trim());
			double higher = Double.parseDouble(inBetweenValues.get(1).trim());
			return (this.savings > lower && this.savings < higher);
		}
		return false;
	}

	public static void main(String[] args) {
		RuleDataModel ruleDataModel = new RuleDataModel();
		ruleDataModel.setSavings(19);
		System.out.println(ruleDataModel.savingsBetween("5,20 "));
	}

	public String getZoneFiredCount() {
		return zoneFiredCount;
	}

	public void setZoneFiredCount(String zoneFiredCount) {
		this.zoneFiredCount = zoneFiredCount;
	}

	public String getRuleFlowGroup() {
		return ruleFlowGroup;
	}

	public void setRuleFlowGroup(String ruleFlowGroup) {
		this.ruleFlowGroup = ruleFlowGroup;
	}

	public List<String> getMatchedZones() {
		return matchedZones;
	}

	public void setMatchedZones(List<String> matchedZones) {
		this.matchedZones = matchedZones;
	}

	public String getZones() {
		return zones;
	}

	public void setZones(String zones) {
		this.zones = zones;
	}

	public double getNetWorth() {
		return netWorth;
	}

	public void setNetWorth(double netWorth) {
		this.netWorth = netWorth;
	}

	public double getLiquidity() {
		return liquidity;
	}

	public void setLiquidity(double liquidity) {
		this.liquidity = liquidity;
	}

	public double getSavings() {
		return savings;
	}

	public void setSavings(double savings) {
		this.savings = savings;
	}

	public double getExpenses() {
		return expenses;
	}

	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}

	public double getHousingDebt() {
		return housingDebt;
	}

	public void setHousingDebt(double housingDebt) {
		this.housingDebt = housingDebt;
	}

	public double getNonHousingDebt() {
		return nonHousingDebt;
	}

	public void setNonHousingDebt(double nonHousingDebt) {
		this.nonHousingDebt = nonHousingDebt;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public long getGoalsSettingID() {
		return goalsSettingID;
	}

	public void setGoalsSettingID(long goalsSettingID) {
		this.goalsSettingID = goalsSettingID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getLenghthOfGoals() {
		return lenghthOfGoals;
	}

	public void setLenghthOfGoals(String lenghthOfGoals) {
		this.lenghthOfGoals = lenghthOfGoals;
	}

	public int getGoalStatus() {
		return goalStatus;
	}

	public void setGoalStatus(int goalStatus) {
		this.goalStatus = goalStatus;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getTypeOfGoals() {
		return typeOfGoals;
	}

	public void setTypeOfGoals(String typeOfGoals) {
		this.typeOfGoals = typeOfGoals;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getEarn() {
		return earn;
	}

	public void setEarn(int earn) {
		this.earn = earn;
	}

	public int getManage() {
		return manage;
	}

	public void setManage(int manage) {
		this.manage = manage;
	}

	public int getSave() {
		return save;
	}

	public void setSave(int save) {
		this.save = save;
	}

	public int getGrow() {
		return grow;
	}

	public void setGrow(int grow) {
		this.grow = grow;
	}

	public int getProtect() {
		return protect;
	}

	public void setProtect(int protect) {
		this.protect = protect;
	}

	public RuleDataModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RuleDataModel(String zones, double netWorth, double liquidity, double savings, double expenses,
			double housingDebt, double nonHousingDebt, String outcome, long goalsSettingID, String name, double amount,
			double total, Date dueDate, String lenghthOfGoals, int goalStatus, long userId, String typeOfGoals,
			String color, int groupId, int companyId, String createdBy, Date createdDate, Date modifiedDate, int earn,
			int manage, int save, int grow, int protect, List<String> matchedZones, String ruleFlowGroup,
			String zoneFiredCount) {
		super();
		this.zones = zones;
		this.netWorth = netWorth;
		this.liquidity = liquidity;
		this.savings = savings;
		this.expenses = expenses;
		this.housingDebt = housingDebt;
		this.nonHousingDebt = nonHousingDebt;
		this.outcome = outcome;
		this.goalsSettingID = goalsSettingID;
		this.name = name;
		this.amount = amount;
		this.total = total;
		this.dueDate = dueDate;
		this.lenghthOfGoals = lenghthOfGoals;
		this.goalStatus = goalStatus;
		this.userId = userId;
		this.typeOfGoals = typeOfGoals;
		this.color = color;
		this.groupId = groupId;
		this.companyId = companyId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.earn = earn;
		this.manage = manage;
		this.save = save;
		this.grow = grow;
		this.protect = protect;
		this.matchedZones = matchedZones;
		this.ruleFlowGroup = ruleFlowGroup;
		this.zoneFiredCount = zoneFiredCount;
	}

	@Override
	public String toString() {
		return "RuleDataModel [zones=" + zones + ", netWorth=" + netWorth + ", liquidity=" + liquidity + ", savings="
				+ savings + ", expenses=" + expenses + ", housingDebt=" + housingDebt + ", nonHousingDebt="
				+ nonHousingDebt + ", outcome=" + outcome + ", goalsSettingID=" + goalsSettingID + ", name=" + name
				+ ", amount=" + amount + ", total=" + total + ", dueDate=" + dueDate + ", lenghthOfGoals="
				+ lenghthOfGoals + ", goalStatus=" + goalStatus + ", userId=" + userId + ", typeOfGoals=" + typeOfGoals
				+ ", color=" + color + ", groupId=" + groupId + ", companyId=" + companyId + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", earn=" + earn + ", manage="
				+ manage + ", save=" + save + ", grow=" + grow + ", protect=" + protect + ", matchedZones="
				+ matchedZones + ", ruleFlowGroup=" + ruleFlowGroup + ", zoneFiredCount=" + zoneFiredCount + "]";
	}


}
