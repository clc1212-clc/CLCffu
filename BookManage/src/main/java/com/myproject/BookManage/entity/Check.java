package com.myproject.BookManage.entity;

import java.io.Serializable;

public class Check implements Serializable {
	
	private static final long serialVersionUID = 8398543916913920583L;
	
	private String username;
	private Integer isManage;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getIsManage() {
		return isManage;
	}
	public void setIsManage(Integer isManage) {
		this.isManage = isManage;
	}
	
}
