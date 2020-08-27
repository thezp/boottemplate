package com.thezp.command;

import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2020 aijia, Inc. All rights reserved. <p>
 * Company: 艾佳生活<p>
 *
 * @author zhangpeng
 * @since 2020/8/25 16:37
 */
@Component
public class FormularCommand implements CommandLineRunner {
    @Override
    public void run(String... strings) {
        FormularBean b = new FormularBean();
        b.setA1(1);
        b.setA2(2);
        b.setFormular("a3=a1+a2");

        Field a1 = ReflectionUtils.findField(FormularBean.class, "a1");
        ReflectionUtils.makeAccessible(a1);
        Field a2 = ReflectionUtils.findField(FormularBean.class, "a2");
        ReflectionUtils.makeAccessible(a2);
        b.setA3((Integer) ReflectionUtils.getField(a1, b) + (Integer) ReflectionUtils.getField(a2, b));
        System.out.println(b);
    }

    @Data
    class FormularBean {
        private Integer a1;

        private Integer a2;

        private Integer a3;

        private String formular;

    }
}
