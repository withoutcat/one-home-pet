package com.withoutcat.user.service.impl

import com.baomidou.mybatisplus.core.conditions.Wrapper
import com.withoutcat.user.data.entity.UserCustomer
import com.withoutcat.user.mapper.UserCustomerMapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.withoutcat.user.data.entity.User
import com.withoutcat.user.data.vo.UserCustomerVO
import com.withoutcat.user.service.UserCustomerService
import org.springframework.beans.factory.annotation.Autowired
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
class UserCustomerServiceImpl(
    @Autowired
    val userCustomerMapper: UserCustomerMapper
) : ServiceImpl<UserCustomerMapper, UserCustomer>(), UserCustomerService {
    override fun getCustomerByWrapper(wrapper: Wrapper<UserCustomerVO>): UserCustomerVO? {
        return userCustomerMapper.getCustomerByWrapper(wrapper)
    }


}
