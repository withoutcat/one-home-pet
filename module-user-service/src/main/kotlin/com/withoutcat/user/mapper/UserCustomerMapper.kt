package com.withoutcat.user.mapper

import com.baomidou.mybatisplus.core.conditions.Wrapper
import com.withoutcat.user.data.entity.UserCustomer
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.toolkit.Constants
import com.withoutcat.user.data.vo.UserCustomerVO
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author withoutcat
 * @since 2023-07-11
 */
@Mapper
interface UserCustomerMapper : BaseMapper<UserCustomer> {

    fun getCustomerByWrapper(@Param(Constants.WRAPPER) wrapper: Wrapper<UserCustomerVO>): UserCustomerVO
}
