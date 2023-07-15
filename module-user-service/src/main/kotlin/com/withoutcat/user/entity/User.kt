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
 * ## 
 *
 * @author withoutcat
 * @since 2023-07-15
 */
@TableName("t_user")
@Schema(name = "User对象", description = "")
data class User (
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    val id: String?,
    @TableField("`password`")
    val password: String?,
    @TableField("`name`")
    val name: String?,
    @TableField("phone")
    val phone: String?,
    @TableField("address")
    val address: String?,
    @TableField("deleted")
    val deleted: Byte?,
    @TableField("create_time")
    val createTime: LocalDateTime?,
    @TableField("create_by")
    val createBy: String?,
    @TableField("update_time")
    val updateTime: LocalDateTime?,
    @TableField("update_by")
    val updateBy: String?,
) : Model<User>() {

    override fun pkVal(): Serializable? {
        return id
    }

}


