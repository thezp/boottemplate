package com.thezp.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;

/**
 * Created by zhangpeng on 2018/1/9.
 */
public enum StatusEnum implements IEnum {

    ACTIVATE(true, "在用"),
    DEACTIVATE(false, "停用");

    private final boolean value;

    private final String desc;

    StatusEnum(final boolean value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.value;
    }

    @JsonValue
    public String getDesc() {
        return this.desc;
    }

}
