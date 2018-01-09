package com.thezp.service.user;

import com.baomidou.mybatisplus.service.IService;
import com.thezp.dao.biz.author.entity.AuthorPlusEntity;
import java.util.List;

/**
 * Created by zhangpeng on 2018/1/9.
 */
public interface IUserPlusService extends IService<AuthorPlusEntity> {

    List<AuthorPlusEntity> selectListBySQL();
}
