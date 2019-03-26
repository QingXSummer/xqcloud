package com.bonc.process.dao.model;

import java.io.Serializable;

/**
 * 用于创建新的流程定义
 * @author xiaqing
 *
 * Created on  2017年10月23日   上午10:59:48
 */
public class ProcessDefinitionPo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//流程名称
	private String processDefinitionName;
	//流程主键
	private String processDefinitionKey;
	//流程描述
	private String processDefinitionDes;

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getProcessDefinitionDes() {
		return processDefinitionDes;
	}

	public void setProcessDefinitionDes(String processDefinitionDes) {
		this.processDefinitionDes = processDefinitionDes;
	}
	
	
	
}
