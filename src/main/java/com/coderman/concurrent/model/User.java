package com.coderman.concurrent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2021/1/18 12:29
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String rename;
    private String telphone;
    private Date birthday;
    private String sex;
    private Integer age;
    private String status;
}
