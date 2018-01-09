package com.thezp.aop;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.thezp.base.service.IConvertService;
import com.thezp.util.ApplicationContextUtil;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * 转换实现
 *
 * Created by zhangpeng on 2018/1/9.
 */
@Slf4j
@Aspect
@Configuration
public class ConvertAop {

    /**
     * 是否初始化过
     */
    private boolean inited = false;

    private Map<String, IConvertService> map = new HashMap<>();

    @Pointcut("execution(* com.thezp.service..*Service.fetch*(..))")
    public void execService() {
    }

    private void doInit() {
        if (!inited) {
            List<IConvertService> list = ApplicationContextUtil
                .getServiceList(IConvertService.class);
            if (CollectionUtils.isNotEmpty(list)) {
                list.forEach(t -> map.put(t.propName(), t));
            }
            inited = true;
        }
    }

    @Around("execService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        doInit();
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
            if (map.containsKey(key)) {
                String nameStr = key + "Name";
                if (result.containsKey(nameStr)) {
                    result
                        .put(nameStr, map.get(key).getValueName(String.valueOf(result.get(key))));
                }
            }
        }
    }

    /**
     * 转换实体
     * @param obj
     */
    private void convertEntity(Object obj) {
        List<String> fnames = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
            fnames.add(f.getName());
        }

        for (String str : fnames) {
            if (map.containsKey(str)) {
                String nameStr = str + "Name";
                if (fnames.contains(nameStr)) {
                    setValue(obj, nameStr, String.class,
                        map.get(str).getValueName(String.valueOf(getValue(obj, str))));
                }
            }
        }
    }

    /**
     * 通过属性名获取属性值
     * @param obj
     * @param propName
     * @return
     */
    private static Object getValue(Object obj, String propName) {
        MethodAccess methodAccess = MethodAccess.get(obj.getClass());
        return methodAccess
            .invoke(obj, "get" + propName.substring(0, 1).toUpperCase() + propName.substring(1));
    }

    /**
     * 通过属性名设置属性值
     * @param obj
     * @param propName
     * @return
     */
    private static void setValue(Object obj, String propName, Class<?> clazz, Object value) {
        MethodAccess methodAccess = MethodAccess.get(obj.getClass());
        methodAccess
            .invoke(obj, "set" + propName.substring(0, 1).toUpperCase() + propName.substring(1),
                value);
    }

}
