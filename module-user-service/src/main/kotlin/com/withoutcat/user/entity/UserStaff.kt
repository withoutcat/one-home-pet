package com.withoutcat.user.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.extension.activerecord.Model
import java.io.Serializable
import io.swagger.v3.oas.annotations.media.Schema

/**
 * ## 用户表的扩展表：员工表
 *
 * @author withoutcat
 * @since 2023-07-17
 */
@TableName("t_user_staff")
@Schema(name = "UserStaff对象", description = "用户表的扩展表：员工表")
data class UserStaff (
    @Schema(description = "fk: t_user.id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    val id: String?,
    @Schema(description = "职位")
    @TableField("position")
    val position: String?,
    @Schema(description = "工资")
    @TableField("salary")
    val salary: Double?,
) : Model<UserStaff>() {

    override fun pkVal(): Serializable? {
        return id
    }

}


