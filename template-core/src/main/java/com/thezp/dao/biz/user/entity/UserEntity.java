package com.thezp.dao.biz.user.entity;

import com.thezp.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户实体
 *
 * Created by zhangpeng on 2018/1/4.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {

    private String categoryId;

    private String categoryIdName;

    private String name;

    private String currencyId;

    private String testId;

    private String testIdName;
}
