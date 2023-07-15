package com.withoutcat.user.dto

/**
 * 从PetService请求的Pet基本数据
 *
 * @property id
 * @property name
 * @property type
 * @constructor Create empty Pet
 */
open class Pet(
    private val id: String,
    private val name: String,
    private val type: String,
)