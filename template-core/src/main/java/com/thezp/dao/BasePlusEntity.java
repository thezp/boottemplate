package com.thezp.dao;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;
import lombok.Data;

/**
 * Created by zhangpeng on 2018/1/9.
 */
@Data
public class BasePlusEntity<T extends Model> extends Model<T> {

    /**
     * 主键ID , 这里故意演示注解可以无
     */
    @TableId("id")
    private Long id;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
