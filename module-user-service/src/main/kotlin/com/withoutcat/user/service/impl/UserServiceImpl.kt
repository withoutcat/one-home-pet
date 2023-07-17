package com.withoutcat.user.service.impl

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.withoutcat.user.data.entity.User
import com.withoutcat.user.mapper.UserMapper
import com.withoutcat.user.service.UserService
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.withoutcat.user.data.vo.UserCustomerVO
import com.withoutcat.user.data.enums.UserType
import com.withoutcat.user.service.UserCustomerService
import com.withoutcat.user.utils.MessageDigestUtils
import kotlinx.coroutines.runBlocking
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
class UserServiceImpl(
    @Autowired
    val userCustomerService: UserCustomerService
) : ServiceImpl<UserMapper, User>(), UserService {
    override fun getUserByAccount(user: User): User? {
        return when (user.type) {
            UserType.CUSTOMER -> {
                val wrapper = QueryWrapper<UserCustomerVO>()
                    .eq("u.account", user.account)
                userCustomerService.getCustomerByWrapper(wrapper)?.let {
                    // 判断密码是否正确
                    val sha256 = MessageDigestUtils.sha256(user.password)
                    if (it.password != sha256) null
                    else {
                        // 查询用户的宠物
//                        val pets = userCustomerService.getPetsByCustomerId(it.id)
//                        it.pets = pets
                        it
                    }
                }
            }

            UserType.STAFF -> {
                UserCustomerVO("123")
            }
        }
    }

}
