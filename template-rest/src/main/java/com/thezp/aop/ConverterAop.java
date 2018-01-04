package com.thezp.aop;

import com.thezp.util.VarsUtil;
import com.thezp.util.VarsUtil.convertDirect;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * 转换aop
 *
 * @author zhangpeng
 */
@Slf4j
@Aspect
@Configuration
public class ConverterAop {

    @Pointcut("execution(* com.thezp.controller.*Controller.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();

        if (result != null) {
            convertObject(result);
        }
        return result;
    }

    /**
     * 转换结果
     *
     * @param result
     */
    private void convertObject(Object result) throws Exception {
        if (result instanceof List) {
            List list = (List) result;
            for (Object obj : list) {
                convertObject(obj);
            }
        } else if (result instanceof Map) {
            convertMap((Map) result);
        } else {
            convertEntity(result);
        }
    }

    /**
     * 转换map
     * @param result
     */
    @SuppressWarnings("unchecked")
    private void convertMap(Map result) {
        for (Object o : result.keySet()) {
            String key = String.valueOf(o);
            if (VarsUtil.containCateCode(key, convertDirect.Entity2Dto)) {
                String nameStr = key + "Name";
                if (result.containsKey(nameStr)) {
                    result
                        .put(nameStr, VarsUtil.getNameByCode(key, String.valueOf(result.get(key))));
                }
            }
        }
    }

    /**
     * 转换实体
     * @param obj
     */
    private void convertEntity(Object obj) throws Exception {
        List<String> fnames = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
            fnames.add(f.getName());
        }

        for (String str : fnames) {
            if (VarsUtil.containCateCode(str, convertDirect.Entity2Dto)) {
                String nameStr = str + "Name";
                if (fnames.contains(nameStr)) {
                    setValue(obj, nameStr, String.class,
                        VarsUtil.getNameByCode(str, String.valueOf(getValue(obj, str))));
                }
            }
        }
    }

    /**
     * 通过属性名获取属性值
     * @param obj
     * @param propName
     * @return
     * @throws Exception
     */
    private static Object getValue(Object obj, String propName) throws Exception {
        Method m = obj.getClass()
            .getMethod("get" + propName.substring(0, 1).toUpperCase() + propName.substring(1));
        return m.invoke(obj);
    }

    /**
     * 通过属性名设置属性值
     * @param obj
     * @param propName
     * @return
     * @throws Exception
     */
    private static void setValue(Object obj, String propName, Class<?> clazz, Object value)
        throws Exception {
        Method m = obj.getClass()
            .getMethod("set" + propName.substring(0, 1).toUpperCase() + propName.substring(1),
                clazz);
        m.setAccessible(true);
        m.invoke(obj, value);
    }
}