package com.bonc.process.constants;

public enum ProcessStatus {
	
	/**
	 * 执行中
	 */
	EXECUTING(0,"ON"),
	
	/**
	 * 结束
	 */
	COMPLETED(-2,"END"),
	
	/**
	 * 退回
	 */
	BACK(-1,"BACK");
	
	Integer value;
	String name;
	
	ProcessStatus(Integer value, String name) {
		this.value = value;
		this.name = name;
	}
	
    public Integer getValue() {  
        return value;  
    }  
      
    public String getName() {  
        return name;  
    } 
}
