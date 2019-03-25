package com.bonc.process.constants;

public enum ApproveStatus {
	/**
	 * 退回到发起人
	 */
	Back(-1,"退回发起人"),
	/**
	 * 同意
	 */
	Agree(1,"通过"),
	/**
	 * 退回到上一步
	 */
	DisAgree(2,"回退上一步"),
	/**
	 * 作废
	 */
	Abandon(3,"废弃"),
	
	/**
	 * 发起
	 */
	Start(4,"发起");
	
	Integer value;
	String name;
	
	ApproveStatus(Integer value, String name) {
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
