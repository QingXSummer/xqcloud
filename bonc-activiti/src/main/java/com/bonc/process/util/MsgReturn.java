package com.bonc.process.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MsgReturn {
	
	public static Map<String, Object> mapOk(String msg) {
		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", true);
		return modelMap;
	}
	
	public static Map<String, Object> mapError(String msg) {
		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("message", msg);
		modelMap.put("success", false);
		return modelMap;
	}
	
	
	public static Map<String, Object> mapOk(long total, List list) {
		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("data", list);
		modelMap.put("total", total);
		modelMap.put("success", true);
		return modelMap;
	}
	
}
