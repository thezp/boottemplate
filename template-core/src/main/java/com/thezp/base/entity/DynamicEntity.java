package com.thezp.base.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 动态entity
 * Created by zhangpeng on 2018/1/3.
 */
@Deprecated
public class DynamicEntity<T extends Serializable> implements Map<String, T> {

    /** 动态实体 */
    private LinkedHashMap<String, T> dynamicEntity = new LinkedHashMap<>();

    @Override
    public void clear() {
        dynamicEntity.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        return dynamicEntity.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return dynamicEntity.containsValue(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<java.util.Map.Entry<String, T>> entrySet() {
        return dynamicEntity.entrySet();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(Object key) {
        return dynamicEntity.get(key);
    }

    @Override
    public boolean isEmpty() {
        return dynamicEntity.isEmpty();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<String> keySet() {
        return dynamicEntity.keySet();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T put(String key, T value) {
        return dynamicEntity.put(key, value);
    }

    @Override
    public void putAll(Map<? extends String, ? extends T> m) {
        dynamicEntity.putAll(m);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(Object key) {
        return  dynamicEntity.remove(key);
    }

    @Override
    public int size() {
        return dynamicEntity.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<T> values() {
        return dynamicEntity.values();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        sb.append("==============实体参数列表==============");
        sb.append("\n");
        for (Object key : dynamicEntity.keySet()) {
            sb.append(key);
            sb.append(":");
            sb.append(dynamicEntity.get(key));
            sb.append("\n");
        }
        sb.append("=====================================");
        return sb.toString();
    }
}
