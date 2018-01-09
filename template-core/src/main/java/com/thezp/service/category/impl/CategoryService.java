package com.thezp.service.category.impl;

import com.thezp.base.entity.CategoryEntity;
import com.thezp.base.service.IConvertService;
import com.thezp.base.service.impl.BaseService;
import com.thezp.service.category.ICategoryService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * Created by zhangpeng on 2018/1/9.
 */
@Service
public class CategoryService extends BaseService<CategoryEntity> implements ICategoryService,
    IConvertService {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("1", "测试1");
        map.put("2", "测试2");
    }

    @Override
    public String propName() {
        return "testId";
    }

    @Override
    public String getValueName(String key) {
        return map.get(key);
    }
}
