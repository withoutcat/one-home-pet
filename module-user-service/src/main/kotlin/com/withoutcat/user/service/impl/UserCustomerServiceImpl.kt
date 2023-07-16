package com.withoutcat.user.service.impl

import com.withoutcat.user.entity.UserCustomer
import com.withoutcat.user.mapper.UserCustomerMapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.withoutcat.user.service.UserCustomerService
import org.springframework.stereotype.Service

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author withoutcat
 * @since 2023-07-11
 */
@Service
class UserCustomerServiceImpl : ServiceImpl<UserCustomerMapper, UserCustomer>(), UserCustomerService {
    override fun getCustomerByUsername(username: String) {
        TODO("Not yet implemented")
    }

}
