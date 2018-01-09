package com.thezp.service.user.impl;

import com.thezp.base.service.impl.BaseService;
import com.thezp.dao.biz.user.entity.UserEntity;
import com.thezp.service.user.IUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by zhangpeng on 2018/1/4.
 */
@Slf4j
@Service
public class UserService extends BaseService<UserEntity> implements IUserService {

    @Override
    @Cacheable(value = "common", sync = true)
    public List<UserEntity> selectEntities(Map<String, Object> param) {
        System.out.println("没走cache");
        List<UserEntity> list = new ArrayList<>();
        UserEntity entity = new UserEntity();
        entity.setName("thezp");
        entity.setCategoryId("1");
        entity.setCurrencyId("1");
        entity.setTestId("2");
        list.add(entity);
        return list;
    }

    @Override
    public List<UserEntity> fetchList() {
        List<UserEntity> list = new ArrayList<>();
        UserEntity entity = new UserEntity();
        entity.setName("thezp");
        entity.setCategoryId("1");
        entity.setCurrencyId("1");
        entity.setTestId("2");
        list.add(entity);
        return list;
    }
}
