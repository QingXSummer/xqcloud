package com.bonc;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 描述    : 配置restFul客户端
 * Author :Qing_X
 * Date   :2019-03-24 00:20
 */
@Configuration
public class RestfulConfigure {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
