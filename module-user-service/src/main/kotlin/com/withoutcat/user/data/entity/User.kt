package com.withoutcat.user.data.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.extension.activerecord.Model
import com.withoutcat.user.data.enums.UserType
import java.io.Serializable
import java.time.LocalDateTime
import io.swagger.v3.oas.annotations.media.Schema
import kotlinx.serialization.Contextual

/**
 * ## 用户的基本信息抽象表
 *
 * @author withoutcat
 * @since 2023-07-17
 */
@TableName("t_user")
@Schema(name = "User对象", description = "用户的基本信息抽象表")
@kotlinx.serialization.Serializable
open class User (
    @Schema(description = "由32字符的UUID组成的主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    var id: String,
    @Schema(description = "用户登陆时的账号")
    @TableField("`account`")
    var account: String,
    @Schema(description = "登录密码，使用sha-256加密")
    @TableField("`password`")
    var password: String,
    @Schema(description = "用户的全名，如果不填默认是用户的手机号")
    @TableField("`name`")
    var name: String,
    @Schema(description = "由11位数字字符串组成的手机号")
    @TableField("phone")
    var phone: String,
    @Schema(description = "用户的地址，送宠上门时可能会用到")
    @TableField("address")
    var address: String,
    @Schema(description = """用户类型
            CUSTOMER客户，对应 t_user_customer
            STAFF员工，对应 t_user_staff""")
    @TableField("type")
    var type: UserType,
    @Schema(description = "逻辑删除0: false,1: true")
    @TableField("deleted")
    var deleted: Byte,
    @Schema(description = "创建时间")
    @TableField("create_time")
    @Contextual
    var createTime: LocalDateTime,
    @Schema(description = "创建人id")
    @TableField("create_by")
    var createBy: String,
    @Schema(description = "最后一次更新时间")
    @TableField("update_time")
    @Contextual
    var updateTime: LocalDateTime,
    @Schema(description = "最后一次更新人id")
    @TableField("update_by")
    var updateBy: String,
) : Model<User>() {

    constructor(account: String, type: UserType) : this(
        id = "",
        account = account,
        password = "",
        name = "",
        phone = "",
        address = "",
        type = type,
        deleted = 0,
        createTime = LocalDateTime.now(),
        createBy = "",
        updateTime = LocalDateTime.now(),
        updateBy = "",
    )



    override fun pkVal(): Serializable {
        return id
    }



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (id != other.id) return false
        if (account != other.account) return false
        if (password != other.password) return false
        if (name != other.name) return false
        if (phone != other.phone) return false
        if (address != other.address) return false
        if (type != other.type) return false
        if (deleted != other.deleted) return false
        if (createTime != other.createTime) return false
        if (createBy != other.createBy) return false
        if (updateTime != other.updateTime) return false
        return updateBy == other.updateBy
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + account.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + phone.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + deleted
        result = 31 * result + createTime.hashCode()
        result = 31 * result + createBy.hashCode()
        result = 31 * result + updateTime.hashCode()
        result = 31 * result + updateBy.hashCode()
        return result
    }

    override fun toString(): String {
        return "User(id='$id', account='$account', password='$password', name='$name', phone='$phone', address='$address', type=$type, deleted=$deleted, createTime=$createTime, createBy='$createBy', updateTime=$updateTime, updateBy='$updateBy')"
    }

}


