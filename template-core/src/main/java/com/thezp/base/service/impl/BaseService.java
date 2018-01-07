package com.thezp.base.service.impl;

import com.thezp.base.entity.BaseEntity;
import com.thezp.base.service.IBaseService;
import java.util.List;
import java.util.Map;

/**
 * 所有service的抽象父类
 *
 * Created by zhangpeng on 2018/1/4.
 */
public class BaseService<T extends BaseEntity> implements IBaseService<T> {

    @Override
    public List<T> selectEntities(Map<String, Object> param) {
        return null;
    }

}
