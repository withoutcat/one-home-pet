package com.withoutcat.pet.entity

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
 * @since 2023-07-17
 */
@TableName("t_pet")
@Schema(name = "Pet对象", description = "")
data class Pet (

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    val id: String,

    @TableField("`name`")
    val name: String?,

    @TableField("`type`")
    val type: String?,

    @TableField("`owner`")
    val owner: String?,

    @TableField("is_deleted")
    val isDeleted: Byte?,

    @TableField("create_time")
    val createTime: LocalDateTime?,

    @TableField("create_by")
    val createBy: String?,

    @TableField("update_time")
    val updateTime: LocalDateTime?,

    @TableField("update_by")
    val updateBy: String?,
) : Model<Pet>() {

    override fun pkVal(): Serializable {
        return id
    }

}


