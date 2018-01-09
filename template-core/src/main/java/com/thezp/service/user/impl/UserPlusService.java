package com.thezp.service.user.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.thezp.dao.biz.author.entity.AuthorPlusEntity;
import com.thezp.dao.biz.author.mapper.AuthorMapper;
import com.thezp.service.user.IUserPlusService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Created by zhangpeng on 2018/1/9.
 */
@Service
public class UserPlusService extends ServiceImpl<AuthorMapper, AuthorPlusEntity> implements
    IUserPlusService {

    @Override
    public List<AuthorPlusEntity> selectListBySQL() {
        return baseMapper.selectListBySQL();
    }
}
