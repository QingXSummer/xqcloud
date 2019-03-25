package com.bonc.process.service;

import java.io.IOException;
import java.io.InputStream;

/**
 * 提供产生流程实例中各种服务
 *
 * @author xiaqing
 * <p>
 * Created on  2017年10月20日   下午2:42:10
 */
public interface ProcessInstanceService {

    /**
     * 生成流程图片
     *
     * @throws IOException
     * @processInstanceId 流程实例ID
     */
    InputStream getInstanceImage(String processInstanceId) throws IOException;


}
