package com.coderman.concurrent.controller;

import com.coderman.concurrent.dto.UserDTO;
import com.coderman.concurrent.vo.response.RestApiResponse;
import com.coderman.concurrent.vo.response.error.BusinessErrorEnum;
import com.coderman.concurrent.vo.response.error.BusinessException;
import com.coderman.concurrent.service.IUserService;
import com.coderman.concurrent.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/hello")
    public RestApiResponse<Date> hello(){
     throw new BusinessException(BusinessErrorEnum.PARAMETER_ERROR,"用户名不能为空");
    }

    @GetMapping(value = "/info/{username}")
    public RestApiResponse<UserVO> info(@PathVariable(value = "username") String username){
        UserDTO userDTO=userService.getByUsername(username);
        return RestApiResponse.success(new UserVO(userDTO.getUsername()));
    }
}
