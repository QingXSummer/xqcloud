package com.bonc.process.service;


import com.bonc.process.dao.model.ProcessDefinitionPo;
import com.bonc.process.dao.model.ProcessDefinitionVo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 用于提供基础的流程定义服务
 *
 * @author xiaqing
 * <p>
 * Created on  2017年10月23日   上午11:42:35
 */
public interface ProcessDefinitionService {


    /**
     * 保存流程定义
     *
     * @param processDefinitionPo 流程定义参数，包括键，名称，描述
     * @return 模型ID
     * @throws UnsupportedEncodingException
     */
    String saveProcessDefinition(ProcessDefinitionPo processDefinitionPo) throws UnsupportedEncodingException;


    /**
     * 判断是否存在同名的流程
     *
     * @return 存在 true 不存在false
     */
    boolean countProcessDefinitionName(String processDefinitionName);

    /**
     * 获取所有流程
     *
     * @return 返回前台的ProcessDefinitionVo对象的List
     */
    List <ProcessDefinitionVo> listProcessByCondition();

    /**
     * 删除一个流程
     *
     * @param processDefinitionId 流程id
     */
    void deleteProcessDefinition(String processDefinitionId);

    /**
     * 部署一个流程
     *
     * @param processDefinitionId 流程id
     * @throws IOException
     */
    void updateProcessToDeploy(String processDefinitionId) throws IOException;

    /**
     * 获取流程图片
     *
     * @param processDefinitionId 流程id
     */
    byte[] getProcessImageById(String processDefinitionId);


}
