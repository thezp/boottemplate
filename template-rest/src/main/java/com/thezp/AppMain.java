package com.thezp;

import com.codahale.metrics.ConsoleReporter;
import com.thezp.util.ApplicationContextUtil;
import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * 应用入口
 *
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
	}
}
