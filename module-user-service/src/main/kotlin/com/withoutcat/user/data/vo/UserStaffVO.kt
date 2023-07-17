package com.withoutcat.user.data.vo

import com.withoutcat.user.data.entity.User
import com.withoutcat.user.data.enums.UserType


class UserStaffVO(account: String) : User(account, UserType.STAFF) {
    var position: String = ""
    var salary: Double = 0.0

    /**
     * 次级构造函数，用于查询时
     */
    constructor(id: String, account: String, name: String, phone: String, address: String, position:String, salary: Double) : this(account) {
        this.id = id
        this.name = name
        this.phone = phone
        this.address = address
        this.position = position
        this.salary = salary
    }
}