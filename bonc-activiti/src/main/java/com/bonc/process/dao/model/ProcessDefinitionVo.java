package com.bonc.process.dao.model;

import java.io.Serializable;

public class ProcessDefinitionVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//流程id
	public String processId;
	//流程名称
	public String processName;
	//流程版本
	public String version;
	//流程图的名称
	public String pictureName;
	//流程描述
	public String processDescription;
	//创建时间
	public String createTime;

	public String getProcessId() {
		return processId;
	}

	public String getProcessDescription() {
		return processDescription;
	}

	public void setProcessDescription(String processDescription) {
		this.processDescription = processDescription;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}


	
}
