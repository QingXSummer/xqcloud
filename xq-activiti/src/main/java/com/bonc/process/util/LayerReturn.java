package com.bonc.process.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LayerReturn {

	public static Map<String, Object> mapOk(List list) {
		Map<String, Object> modelMap = new HashMap<String, Object>(4);
		modelMap.put("msg", "");
		modelMap.put("code", "0");
		modelMap.put("count", list.size());
		modelMap.put("data", list);
		return modelMap;
	}
}
