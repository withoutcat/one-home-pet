package com.withoutcat.user.data.dto

/**
 * 从PetService请求的Pet基本数据
 *
 * @property id
 * @property name
 * @property type
 * @constructor Create empty Pet
 */
data class PetDTO(
    val id: String,
    val name: String,
    val type: String,
)