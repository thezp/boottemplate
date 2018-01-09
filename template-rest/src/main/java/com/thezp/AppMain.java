package com.thezp;

import com.thezp.util.ApplicationContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 应用入口
 *
 * Created by zhangpeng on 2017/12/23.
 */
@SpringBootApplication
@EnableCaching
public class AppMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication
            .run(AppMain.class, args);
        ApplicationContextUtil.initApplicationContext(applicationContext);
    }
}
