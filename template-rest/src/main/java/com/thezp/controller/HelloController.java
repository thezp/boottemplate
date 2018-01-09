package com.thezp.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.thezp.dao.biz.author.entity.AuthorPlusEntity;
import com.thezp.dao.biz.user.entity.UserEntity;
import com.thezp.enums.StatusEnum;
import com.thezp.service.user.IUserPlusService;
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

    @Autowired
    private IUserPlusService userPlusService;

    @GetMapping(path = "/greeting")
    public String greeting() {
        return "world";
    }

    @GetMapping(path = "/getUsers")
    public List<UserEntity> getUsers() {

        return userService.selectEntities(null);
    }

    @GetMapping(path = "/voidMethod")
    public void voidMethod() {
        System.out.println("hahhah");
    }

    @GetMapping(path = "/testAop")
    public List<UserEntity> selectList() {
        return userService.fetchList();
    }

    /**
     * 分页 PAGE
     */
    @GetMapping("/testPlus")
    public Page<AuthorPlusEntity> testPlus() {
        return userPlusService.selectPage(new Page<>(0, 10));
    }

    @GetMapping("/selectsql")
    public Object getUserBySql() {
        JSONObject result = new JSONObject();
        result.put("records", userPlusService.selectListBySQL());
        return result;
    }

    @GetMapping("/add")
    public Object addUser() {
        AuthorPlusEntity user = new AuthorPlusEntity();
        user.setFirstName("e");
        user.setLastName("f");
        user.setStatus(StatusEnum.ACTIVATE);
        JSONObject result = new JSONObject();
        result.put("result", userPlusService.insert(user));
        return result;
    }
}
