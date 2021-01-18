package com.coderman.concurrent.controller;

import com.coderman.concurrent.response.RestApiResponse;
import com.coderman.concurrent.response.error.BusinessErrorEnum;
import com.coderman.concurrent.response.error.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * @Author zhangyukang
 * @Date 2021/1/18 11:52
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/hello")
    public RestApiResponse<Date> hello(){
     throw new BusinessException(BusinessErrorEnum.PARAMETER_ERROR,"用户名不能为空");
    }
}
