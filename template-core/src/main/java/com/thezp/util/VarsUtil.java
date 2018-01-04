package com.thezp.util;

import com.thezp.dao.comm.vars.entity.VarsEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.collections4.CollectionUtils;

/**
 * 对应关系工具类
 *
 * Created by zhangpeng on 2018/1/4.
 */
public class VarsUtil {

    /**
     * 编码与名称map
     */
    private static Map<String, Map<String, String>> code2Name = new ConcurrentHashMap<>();

    /**
     * 名称与编码map
     */
    private static Map<String, Map<String, String>> name2Code = new ConcurrentHashMap<>();

    public static enum convertDirect {
        Entity2Dto, Dto2Entity
    }

    /**
     * 初始化对应关系
     *
     * @param varsList
     */
    public static void initVars(List<VarsEntity> varsList) {
        if (CollectionUtils.isNotEmpty(varsList)) {
            initCode2Name(varsList);
            initName2Code(varsList);
        }
    }

    /**
     * 初始化name对应code
     *
     * @param varsList
     */
    private static void initName2Code(List<VarsEntity> varsList) {
        for (VarsEntity entity : varsList) {
            Map<String, String> map = name2Code.get(entity.getCateCode());
            if (map == null) {
                map = new HashMap<>();
                name2Code.put(entity.getCateCode(), map);
            }

            map.put(entity.getName(), entity.getCode());
        }
    }

    /**
     * 初始化code对应name
     *
     * @param varsList
     */
    private static void initCode2Name(List<VarsEntity> varsList) {
        for (VarsEntity entity : varsList) {
            Map<String, String> map = code2Name.get(entity.getCateCode());
            if (map == null) {
                map = new HashMap<>();
                code2Name.put(entity.getCateCode(), map);
            }

            map.put(entity.getCode(), entity.getName());
        }
    }

    /**
     * 通过指定名称获取指定类别中的code
     *
     * @param cateCode
     * @param name
     * @return
     */
    public static String getCodeByName(String cateCode, String name) {
        return getValue(name2Code, cateCode, name);
    }

    /**
     * 通过指定code获取指定类别中的name
     *
     * @param cateCode
     * @param code
     * @return
     */
    public static String getNameByCode(String cateCode, String code) {
        return getValue(code2Name, cateCode, code);
    }

    /**
     * 获取指定值
     *
     * @param outMap
     * @param cateCode
     * @param key
     * @return
     */
    private static String getValue(Map<String, Map<String, String>> outMap, String cateCode,
        String key) {
        Map<String, String> map = outMap.get(cateCode);
        String ret = null;
        if (map != null) {
            ret = map.get(key);
        }

        if (ret == null) {
            return "";
        }
        return ret;
    }

    /**
     * 判断是否存在
     * @param cateCode
     * @param direct
     * @return
     */
    public static boolean containCateCode(String cateCode, convertDirect direct) {
        if (convertDirect.Entity2Dto == direct) {
            return code2Name.containsKey(cateCode);
        } else {
            return name2Code.containsKey(cateCode);
        }
    }
}
