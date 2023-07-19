package com.withoutcat.user.data.enums

import com.baomidou.mybatisplus.annotation.EnumValue
import com.fasterxml.jackson.annotation.JsonValue

/**
 * 用户的类型
 *
 */
enum class UserType(desc: String) {
    /**
     * 客户
     */
    CUSTOMER("客户"),
    /**
     * 店内员工
     */
    STAFF("员工"),

    /**
     * 管理员
     */
    ADMIN("管理员");

    @EnumValue
    val value: String = name

    @JsonValue
    val desc: String = desc
}
