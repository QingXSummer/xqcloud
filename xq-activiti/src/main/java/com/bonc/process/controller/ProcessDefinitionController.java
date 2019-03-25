package com.bonc.process.controller;

import com.bonc.process.dao.model.ProcessDefinitionPo;
import com.bonc.process.dao.model.ProcessDefinitionVo;
import com.bonc.process.service.ProcessDefinitionService;
import com.bonc.process.util.LayerReturn;
import com.bonc.process.util.MsgReturn;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 处理流程定义
 * @author xiaqing
 *
 * Created on  2017年10月20日   下午2:35:22
 */
@Controller
@RequestMapping("/processDefinition")
public class ProcessDefinitionController {

	@Autowired
	private ProcessDefinitionService definitionService;
	
	@RequestMapping(value="handle")
	@ResponseBody
	public Map<String, Object> processHandle(String type) {
	
		return null;
	}
	
	@RequestMapping(value="saveProcessDefinition")
	@ResponseBody
	public Map<String, Object> saveProcessDefinition(ProcessDefinitionPo processDefinitionPo, HttpServletRequest request, HttpServletResponse response){
		
		try {
			String processDefinitionName = processDefinitionPo.getProcessDefinitionName();
					
			if (StringUtils.isNotBlank(processDefinitionName)) {
				//判断这个流程名称是否重名
				if(definitionService.countProcessDefinitionName(processDefinitionName)){
					//因为流程部署时 需要根据流程名称生成xml文件以及流程图  需要保证流程名称的唯一性
					//0 表示已经存在同名流程 
					return MsgReturn.mapOk("0");
				}
				//不存在  生成流程模型
				String modelId = definitionService.saveProcessDefinition(processDefinitionPo);
				//新增流程成功 将模型id传到前台
				return MsgReturn.mapOk(modelId);
			}
			return MsgReturn.mapError("请保证所有信息合法填入");
		} catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return MsgReturn.mapError("新增流程失败");
		}
	}
	
	@RequestMapping(value="listProcessByCondition")
	@ResponseBody
	public Map<String, Object> listProcessByCondition(){
		
		List<ProcessDefinitionVo> list = definitionService.listProcessByCondition();

		return LayerReturn.mapOk(list);
	}
	
	@RequestMapping(value="deleteProcessDefinition")
	@ResponseBody
	public Map<String, Object> deleteProcessDefinition(String processDefinitionId){
		
		try {
			definitionService.deleteProcessDefinition(processDefinitionId);
			return MsgReturn.mapOk("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MsgReturn.mapError("删除失败");
		}

	}
	
	@RequestMapping(value="updateProcessToDeploy")
	@ResponseBody
	public Map<String, Object> updateProcessToDeploy(String processDefinitionId){
		   
		try {
			definitionService.updateProcessToDeploy(processDefinitionId);
			return MsgReturn.mapOk("部署成功");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return MsgReturn.mapError("部署失败,请保证流程图已经存在");
	}
	
	@RequestMapping(value="getProcessImageById")
	@ResponseBody
	public void getProcessImageById(String processDefinitionId, HttpServletResponse response){

			byte[] bytes = definitionService.getProcessImageById(processDefinitionId);
			try {
				response.getOutputStream().write(bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	
	
	
	public static void main(String[] args) {
		
	}
	
}
