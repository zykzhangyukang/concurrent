package com.coderman.concurrent.controller;

import com.coderman.concurrent.model.User;
import com.coderman.concurrent.service.IUserService;
import com.coderman.concurrent.vo.UserDetailVO;
import com.coderman.concurrent.vo.response.RestApiResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2021/2/7 11:38
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 获取所有用户信息
     *
     * @return
     */
    @GetMapping(value = "/findAll")
    public RestApiResponse<List<UserDetailVO>> findAll() {
        List<UserDetailVO> userDetailVOList = new ArrayList<>();
        userService.findAll().forEach(u -> userDetailVOList.add(convertFromDataObject(u)));
        return RestApiResponse.success(userDetailVOList);
    }

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/findById/{id}")
    public RestApiResponse<UserDetailVO> findById(@PathVariable(value = "id") Integer id) {
        User user = userService.findById(id);
        return RestApiResponse.success(convertFromDataObject(user));
    }

    /**
     * DataObject转成VO对象
     *
     * @param u
     * @return
     */
    private UserDetailVO convertFromDataObject(User u) {
        if (u == null) {
            return null;
        }
        UserDetailVO detailVO = new UserDetailVO();
        BeanUtils.copyProperties(u, detailVO);
        return detailVO;
    }
}
