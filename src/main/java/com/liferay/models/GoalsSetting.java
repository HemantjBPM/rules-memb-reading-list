package com.liferay.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goals_setting")
public class GoalsSetting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long goalsSettingId;
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
	private Date createDate;
	private Date modifiedDate;

	public Long getGoalsSettingId() {
		return goalsSettingId;
	}

	public void setGoalsSettingId(Long goalsSettingId) {
		this.goalsSettingId = goalsSettingId;
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
		return createDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "GoalsSetting [goalsSettingId=" + goalsSettingId + ", name=" + name + ", amount=" + amount + ", total="
				+ total + ", dueDate=" + dueDate + ", lenghthOfGoals=" + lenghthOfGoals + ", goalStatus=" + goalStatus
				+ ", userId=" + userId + ", typeOfGoals=" + typeOfGoals + ", color=" + color + ", groupId=" + groupId
				+ ", companyId=" + companyId + ", createdBy=" + createdBy + ", createdDate=" + createDate
				+ ", modifiedDate=" + modifiedDate + "]";
	}

	public GoalsSetting(Long goalsSettingId, String name, double amount, double total, Date dueDate,
			String lenghthOfGoals, int goalStatus, long userId, String typeOfGoals, String color, int groupId,
			int companyId, String createdBy, Date createdDate, Date modifiedDate) {
		super();
		this.goalsSettingId = goalsSettingId;
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
		this.createDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public GoalsSetting() {
		super();
		// TODO Auto-generated constructor stub
	}

}
