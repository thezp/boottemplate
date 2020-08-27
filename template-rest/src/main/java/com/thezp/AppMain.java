package com.thezp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.codahale.metrics.ConsoleReporter;
import com.thezp.util.ApplicationContextUtil;
import com.thezp.vo.DynamicBean;
import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 应用入口
 * <p>
 * Created by zhangpeng on 2017/12/23.
 */
@SpringBootApplication
@EnableCaching
@EnableAdminServer
public class AppMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AppMain.class, args);
        ApplicationContextUtil.initApplicationContext(applicationContext);

        // 启动Reporter
        ConsoleReporter reporter = ApplicationContextUtil.getBean(ConsoleReporter.class);
        reporter.start(1, TimeUnit.MINUTES);

        Map<String, Class> property = new HashMap<>();
        property.put("id", Integer.class);
        property.put("name", String.class);
        property.put("age", Integer.class);
        property.put("money", BigDecimal.class);
        DynamicBean bean = new DynamicBean(property);

        String str = "{\"id\":1,\"name\":\"thezp\",\"age\":12,\"money\":12345.3}";
        JSONObject obj = JSON.parseObject(str);
        obj.keySet().forEach(k -> bean.setValue(k, obj.get(k)));
        System.out.println(JSONObject.toJSONString(bean.getObject()));
        System.out.println(obj.getLong("id"));
    }
}
