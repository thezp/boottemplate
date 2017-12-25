package com.thezp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by zhangpeng on 2017/12/23.
 */
@SpringBootApplication
public class AppMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication
            .run(AppMain.class, args);
    }
}
