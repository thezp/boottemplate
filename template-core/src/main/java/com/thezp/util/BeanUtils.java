package com.thezp.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.collections4.MapUtils;
import org.springframework.util.Assert;

/**
 * Bean工具扩展类
 * @author liuzhe
 */
public class BeanUtils {

    /**
     * 对象复制，只复制两个对象中具有相同名称与类型的属性
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copy(Object source, Object target) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
        beanCopier.copy(source, target, null);
    }

    /**
     * 将对象装换为map，默认对string调用trim()
     *
     * @param bean
     * @param filterNull 是否过滤null值
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean, boolean filterNull) {
        return beanToMap(bean, filterNull, true);
    }

    /**
     * 将对象装换为map
     *
     * @param bean
     * @param filterNull 是否过滤null值
     * @param trimString 是否对string调用trim()
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, Object> beanToMap(T bean, boolean filterNull,
        boolean trimString) {
        if (bean == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>(16);
        BeanMap beanMap = BeanMap.create(bean);
        for (Object obj : beanMap.entrySet()) {
            Entry<Object, Object> entry = (Entry<Object, Object>) obj;
            Object value = entry.getValue();
            if (filterNull && value == null) {
                continue;
            }
            if (trimString && value != null && value instanceof String) {
                value = ((String) value).trim();
            }
            map.put(entry.getKey() + "", value);
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     *
     * @param map
     * @param bean
     */
    public static <T> void mapToBean(Map<String, Object> map, T bean) {
        if (bean == null || MapUtils.isEmpty(map)) {
            return;
        }
        BeanMap.create(bean).putAll(map);
    }

    /**
     * 把bean转换为json字符串
     * @param bean
     * @return
     */
    public static <T> String bean2JSONString(T bean) {
        return JSONObject.toJSONString(bean, SerializerFeature.WriteDateUseDateFormat);
    }

}
