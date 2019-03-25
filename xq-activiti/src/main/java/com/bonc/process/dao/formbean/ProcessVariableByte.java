package com.bonc.process.dao.formbean;

import java.io.Serializable;

/**
 * 用于接受从数据库读取的byte[] 数据，进行反序列化
 * @author xiaqing
 *
 * Created on  2017年12月25日   下午4:07:37
 */
public class ProcessVariableByte implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用于保存流程实例ID
	private String processInstanceId;
	//用于接受流程信息
	private byte [] variables;
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public byte[] getVariables() {
		return variables;
	}
	public void setVariables(byte[] variables) {
		this.variables = variables;
	}
	
}
