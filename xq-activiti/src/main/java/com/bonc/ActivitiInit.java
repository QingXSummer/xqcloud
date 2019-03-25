package com.bonc;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations= {"classpath:applicationContext.xml","classpath:activiti.cfg.xml"})
public class ActivitiInit {

}