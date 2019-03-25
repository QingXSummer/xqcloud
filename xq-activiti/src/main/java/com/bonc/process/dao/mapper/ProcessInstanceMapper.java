package com.bonc.process.dao.mapper;


import com.bonc.process.dao.formbean.ProcessVariableByte;

import java.util.List;

public interface ProcessInstanceMapper {

	/**
	 * 找出用户发起的流程实例
	 * @param userId 用户的userId
	 * @return
	 */
	List <ProcessVariableByte> listProcessInsBystarter(String userId);
	
}
