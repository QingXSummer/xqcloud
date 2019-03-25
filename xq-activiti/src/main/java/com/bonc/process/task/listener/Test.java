package com.bonc.process.task.listener;

import com.alibaba.fastjson.JSON;
import com.bonc.process.util.SpringContextUtil;
import com.bonc.process.dao.formbean.ProcessVariable;
import com.bonc.process.util.HttpUtil;
import com.bonc.process.util.ProcessConfigUtils;
import org.activiti.engine.HistoryService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.history.HistoricProcessInstance;

import java.io.IOException;
import java.util.Map;

public class Test implements TaskListener{

	@Override
	public void notify(DelegateTask delegateTask) {
		String instanceId = delegateTask.getProcessInstanceId();
		HistoryService historyService = (HistoryService) SpringContextUtil.getBean("historyService");
		HistoricProcessInstance instance = historyService.createHistoricProcessInstanceQuery()
							.processInstanceId(instanceId).includeProcessVariables().singleResult();
		Map<String, Object> map = instance.getProcessVariables();
		String jsonMap = (String) map.get(instanceId);
		ProcessVariable processVariable = JSON.parseObject(jsonMap, ProcessVariable.class);
		try {
			HttpUtil.get(ProcessConfigUtils.approvePassCallBack+processVariable.getBusinessCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
