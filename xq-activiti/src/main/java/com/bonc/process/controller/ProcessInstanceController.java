package com.bonc.process.controller;

import com.bonc.process.constants.ApproveStatus;
import com.bonc.process.dao.formbean.LocalTaskVariable;
import com.bonc.process.dao.model.ProcessUser;
import com.bonc.process.service.IProcessInstance;
import com.bonc.process.service.ProcessInstanceService;
import com.bonc.process.util.DateUtils;
import com.bonc.process.util.MsgReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

/**
 * 处理流程实例
 * 
 * @author xiaqing
 *
 *         Created on 2017年10月20日 下午2:36:28
 */
@Controller
@RequestMapping("/instance")
public class ProcessInstanceController {

	@Autowired
	private ProcessInstanceService instanceService;
	@Autowired
	private IProcessInstance iProcessInstance;

	@RequestMapping(value = "listProcessInsByUser")
	@ResponseBody
	public Map<String, Object> listProcessInsByCandidateUser(int type, int start, int limit, String processType,
			String startTime, String endTime, HttpServletRequest request) {
		ProcessUser user = (ProcessUser) request.getSession().getAttribute("processUser");
		try {

			if (0 == type) {
				return iProcessInstance.listProcessInsBystarter(user.getUserId(), start, limit, processType, startTime,
						endTime);
			} else if (1 == type) {
				return iProcessInstance.listProcessInsByCandidateUser(user.getUserId(), start, limit, processType,
						startTime, endTime);
			} else if (2 == type) {
				return iProcessInstance.listFishedProcessInsByparticipantUser(user.getUserId(), start, limit,
						processType, startTime, endTime);
			} else {
				return MsgReturn.mapError("参数错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MsgReturn.mapError("程序异常");
		}
	}

	/**
	 * 获取实例图片信息
	 * 
	 * @param instanceId
	 */
	@Transactional
	@RequestMapping("getInstanceImage")
	public void getInstanceImage(String instanceId, HttpServletResponse response) {
		try {
			InputStream imageStream = instanceService.getInstanceImage(instanceId);
			// 输出资源内容到相应对象
			byte[] b = new byte[1024];
			int len;
			while ((len = imageStream.read(b, 0, 1024)) != -1) {
				response.getOutputStream().write(b, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "insApprove")
	@ResponseBody
	public Map<String, Object> insApprove(String insCode, Integer agree, String comment, String taskId,
			HttpServletRequest request) {
		ProcessUser user = (ProcessUser) request.getSession().getAttribute("processUser");
		LocalTaskVariable localTaskVariable = new LocalTaskVariable();
		localTaskVariable.setComment(comment);
		localTaskVariable.setCreateTime(DateUtils.transferDateT(new Date()));
		localTaskVariable.setTaskId(taskId);
		localTaskVariable.setUserId(user.getUserId());
		localTaskVariable.setUserName(user.getUserName());
		localTaskVariable.setTeleNo(user.getTel());
		try {
			if (ApproveStatus.Agree.getValue() == agree) {
				localTaskVariable.setState(ApproveStatus.Agree.getValue());
				iProcessInstance.complete(localTaskVariable);
				return MsgReturn.mapOk("成功");
			} else if (ApproveStatus.Back.getValue() == agree) {
				localTaskVariable.setState(ApproveStatus.Back.getValue());
				iProcessInstance.rollBack(localTaskVariable);
				return MsgReturn.mapOk("成功");
			} else {
				return MsgReturn.mapError("参数异常");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MsgReturn.mapError("程序异常");
		}
	}

	@RequestMapping(value = "getProcessInfo")
	@ResponseBody
	public Map<String, Object> getProcessInfo(String insCode, HttpServletRequest request) {
		try {
			ProcessUser user = (ProcessUser) request.getSession().getAttribute("processUser");
			return iProcessInstance.getProcessInfo(insCode,user.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			return MsgReturn.mapError("程序异常");
		}

	}

	@RequestMapping(value = "deleteProcessInsByInsCode")
	@ResponseBody
	public Map<String, Object> deleteProcessInfoByInsCode(String insCode) {

		try {
			iProcessInstance.deleteProcessInfo(insCode);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgReturn.mapError("程序异常");
		}
		return MsgReturn.mapOk("删除成功");
	}

}
