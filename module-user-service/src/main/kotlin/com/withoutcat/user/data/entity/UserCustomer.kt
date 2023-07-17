package com.withoutcat.user.data.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.extension.activerecord.Model
import java.io.Serializable
import io.swagger.v3.oas.annotations.media.Schema

/**
 * ## 用户表的扩展表：客户表
 *
 * @author withoutcat
 * @since 2023-07-17
 */
@TableName("t_user_customer")
@Schema(name = "UserCustomer对象", description = "用户表的扩展表：客户表")
data class UserCustomer (
    @Schema(description = "fk: t_user.id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    val id: String,
    @Schema(description = "账户余额")
    @TableField("balance")
    val balance: Double,
    @Schema(description = "会员等级id")
    @TableField("vip_level")
    val vipLevel: Byte,
    @Schema(description = "此用户对应的打折id")
    @TableField("vip_discount_id")
    val vipDiscountId: String,
    @Schema(description = "对用户的备注")
    @TableField("remark")
    val remark: String,
) : Model<UserCustomer>() {

    override fun pkVal(): Serializable {
        return id
    }

}


