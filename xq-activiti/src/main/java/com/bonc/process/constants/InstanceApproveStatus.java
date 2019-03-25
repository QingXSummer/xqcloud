package com.bonc.process.constants;

public enum InstanceApproveStatus {

	/**
	 * 已审批
	 */
	APPROVE_COMPLETE(4,"已审批"),
	/**
	 * 待审批
	 */
	APPROVE_NEED(3,"待审批"),
		
	/**
	 * 业支审批
	 */
	CMCC_COMPLETE(1,"未执行"),
	
	/**
	 * 数据实施
	 */
	BONC_COMPLETE(2,"未实施"),

	/**
	 * 
	 */
	SZZX_COMPLETE(3,"已实施");
	
	Integer value;
	String name;
	
	InstanceApproveStatus(Integer value, String name) {
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
