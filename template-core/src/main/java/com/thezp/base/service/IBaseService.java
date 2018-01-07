package com.thezp.base.service;

import com.thezp.base.entity.BaseEntity;
import java.util.List;
import java.util.Map;

/**
 * 所有service的顶层抽象接口
 *
 * Created by zhangpeng on 2018/1/4.
 */
public interface IBaseService<T extends BaseEntity> {

    /**
     * 获取List<T>对象
     *
     * @param param
     * @return List<T>
     */
    List<T> selectEntities(Map<String, Object> param);
}
