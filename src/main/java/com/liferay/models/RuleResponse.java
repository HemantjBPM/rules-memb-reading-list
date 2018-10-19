package com.liferay.models;

import java.io.Serializable;

public class RuleResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String errorMessage;
	boolean error;
	RuleDataModel data;
	Object articleMap;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public RuleDataModel getData() {
		return data;
	}
	public void setData(RuleDataModel data) {
		this.data = data;
	}
	public Object getArticleMap() {
		return articleMap;
	}
	public void setArticleMap(Object articleMap) {
		this.articleMap = articleMap;
	}
	@Override
	public String toString() {
		return "RuleResponse [errorMessage=" + errorMessage + ", error=" + error + ", data=" + data + ", articleMap="
				+ articleMap + "]";
	}
	public RuleResponse(String errorMessage, boolean error, RuleDataModel data, Object articleMap) {
		super();
		this.errorMessage = errorMessage;
		this.error = error;
		this.data = data;
		this.articleMap = articleMap;
	}
	
	public RuleResponse(String errorMessage, boolean error, RuleDataModel data) {
		super();
		this.errorMessage = errorMessage;
		this.error = error;
		this.data = data;
	}
	public RuleResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


}
