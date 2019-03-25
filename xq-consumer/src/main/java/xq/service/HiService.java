package xq.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 描述    :
 * Author :Qing_X
 * Date   :2019-03-24 00:23
 */
@Service
public class HiService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(){
        return restTemplate.getForObject("http://XQ-USER-SERVICE/hi", String.class);
    }


    public String hiError(){
        return "It's a error!";
    }

}
