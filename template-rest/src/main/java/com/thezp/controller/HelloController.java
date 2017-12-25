package com.thezp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangpeng on 2017/12/25.
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @GetMapping(path = "/greeting")
    public String greeting() {
        return "world";
    }
}
