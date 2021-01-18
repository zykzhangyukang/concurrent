package com.coderman.concurrent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 12:23
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String password;
}
