package com.withoutcat.user.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.extension.activerecord.Model
import java.io.Serializable
import java.time.LocalDateTime
import io.swagger.v3.oas.annotations.media.Schema

/**
 * ## 用户的基本信息抽象表
 *
 * @author withoutcat
 * @since 2023-07-17
 */
@TableName("t_user")
@Schema(name = "User对象", description = "用户的基本信息抽象表")
data class User (
    @Schema(description = "由32字符的UUID组成的主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    val id: String,
    @Schema(description = "用户登陆时的账号")
    @TableField("`account`")
    val account: String,
    @Schema(description = "登录密码，使用sha-256加密")
    @TableField("`password`")
    val password: String,
    @Schema(description = "用户的全名，如果不填默认是用户的手机号")
    @TableField("`name`")
    val name: String,
    @Schema(description = "由11位数字字符串组成的手机号")
    @TableField("phone")
    val phone: String,
    @Schema(description = "用户的地址，送宠上门时可能会用到")
    @TableField("address")
    val address: String,
    @Schema(description = "逻辑删除0: false,1: true")
    @TableField("deleted")
    val deleted: Byte,
    @Schema(description = "创建时间")
    @TableField("create_time")
    val createTime: LocalDateTime,
    @Schema(description = "创建人id")
    @TableField("create_by")
    val createBy: String,
    @Schema(description = "最后一次更新时间")
    @TableField("update_time")
    val updateTime: LocalDateTime,
    @Schema(description = "最后一次更新人id")
    @TableField("update_by")
    val updateBy: String,
) : Model<User>() {

    override fun pkVal(): Serializable {
        return id
    }

}


