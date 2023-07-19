package com.withoutcat.user.data.dto

import com.withoutcat.user.data.enums.UserType
import kotlinx.serialization.Serializable

/**
 * 当用户登录成功后返回的信息
 *
 */
@Serializable
data class UserDTO(
    val id: String,
    val account: String,
    val name: String,
    val phone: String,
    val address: String,
    val type: UserType,
    val pets: List<PetDTO>,
    val balance: Double,
    val vipLevel: Int,
    // TODO:折扣信息

)