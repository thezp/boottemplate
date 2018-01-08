package com.thezp.service.user.impl;

import static com.thezp.dao.jooq.tables.Author.AUTHOR;

import com.thezp.base.service.impl.BaseService;
import com.thezp.dao.biz.author.entity.AuthorEntity;
import com.thezp.dao.biz.user.entity.UserEntity;
import com.thezp.dao.jooq.tables.records.AuthorRecord;
import com.thezp.service.user.IUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by zhangpeng on 2018/1/4.
 */
@Slf4j
@Service
public class UserService extends BaseService<UserEntity> implements IUserService {

    @Autowired
    private DSLContext dsl;

    @Override
    @Cacheable(value = "common", sync = true)
    public List<UserEntity> selectEntities(Map<String, Object> param) {
        System.out.println("没走cache");
        List<UserEntity> list = new ArrayList<>();
        UserEntity entity = new UserEntity();
        entity.setName("thezp");
        entity.setCategoryId("1");
        entity.setCurrencyId("1");
        list.add(entity);
        return list;
    }

    @Override
    public void getList() {

        dsl.select().from(AUTHOR).fetchInto(AuthorEntity.class).forEach(r -> log.info(
            "ID: " + r.getId() + " first name: " + r.getFirstName() + " last name: " + r
                .getLastName()));
        AuthorRecord ar = new AuthorRecord();
        ar.setId(1);
        System.out.println(dsl.fetchByExample(ar));
    }
}
