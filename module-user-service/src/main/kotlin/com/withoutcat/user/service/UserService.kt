package com.withoutcat.user.service

import com.withoutcat.user.data.entity.User
import com.baomidou.mybatisplus.extension.service.IService

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author withoutcat
 * @since 2023-07-11
 */
interface UserService : IService<User> {
    /**
     * 根据用户的类型和用户的账号获取用户的信息
     *
     * @param user 使用user的协变类型
     * @return 返回user的协变类型
     */
    fun getUserByAccount(user: User): User?
}
