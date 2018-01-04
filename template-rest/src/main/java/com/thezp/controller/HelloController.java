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

    @GetMapping(path = "/getList")
    public List<UserEntity> getList() {

        return userService.selectEntities(null);
    }
}
