package com.withoutcat.user.domain


/**
 * 用户的基本信息
 *
 * @property id
 * @property name
 * @property phone
 * @constructor Create empty BaseUser
 */
open class BaseUser(
    private val id: String,
    private val name: String,
    private val phone: String,
)