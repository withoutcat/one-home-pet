package com.withoutcat.user.data.dto

/**
 * 当用户登录成功后返回的信息
 *
 * @constructor
 *
 * @param id
 * @param name
 * @param phone
 * @param petDto
 */
data class LoginResponseDTO(
    val id: String,
    val name: String,
    val phone: String,
//    val petDto: PetDTO,
)