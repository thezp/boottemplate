package com.thezp.controller;

import com.thezp.dao.biz.user.entity.UserEntity;
import com.thezp.service.user.IUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangpeng on 2017/12/25.
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @Autowired
    private IUserService userService;

    @GetMapping(path = "/greeting")
    public String greeting() {
        return "world";
    }

    @GetMapping(path = "/getUsers")
    public List<UserEntity> getUsers() {

        return userService.selectEntities(null);
    }

    @GetMapping(path = "/getList")
    public void getList() {
        userService.getList();
    }

    @GetMapping(path = "/voidMethod")
    public void voidMethod() {
        System.out.println("hahhah");
    }

    @GetMapping(path = "/testAop")
    public List<UserEntity> selectList() {
        return userService.fetchList();
    }
}
