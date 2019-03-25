package com.bonc.process.service;


import com.bonc.process.dao.formbean.LocalTaskVariable;
import com.bonc.process.dao.formbean.ProcessVariable;

import java.util.Map;

/**
 * 用于处理与用于相关的各个流程和任务
 *
 * @author xiaqing
 * <p>
 * Created on 2017年12月25日 上午11:06:40
 */
public interface IProcessInstance {


    /**
     * 功能描述: 发起任务
     *
     * @param processVariable
     * @param localTaskVariable
     * @param processDefinitionId
     * @return void
     * @author Qing_X
     * @date 2019-44-25 23:44:36
     */
    void start(ProcessVariable processVariable, LocalTaskVariable localTaskVariable, String processDefinitionId);

    /**
     * 工作流审批结束
     *
     * @param localTaskVariable
     */
    void complete(LocalTaskVariable localTaskVariable);

    /**
     * 驳回任务 直接驳回到原始
     *
     * @param localTaskVariable 任务变量
     */
    void rollBack(LocalTaskVariable localTaskVariable);

    /**
     * 找出用户发起的流程实例
     *
     * @param userId 用户id
     * @param start  起始
     * @param limit  间隔
     * @return
     */
    Map <String, Object> listProcessInsBystarter(String userId, int start, int limit, String processType, String startTime, String endTime);

    /**
     * 根据用户ID查找出候选的处理流程
     *
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    Map <String, Object> listProcessInsByCandidateUser(String userId, int start, int limit, String processType, String startTime, String endTime);

    /**
     * 找出流程的参与人参与的所有已完成的任务
     *
     * @param userId
     * @param start
     * @param limit
     * @return
     */
    Map <String, Object> listFishedProcessInsByparticipantUser(String userId, int start, int limit, String processType, String startTime, String endTime);

    /**
     * 获取流程信息
     *
     * @param insCode 流程实例insCode
     * @return
     */
    Map <String, Object> getProcessInfo(String insCode, String userId);

    /**
     * 删除流程信息
     *
     * @author wanghui
     */
    void deleteProcessInfo(String insCode);
}
