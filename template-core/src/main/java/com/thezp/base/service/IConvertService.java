package com.thezp.base.service;

/**
 * Created by zhangpeng on 2018/1/9.
 */
public interface IConvertService {

    /**
     * 属性名称
     * @return
     */
    String propName();

    /**
     * 获取对应的值
     * @param key
     * @return
     */
    String getValueName(String key);
}
