package com.withoutcat.user.service

import com.withoutcat.user.entity.User
import com.baomidou.mybatisplus.extension.service.IService
import com.withoutcat.user.data.enums.UserType

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author withoutcat
 * @since 2023-07-11
 */
interface UserService : IService<User> {
    fun getUserByType(userType: UserType)
}
