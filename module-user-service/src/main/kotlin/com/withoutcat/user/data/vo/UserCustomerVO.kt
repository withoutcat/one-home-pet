package com.withoutcat.user.data.vo

import com.withoutcat.user.data.entity.User
import com.withoutcat.user.data.enums.UserType

class UserCustomerVO(account: String) : User(account, UserType.CUSTOMER) {
    var balance: Double = 0.0
    var vipLevel: Int = 0
    var vipDiscountId: String = ""
    var remark: String = ""
}

