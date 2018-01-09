package com.thezp.dao.biz.author.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.thezp.dao.BasePlusEntity;
import com.thezp.enums.StatusEnum;
import lombok.Data;

/**
 * Created by zhangpeng on 2018/1/9.
 */
@Data
@TableName(value = "author")
public class AuthorPlusEntity extends BasePlusEntity<AuthorPlusEntity> {

    private String firstName;

    private String lastName;

    private StatusEnum status;
}
