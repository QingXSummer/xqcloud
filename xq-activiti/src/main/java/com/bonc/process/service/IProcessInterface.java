package com.bonc.process.service;


import com.bonc.process.dao.formbean.LocalTaskVariable;
import com.bonc.process.dao.formbean.ProcessVariable;

public interface IProcessInterface {
    /**
     * 工作流审批结束
     *
     * @param localTaskVariable
     */
    void complete(LocalTaskVariable localTaskVariable);

    /**
     * 工作流发起
     *
     * @param task
     */
    void start(ProcessVariable processVariable);

    /**
     * 工作流回退到发起人
     *
     * @param localTaskVariable
     */
    void rejectStart(LocalTaskVariable localTaskVariable);

    /**
     * 工作流作废
     *
     * @param localTaskVariable
     */
    void end(LocalTaskVariable localTaskVariable);

    /**
     * 工作流回退上一步
     *
     * @param localTaskVariable
     */
    void rejectPre(LocalTaskVariable localTaskVariable);
}
