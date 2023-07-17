package com.withoutcat.user.service.impl

import com.baomidou.mybatisplus.core.conditions.Wrapper
import com.withoutcat.user.data.entity.UserStaff
import com.withoutcat.user.mapper.UserStaffMapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.withoutcat.user.data.entity.User
import com.withoutcat.user.service.UserService
import com.withoutcat.user.service.UserStaffService
import org.springframework.stereotype.Service
import java.util.function.Function

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author withoutcat
 * @since 2023-07-11
 */
@Service
class UserStaffServiceImpl : ServiceImpl<UserStaffMapper, UserStaff>(), UserStaffService {

}
