package com.withoutcat.user.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.extension.activerecord.Model
import java.io.Serializable
import io.swagger.v3.oas.annotations.media.Schema

/**
 * ## 
 *
 * @author withoutcat
 * @since 2023-07-15
 */
@TableName("t_user_customer")
@Schema(name = "UserCustomer对象", description = "")
data class UserCustomer (
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    val id: String?,
    @TableField("balance")
    val balance: Double?,
    @TableField("vip_level")
    val vipLevel: Byte?,
    @TableField("vip_discount_id")
    val vipDiscountId: String?,
    @TableField("remark")
    val remark: String?,
) : Model<UserCustomer>() {

    override fun pkVal(): Serializable? {
        return id
    }

}


