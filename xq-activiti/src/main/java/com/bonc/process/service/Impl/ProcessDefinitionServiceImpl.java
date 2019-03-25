package com.bonc.process.service.Impl;

import com.bonc.process.dao.mapper.ProcessExMapper;
import com.bonc.process.dao.model.ProcessDefinitionPo;
import com.bonc.process.dao.model.ProcessDefinitionVo;
import com.bonc.process.service.ProcessDefinitionService;
import com.bonc.process.util.DateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于提供基础的流程定义服务
 * @author xiaqing
 *
 * Created on  2017年10月23日   上午11:42:35
 */
@Service
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    ProcessExMapper processExMapper;
    
    /**
     * 保存流程定义
     * @param processDefinitionPo 流程定义参数，包括键，名称，描述
     * @return 模型ID
     * @throws UnsupportedEncodingException
     */
    @Transactional
    public String saveProcessDefinition(ProcessDefinitionPo processDefinitionPo) throws UnsupportedEncodingException{
    	ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");  
        editorNode.put("resourceId", "canvas");  
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");  
        editorNode.put("stencilset", stencilSetNode);  
        Model modelData = repositoryService.newModel();
        
        ObjectNode modelObjectNode = objectMapper.createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinitionPo.getProcessDefinitionName());  
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);  
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, StringUtils.defaultString(processDefinitionPo.getProcessDefinitionDes()));
        modelData.setMetaInfo(modelObjectNode.toString());  
        modelData.setName(processDefinitionPo.getProcessDefinitionName());  
        modelData.setKey(processDefinitionPo.getProcessDefinitionKey());
        
        repositoryService.saveModel(modelData);  
        repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8")); 
        
        return modelData.getId();
    }
    
    
    /**
     * 判断是否存在同名的流程
     * @return 存在 true 不存在false
     */
    public boolean countProcessDefinitionName(String processDefinitionName){
    	
    	List<Model> modelList = repositoryService.createModelQuery().list();
    	
    	for(Model model : modelList){
    		if(model.getName().equals(processDefinitionName)){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    /**
     * 获取所有流程
     * @return 返回前台的ProcessDefinitionVo对象的List
     */
    @Transactional
    public List<ProcessDefinitionVo> listProcessByCondition(){
    	//获取所有流程模型
    	List<Model> modelList = repositoryService.createModelQuery().orderByCreateTime().desc().list();
    	//存放返回前台的	ProcessDefinitionVo对象
    	List<ProcessDefinitionVo> processDefinitionVoList = new ArrayList<>();
    	
    	ProcessDefinitionVo p;
    	//组装前台显示的信息
    	for(Model model : modelList){
    		p = new ProcessDefinitionVo();
    		p.setProcessId(model.getId());
    		p.setProcessName(model.getName());
    		p.setVersion(""+model.getVersion());
    		p.setCreateTime(DateUtils.transferDateT(model.getCreateTime()));
    		processDefinitionVoList.add(p);
    	}
    	
    	return processDefinitionVoList;
    }
    
    /**
     * 删除一个流程
     * @param processDefinitionId 流程id
     */
    @Transactional
    public void deleteProcessDefinition(String processDefinitionId){

    		//获取所有以及部署的流程 
    		List<ProcessDefinition> ProcessDefinitionList = repositoryService.createProcessDefinitionQuery().list();
    		
    		for(ProcessDefinition p : ProcessDefinitionList ){
    			//判断如果流程已经部署 先解除部署
    			if(processDefinitionId.equals(p.getDeploymentId())){
    				repositoryService.deleteDeployment(processDefinitionId, true);
    				break;
    			}
    		}
    		//删除流程模型
			repositoryService.deleteModel(processDefinitionId);

    }
    
    /**
     * 部署一个流程
     * @param processDefinitionId 流程id
     * @throws IOException 
     * @throws JsonProcessingException
     */
    @Transactional
	public void updateProcessToDeploy(String processDefinitionId) throws IOException {
    	
    	//根据id获取模型
		Model modelData = repositoryService.getModel(processDefinitionId);
		//获取模型节点
		JsonNode modelNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(processDefinitionId));
		byte[] bpmnBytes = null;
		//将模型节点转化为BpmnModel
		BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
		//根据BpmnModel转化成xml的二进制文件
		bpmnBytes = new BpmnXMLConverter().convertToXML(model, "utf-8");
		//根据流程名称命名xml文件名称
		String processName = modelData.getName() + ".bpmn20.xml";
		//将流程部署
		repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes)).deploy();

	}
    /**
     * 获取流程图片
     * @param processDefinitionId 流程id
     */
    @Transactional
    public byte[] getProcessImageById(String processDefinitionId){
    	
		byte[] imageBytes = (byte[]) (processExMapper.getImageByteByModelId(processDefinitionId)).get("byte");

		return imageBytes;
    	
    }

}
