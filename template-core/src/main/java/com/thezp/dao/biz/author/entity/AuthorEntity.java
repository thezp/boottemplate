package com.thezp.dao.biz.author.entity;

import com.thezp.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zhangpeng on 2018/1/5.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorEntity extends BaseEntity {

    /**
     * 姓
     */
    private String firstName;

    /**
     * 名
     */
    private String lastName;

    /**
     * 状态
     */
    private String status;
}
