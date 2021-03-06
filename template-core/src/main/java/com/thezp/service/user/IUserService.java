package com.thezp.service.user;

import com.thezp.base.service.IBaseService;
import com.thezp.dao.biz.user.entity.UserEntity;
import java.util.List;

/**
 * Created by zhangpeng on 2018/1/4.
 */
public interface IUserService extends IBaseService<UserEntity> {

    List<UserEntity> fetchList();
}
