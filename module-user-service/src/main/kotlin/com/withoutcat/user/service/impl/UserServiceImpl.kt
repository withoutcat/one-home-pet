package com.withoutcat.user.service.impl;

import com.withoutcat.user.entity.User;
import com.withoutcat.user.mapper.UserMapper;
import com.withoutcat.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author withoutcat
 * @since 2023-07-11
 */
@Service
open class UserServiceImpl : ServiceImpl<UserMapper, User>(), UserService {

}
