package com.liferay.models;

import java.io.Serializable;

public class Article implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String articleId;
	private String url;
	private String image;
	private String type;
	private String name;
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Article() {
		super();
	}
	public Article(String articleId, String url, String image, String type, String name) {
		super();
		this.articleId = articleId;
		this.url = url;
		this.image = image;
		this.type = type;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", url=" + url + ", image=" + image + ", type=" + type + ", name="
				+ name + "]";
	}
	
	
}
