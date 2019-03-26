package com.bonc.process.dao.model;

import java.io.Serializable;

public class ProcessUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用户ID
	private String userId;
	//用户名
	private String userName;
	//密码
	private String password;
	//电话
	private String tel;
	//邮箱
	private String mail;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
}
