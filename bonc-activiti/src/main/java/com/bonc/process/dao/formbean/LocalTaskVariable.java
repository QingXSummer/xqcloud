package com.bonc.process.dao.formbean;

import java.io.Serializable;
import java.util.Map;

/**
 * 任务自定义参数
 * @author SC
 *
 */
public class LocalTaskVariable implements Serializable{
	/**
	 * serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 审批人id
	 */
    private String userId;
    /**
     * 审批人姓名
     */
    private String userName;
    /**
     * 审批人备注
     */
    private String comment;
    /**
     * 审批动作：0,退回发起人,1,同意,2,回退上一步,3,作废,
     */
    private Integer state;
    /**
     * 电话号码
     */
    private String teleNo;
    /**
     * 任务开始
     */
    private String createTime;
    /**
     * 自定义变量
     */
    private Map<String,Object> taskVariables;
    
    private String taskId;
    
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Map<String, Object> getTaskVariables() {
		return taskVariables;
	}
	public void setTaskVariables(Map<String, Object> taskVariables) {
		this.taskVariables = taskVariables;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTeleNo() {
		return teleNo;
	}
	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
