package com.withoutcat.feign.dto.user

import com.withoutcat.feign.dto.pet.PetDTO

/**
 * 当用户登录成功后返回的信息
 *
 */
data class UserDTO(
    val id: String,
    val account: String,
    val name: String,
    val phone: String,
    val address: String,
    val type: String,
    val pets: List<PetDTO>,



)