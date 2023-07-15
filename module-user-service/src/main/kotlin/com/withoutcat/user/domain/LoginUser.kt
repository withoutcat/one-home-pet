package com.withoutcat.user.domain

import com.withoutcat.user.dto.Pet

/**
 * 当用户登录成功后返回的信息
 *
 * @constructor
 *
 * @param id
 * @param name
 * @param phone
 * @param pet
 */
class LoginUser(
    id: String,
    name: String,
    phone: String,
    pet: Pet,
) : BaseUser(id, name, phone) {

}