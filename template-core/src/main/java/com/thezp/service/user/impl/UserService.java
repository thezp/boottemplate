package com.thezp.service.user.impl;

import static com.thezp.dao.jooq.Tables.AUTHOR;

import com.thezp.base.service.impl.BaseService;
import com.thezp.dao.biz.user.entity.UserEntity;
import com.thezp.service.user.IUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by zhangpeng on 2018/1/4.
 */
@Service
public class UserService extends BaseService<UserEntity> implements IUserService {

    @Autowired
    private DSLContext dslContext;

    @Override
    @Cacheable(value = "common", sync = true)
    public List<UserEntity> selectEntities(Map<String, Object> param) {
        System.out.println("没走cache");
        List<UserEntity> list = new ArrayList<>();
        UserEntity entity = new UserEntity();
        entity.setName("zhangpeng");
        entity.setCategoryId("1");
        entity.setCurrencyId("1");
        list.add(entity);
        return list;
    }

    @Override
    public void getList() {
        Result<Record> ret = dslContext.select().from(AUTHOR).fetch();

        for (Record r : ret) {
            Integer id = r.getValue(AUTHOR.ID);
            String firstName = r.getValue(AUTHOR.FIRST_NAME);
            String lastName = r.getValue(AUTHOR.LAST_NAME);

            System.out
                .println("ID: " + id + " first name: " + firstName + " last name: " + lastName);
        }
    }
}
