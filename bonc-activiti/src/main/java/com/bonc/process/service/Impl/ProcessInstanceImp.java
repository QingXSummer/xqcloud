package com.bonc.process.service.Impl;

import com.alibaba.fastjson.JSON;
import com.bonc.process.constants.ApproveStatus;
import com.bonc.process.constants.InstanceApproveStatus;
import com.bonc.process.constants.ProcessStatus;
import com.bonc.process.dao.formbean.LocalTaskVariable;
import com.bonc.process.dao.formbean.ProcessVariable;
import com.bonc.process.service.IProcessInstance;
import com.bonc.process.util.DateUtils;
import com.bonc.process.util.HttpUtil;
import com.bonc.process.util.MsgReturn;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程实例处理实现层
 * 
 * @author xiaqing
 *
 *         Created on 2017年12月25日 下午12:20:55
 */
@Service
public class ProcessInstanceImp implements IProcessInstance
{

	@Autowired
	private IdentityService identityService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private RepositoryService repositoryService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void start(ProcessVariable processVariable, LocalTaskVariable localTaskVariable, String processDefinitionId)
	{
		// 1.设置用户
		String userId = processVariable.getUserId();
		identityService.setAuthenticatedUserId(userId);
		// 2.启动一个流程实例
		// 通过key值获取发起最大版本的流程
		ProcessInstance instance = runtimeService.startProcessInstanceByKey(processDefinitionId);
		// ProcessInstance instance =
		// runtimeService.startProcessInstanceById(processDefinitionId);
		String instanceId = instance.getId();
		processVariable.setProcessInstanceId(instanceId);
		processVariable.setProcessStatus(ProcessStatus.EXECUTING.getValue());
		String processVariableJson = JSON.toJSONString(processVariable);
		runtimeService.setVariable(instanceId, instanceId, processVariableJson);
		// 3.第一个任务默认为发起者的任务，默认通过
		Task task = this.taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
		String taskId = task.getId();
		taskService.setAssignee(taskId, userId);
		String localTaskVariableJson = JSON.toJSONString(localTaskVariable);
		taskService.setVariableLocal(taskId, taskId, localTaskVariableJson);
		taskService.complete(taskId);
	}

	@Override
	public Map<String, Object> listProcessInsBystarter(String userId, int start, int limit,String processType,String startTime,String endTime)
	{

		//long total = historyService.createHistoricProcessInstanceQuery().startedBy(userId).count();
		List<ProcessVariable> listVariable = new ArrayList<>();
		List<HistoricProcessInstance> listHisInstance = new ArrayList<>();
		HistoricProcessInstanceQuery historicProcessInstanceQuery = this.historyService.createHistoricProcessInstanceQuery().includeProcessVariables().startedBy(userId);
		//根据查询条件 去增加查询条件
		if(null !=startTime && !"".trim().equals(startTime)){
			historicProcessInstanceQuery.startedAfter(DateUtils.transferDate(startTime));
		}
		if(null !=endTime && !"".trim().equals(endTime)){
			historicProcessInstanceQuery.startedBefore(DateUtils.transferDate(endTime));
		}
		listHisInstance = historicProcessInstanceQuery.orderByProcessInstanceStartTime().desc().listPage(start, limit);	

		for (HistoricProcessInstance historicProcessInstance : listHisInstance)
		{
			Map<String, Object> processVariableMap = historicProcessInstance.getProcessVariables();
			String processVariableJson = (String) processVariableMap.get(historicProcessInstance.getId());
			if (processVariableJson == null)
				throw new NullPointerException();
			ProcessVariable processVariable = JSON.parseObject(processVariableJson, ProcessVariable.class);
			//以后要在这判断 processVariable.setBusinessCode(businessCode);
			listVariable.add(processVariable);
		}
		return MsgReturn.mapOk(listVariable.size(), listVariable);
	}

	@Override
	public Map<String, Object> listProcessInsByCandidateUser(String userId, int start, int limit,String processType,String startTime,String endTime)
	{

		//long total = this.taskService.createTaskQuery().taskCandidateUser(userId).count();

		List<ProcessVariable> listVariable = new ArrayList<>();
		
		List<Task> listTask = new ArrayList<>();
		TaskQuery taskQuery = this.taskService.createTaskQuery().taskCandidateUser(userId).includeProcessVariables();
		if(null !=startTime && !"".trim().equals(startTime)){
			taskQuery.taskCreatedAfter(DateUtils.transferDate(startTime));
		}
		if(null !=endTime && !"".trim().equals(endTime)){
			taskQuery.taskCreatedBefore(DateUtils.transferDate(endTime));
		}
		
		listTask = taskQuery.orderByTaskCreateTime().desc().listPage(start, limit);
		for (Task task : listTask)
		{
			Map<String, Object> taskMap = task.getProcessVariables();
			String processVariableJson = (String) taskMap.get(task.getExecutionId());
			if (processVariableJson == null)
				throw new NullPointerException();
			ProcessVariable processVariable = JSON.parseObject(processVariableJson, ProcessVariable.class);
			processVariable.setCurrentTaskIdVo(task.getId());
			processVariable.setProcessStatus(InstanceApproveStatus.APPROVE_NEED.getValue());
			listVariable.add(processVariable);
		}

		return MsgReturn.mapOk(listVariable.size(), listVariable);
	}

	@Override
	public Map<String, Object> listFishedProcessInsByparticipantUser(String userId, int start, int limit,String processType,String startTime,String endTime)
	{

		//long total = this.historyService.createHistoricTaskInstanceQuery().taskInvolvedUser(userId).finished().count();

		List<ProcessVariable> listVariable = new ArrayList<>();
		List<HistoricTaskInstance> listHisInstance = new ArrayList<>();
		HistoricTaskInstanceQuery historicTaskInstanceQuery = this.historyService.createHistoricTaskInstanceQuery().taskInvolvedUser(userId).includeProcessVariables().finished();
		if(null !=startTime && !"".trim().equals(startTime)){
			historicTaskInstanceQuery.taskCreatedAfter(DateUtils.transferDate(startTime));
		}
		if(null !=endTime && !"".trim().equals(endTime)){
			historicTaskInstanceQuery.taskCreatedBefore(DateUtils.transferDate(endTime));
		}
		
		listHisInstance = historicTaskInstanceQuery.orderByTaskCreateTime().desc().listPage(start, limit);
		
		for (HistoricTaskInstance historicTaskInstance : listHisInstance)
		{
			Map<String, Object> processVariableMap = historicTaskInstance.getProcessVariables();
			String processVariableJson = (String) processVariableMap.get(historicTaskInstance.getProcessInstanceId());
			if (processVariableJson == null)
				throw new NullPointerException();
			ProcessVariable processVariable = JSON.parseObject(processVariableJson, ProcessVariable.class);
			if(processVariable.getProcessStatus() != ProcessStatus.BACK.getValue()){
				processVariable.setProcessStatus(InstanceApproveStatus.APPROVE_COMPLETE.getValue());
			}
			listVariable.add(processVariable);
		}
		return MsgReturn.mapOk(listVariable.size(), listVariable);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void complete(LocalTaskVariable localTaskVariable)
	{
		String taskId = localTaskVariable.getTaskId();
		this.taskService.claim(taskId, localTaskVariable.getUserId());
		String localTaskVariableJson = JSON.toJSONString(localTaskVariable);
		this.taskService.setVariableLocal(taskId, taskId, localTaskVariableJson);
		Task task = this.taskService.createTaskQuery().taskId(taskId).includeProcessVariables().singleResult();

		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(task.getProcessDefinitionId());
		ActivityImpl activityImpl = def.findActivity(task.getTaskDefinitionKey());
		List<PvmTransition> ouTransitions = activityImpl.getOutgoingTransitions();
		for (PvmTransition pvmTransition : ouTransitions)
		{
			ActivityImpl aImpl = (ActivityImpl) pvmTransition.getDestination();
			String event = checkActivityImpl(aImpl);
			Map<String, Object> map = task.getProcessVariables();
			String jsonMap = (String) map.get(task.getProcessInstanceId());
			ProcessVariable processVariable = JSON.parseObject(jsonMap, ProcessVariable.class);
			if (("endEvent").equalsIgnoreCase(event))
			{
				try
				{
					HttpUtil.get(processVariable.getClassPath());
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				updateProcessStatus(task, ProcessStatus.COMPLETED.getValue());
			}else{
				int status = processVariable.getProcessStatus() + ApproveStatus.Agree.getValue();
				updateProcessStatus(task, status);
			}
		}
		this.taskService.complete(taskId);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void rollBack(LocalTaskVariable localTaskVariable)
	{
		String taskId = localTaskVariable.getTaskId();
		this.taskService.claim(taskId, localTaskVariable.getUserId());
		String localTaskVariableJson = JSON.toJSONString(localTaskVariable);
		this.taskService.setVariableLocal(taskId, taskId, localTaskVariableJson);
		Task task = this.taskService.createTaskQuery().taskId(taskId).includeProcessVariables().singleResult();

		updateProcessStatus(task, ProcessStatus.BACK.getValue());

		this.runtimeService.deleteProcessInstance(task.getProcessInstanceId(), ApproveStatus.Back.getName());

	}

	@Override
	public Map<String, Object> getProcessInfo(String insCode,String userId)
	{
		HistoricProcessInstance instance = this.historyService.createHistoricProcessInstanceQuery()
				.includeProcessVariables().processInstanceId(insCode).singleResult();
		Map<String, Object> processVariableMap = instance.getProcessVariables();
		String processVariableJson = (String) processVariableMap.get(insCode);
		ProcessVariable processVariable = JSON.parseObject(processVariableJson, ProcessVariable.class);

		List<HistoricTaskInstance> listHis = historyService.createHistoricTaskInstanceQuery().executionId(insCode)
				.includeTaskLocalVariables().finished().orderByTaskCreateTime().desc().list();

		List<LocalTaskVariable> listLocalTasks = new ArrayList<>();
		
		
		for (HistoricTaskInstance historicTaskInstance : listHis)
		{
			Map<String, Object> map = historicTaskInstance.getTaskLocalVariables();
			String localJson = (String) map.get(historicTaskInstance.getId());
			LocalTaskVariable localTaskVariable = JSON.parseObject(localJson, LocalTaskVariable.class);	
			listLocalTasks.add(localTaskVariable);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("processVariable", processVariable);
		map.put("listLocalTasks", listLocalTasks);
		return map;
	}

	/**
	 * 修改流程参数状态
	 * 
	 * @param task
	 *            任务， 任务必须携带全局参数
	 * @param status
	 *            流程状态
	 */
	private void updateProcessStatus(Task task, int status)
	{
		System.out.println("sssss");
		String insTanceId = task.getProcessInstanceId();
		Map<String, Object> processVariableMap = task.getProcessVariables();
		Object processVariable = processVariableMap.get(insTanceId);
		ProcessVariable pVariable = JSON.parseObject(processVariable.toString(), ProcessVariable.class);
		pVariable.setProcessStatus(status);
		String pVariableJson = JSON.toJSONString(pVariable);
		this.runtimeService.setVariable(insTanceId, insTanceId, pVariableJson);
	}

	/**
	 * 根据ActivityImpl获取类型信息
	 * 
	 * @param activityImpl
	 */
	private String checkActivityImpl(ActivityImpl activityImpl)
	{
		Map<String, Object> map = activityImpl.getProperties();
		return map.get("type") == null ? "" : map.get("type").toString();
	}

	@Override
	public void deleteProcessInfo(String insCode)
	{
		// TODO Auto-generated method stub
		String processInstanceId = insCode;
		String deleteReason = "delete";
		List<HistoricTaskInstance> taskList = new ArrayList<>();
		taskList = this.historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId)
				.includeTaskLocalVariables().orderByTaskId().asc().list();
		// runtimeService.deleteProcessInstance(processInstanceId,
		// deleteReason);
		for (HistoricTaskInstance task : taskList)
		{
			System.out.println("===========================" + task);
			historyService.deleteHistoricTaskInstance(task.getId());
		}

		historyService.deleteHistoricProcessInstance(processInstanceId);

	}

}
