package com.bonc.process.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bonc.process.dao.formbean.LocalTaskVariable;
import com.bonc.process.dao.formbean.ProcessVariable;
import com.bonc.process.service.IProcessInstance;
import com.bonc.process.util.MsgReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/process")
public class ProcessRestController {
	
	@Autowired
	private IProcessInstance instanceService;
	
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public Map<String, Object>startProcess(String param, HttpServletRequest request){
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = JSON.parseObject(param, HashMap.class);
			
			ProcessVariable processVariable = JSONObject.toJavaObject((JSONObject)map.get("processVariable"), ProcessVariable.class);
			LocalTaskVariable localTaskVariable = JSONObject.toJavaObject((JSONObject)map.get("localTaskVariable"), LocalTaskVariable.class);
			String processDefinitionId =map.get("processDefinitionId").toString();
			instanceService.start(processVariable, localTaskVariable, processDefinitionId);
			return MsgReturn.mapOk("发起成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MsgReturn.mapError("数据异常");
		}
	}
	
}
