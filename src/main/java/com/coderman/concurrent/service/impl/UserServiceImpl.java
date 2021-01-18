package com.coderman.concurrent.service.impl;

import com.coderman.concurrent.dto.UserDTO;
import com.coderman.concurrent.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 12:24
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements IUserService {
    @Override
    public UserDTO getByUsername(String username) {
        return new UserDTO(username,"123456");
    }
}
