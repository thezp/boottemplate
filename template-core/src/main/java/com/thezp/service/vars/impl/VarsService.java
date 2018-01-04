package com.thezp.service.vars.impl;

import com.thezp.base.service.impl.BaseService;
import com.thezp.dao.comm.vars.entity.VarsEntity;
import com.thezp.service.vars.IVarsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * Created by zhangpeng on 2018/1/4.
 */
@Service
public class VarsService extends BaseService<VarsEntity> implements IVarsService {

    @Override
    public List<VarsEntity> selectEntities(Map<String, Object> param) {
        List<VarsEntity> list = new ArrayList<>();
        VarsEntity entity1 = new VarsEntity();
        entity1.setCateCode("categoryId");
        entity1.setCode("1");
        entity1.setName("分类1");
        list.add(entity1);

        VarsEntity entity2 = new VarsEntity();
        entity2.setCateCode("categoryId");
        entity2.setCode("2");
        entity2.setName("分类2");
        list.add(entity2);

        VarsEntity entity3 = new VarsEntity();
        entity3.setCateCode("currencyId");
        entity3.setCode("1");
        entity3.setName("币种1");
        list.add(entity3);
        return list;
    }
}
