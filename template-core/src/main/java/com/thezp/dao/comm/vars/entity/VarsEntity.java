package com.thezp.dao.comm.vars.entity;

import com.thezp.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 参数配置信息
 *
 * Created by zhangpeng on 2018/1/4.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class VarsEntity extends BaseEntity {
    /**************业务属性开始**************/

    /**
     * 业务归属类别code
     */
    private String cateCode;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;
}
