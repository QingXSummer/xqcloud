package xq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xq.service.HiService;

/**
 * 描述    :
 * Author :Qing_X
 * Date   :2019-03-19 21:17
 */

@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${server.port}")
    int port;

    @Autowired
    HiService hiService;

    @GetMapping(value = "hi")
    public String getMsg(){
        logger.info("访问hi controller");
        return hiService.hi();
    }

}

