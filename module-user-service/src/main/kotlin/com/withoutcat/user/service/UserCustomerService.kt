package com.withoutcat.user.service

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper
import com.baomidou.mybatisplus.core.conditions.Wrapper
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.withoutcat.user.data.entity.UserCustomer
import com.baomidou.mybatisplus.extension.service.IService
import com.withoutcat.user.data.entity.User
import com.withoutcat.user.data.vo.UserCustomerVO

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
     * 根据传入的wrapper查询客户的信息
     *
     * @param wrapper 查询条件
     * @return User的协变类型
     */
    fun getCustomerByWrapper(wrapper: Wrapper<UserCustomerVO>): UserCustomerVO?
}
