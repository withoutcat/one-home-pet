package com.withoutcat.user.service

import com.withoutcat.user.entity.UserCustomer
import com.baomidou.mybatisplus.extension.service.IService

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author withoutcat
 * @since 2023-07-11
 */
interface UserCustomerService : IService<UserCustomer> {

    /**
     * 通过用户的登录名查询到用户的完整信息
     *
     * @param username
     * @return
     */
    fun getCustomerByUsername(username: String)
}
