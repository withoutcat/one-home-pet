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
@TableName("t_user_staff")
@Schema(name = "UserStaff对象", description = "")
data class UserStaff (
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    val id: String?,
    @TableField("position")
    val position: String?,
    @TableField("salary")
    val salary: Double?,
) : Model<UserStaff>() {

    override fun pkVal(): Serializable? {
        return id
    }

}


