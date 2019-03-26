package com.bonc.process.dao.mapper;

import java.util.Map;

public interface ProcessExMapper {

	/**
	 * 通过模型ID获取流程模型图
	 * @param modelId 流程模型ID
	 * @return
	 */
	Map<String,Object> getImageByteByModelId(String modelId);
	
}
