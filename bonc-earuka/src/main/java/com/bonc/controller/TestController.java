package com.bonc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述    :
 * Author :Qing_X
 * Date   :2019-03-19 21:17
 */

@RestController
public class TestController {

    @GetMapping(value = "msg")
    public String getMsg(){
        return "com/bonc";
    }

}


