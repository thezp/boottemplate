package com.thezp.dao.biz.author.mapper;

import com.thezp.IBasePlusMapper;
import com.thezp.dao.biz.author.entity.AuthorPlusEntity;
import java.util.List;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zhangpeng on 2018/1/9.
 */
public interface AuthorMapper extends IBasePlusMapper<AuthorPlusEntity> {

    @Select("select id, first_name as firstName, last_name as lastName, status from author")
    List<AuthorPlusEntity> selectListBySQL();
}
