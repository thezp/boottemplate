package com.thezp.base.entity;

import com.thezp.util.BeanUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import lombok.Data;

/**
 * 基础实体类
 *
 * Created by zhangpeng on 2018/1/4.
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 默认页大小
     */
    private static final Integer DEFAULT_SIZE = 10;

    /**
     * 默认第一页
     */
    private static final Integer DEFAULT_PAGE = 1;

    /**
     * id
     */
    private Integer id;

    /**
     * 操作人id
     */
    private Integer optId;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否有效(0->有效 -1->无效)
     */
    private Integer mark;

    /**
     * 页码
     */
    private Integer pageNumber;

    /**
     * 页大小
     */
    private Integer pageSize;

    public Integer getPageNumber() {
        if (pageNumber == null) {
            return DEFAULT_PAGE;
        }
        return pageNumber;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            return DEFAULT_SIZE;
        }
        return pageSize;
    }

    public Integer getPageBegin() {
        return (getPageNumber() - 1) * getPageSize();
    }

    /**
     * 转换dto为查询map
     * @return map，包含分页参数
     */
    public Map<String, Object> convertDto2QueryMap() {
        Map<String, Object> paramMap = BeanUtils.beanToMap(this, true);
        paramMap.put("pageBegin", getPageBegin());
        return paramMap;
    }

}
