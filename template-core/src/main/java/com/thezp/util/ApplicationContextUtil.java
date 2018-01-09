package com.thezp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationContext;

/**
 * ApplicationContext工具类
 * @author thezp
 */
public class ApplicationContextUtil {

    /**初始化标志*/
    private static boolean hasInit;

    /**应用上下文*/
    private static ApplicationContext ctx;

    /**
     * 初始化ApplicationContext
     * @param ctx
     */
    public static void initApplicationContext(ApplicationContext ctx) {
        if (!hasInit) {
            synchronized (ApplicationContextUtil.class) {
                if (!hasInit) {
                    ApplicationContextUtil.ctx = ctx;
                    hasInit = true;
                }
            }
        }
    }

    /**
     * 判断bean是否存在
     * @param beanName
     * @return
     */
    public static boolean containsBean(String beanName) {
        if (ctx == null) {
            return false;
        }
        return ctx.containsBean(beanName);
    }

    /**
     * 根据名称获取bean
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        if (ctx == null) {
            return null;
        }
        return ctx.getBean(beanName);
    }

    /**
     * 根据类型获取bean
     * @param requiredType
     * @return
     */
    public static <T> T getBean(Class<T> requiredType) {
        if (ctx == null) {
            return null;
        }
        return ctx.getBean(requiredType);
    }

    /**
     * 根据名称和类型获取bean
     * @param beanName
     * @param requiredType
     * @return
     */
    public static <T> T getBean(String beanName, Class<T> requiredType) {
        if (ctx == null) {
            return null;
        }
        return ctx.getBean(beanName, requiredType);
    }

    /**
     * 根据名称和参数获取bean
     * @param beanName
     * @param args
     * @return
     */
    public static Object getBean(String beanName, Object... args) {
        if (ctx == null) {
            return null;
        }
        return ctx.getBean(beanName, args);
    }

    /**
     * 获取应用中所有实现了某接口的service列表
     * @return
     */
    public static <T> List<T> getServiceList(Class<T> clazz) {
        List<T> list = new ArrayList<>();
        Map<String, T> map = ctx.getBeansOfType(clazz);
        if (map != null && map.size() > 0) {
            map.keySet().forEach(m -> list.add(map.get(m)));
        }
        return list;
    }

}
