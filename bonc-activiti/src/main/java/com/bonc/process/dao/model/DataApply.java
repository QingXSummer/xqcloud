package com.bonc.process.dao.model;

import java.io.Serializable;

public class DataApply implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//需求名称
	private String demandName;
	//需求类型
	private String demandType;
	//需求描述
	private String demandDes;
	//需求输出结果
	private String demandResult;
	//申请数据范围
	private String applyScope;
	//期待上线时间
	private String expectOnline;
	//发起用户
	private String userId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDemandName() {
		return demandName;
	}
	public void setDemandName(String demandName) {
		this.demandName = demandName;
	}
	public String getDemandType() {
		return demandType;
	}
	public void setDemandType(String demandType) {
		this.demandType = demandType;
	}
	public String getDemandDes() {
		return demandDes;
	}
	public void setDemandDes(String demandDes) {
		this.demandDes = demandDes;
	}
	public String getDemandResult() {
		return demandResult;
	}
	public void setDemandResult(String demandResult) {
		this.demandResult = demandResult;
	}
	public String getApplyScope() {
		return applyScope;
	}
	public void setApplyScope(String applyScope) {
		this.applyScope = applyScope;
	}
	public String getExpectOnline() {
		return expectOnline;
	}
	public void setExpectOnline(String expectOnline) {
		this.expectOnline = expectOnline;
	}
	
}
