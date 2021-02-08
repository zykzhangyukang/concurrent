package com.coderman.concurrent.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhangyukang
 * @Date 2021/2/7 11:56
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailVO {
    private Integer id;
    private String username;
    private String email;
    private String rename;
}
