package com.bonc.process.dao.formbean;

import java.io.Serializable;

/**
 * 基础表单类型
 * 
 * @author xiaqing
 *
 * Created on  2017年10月18日   上午11:15:43
 */
public  class ProcessVariable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//标题
	private String title;
	//申请人主键
	private String userId;
	//申请人
	private String proposer;
	//业务类型编码
	private String businessCode;
	//业务主键
	private String businessKey;
	//申请日期 yyyy-MM-dd HH:mm:ss
	private String applyDate;
	//所属组织
	private String orgName;
	//与业务关联的流程实例主键
	private String processInstanceId;
	//用于前台展示当前流程关联的任务，不写如数据库
	private String currentTaskIdVo;
	/**
	 * 流程单状态
	 */
	private Integer processStatus;
    /**
     * 流程审批时回调的接口
     */
	private String classPath;	
	/**
	 * 待办展里的详情页信息
	 */
	private String url;
	
	
	
	public String getCurrentTaskIdVo() {
		return currentTaskIdVo;
	}

	public void setCurrentTaskIdVo(String currentTaskIdVo) {
		this.currentTaskIdVo = currentTaskIdVo;
	}

	public Integer getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}


	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
