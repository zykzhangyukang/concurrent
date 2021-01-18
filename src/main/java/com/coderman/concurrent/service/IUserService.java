package com.coderman.concurrent.service;

import com.coderman.concurrent.dto.UserDTO;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 12:23
 * @Version 1.0
 **/
public interface IUserService {

    UserDTO getByUsername(String username);

}
