package com.withoutcat.pet.data.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.extension.activerecord.Model
import java.io.Serializable
import java.time.LocalDateTime
import io.swagger.v3.oas.annotations.media.Schema

/**
 * ## 客户的宠物表
 *
 * @author withoutcat
 * @since 2023-07-19
 */
@TableName("t_pet")
@Schema(name = "Pet对象", description = "客户的宠物表")
open class Pet (

    @Schema(description = "主键UUID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    val id: String,

    @Schema(description = "宠物的名字")
    @TableField("`name`")
    val name: String,

    @Schema(description = "宠物的品种")
    @TableField("breed_id")
    val breedId: String,

    @Schema(description = "宠物主人id: t_user_customer.id")
    @TableField("owner_id")
    val ownerId: String,

    @Schema(description = "逻辑删除")
    @TableField("deleted")
    val deleted: Boolean,

    @TableField("create_time")
    val createTime: LocalDateTime,

    @TableField("create_by")
    val createBy: String,

    @TableField("update_time")
    val updateTime: LocalDateTime,

    @TableField("update_by")
    val updateBy: String,
) : Model<Pet>() {

    override fun pkVal(): Serializable {
        return id
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Pet) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (breedId != other.breedId) return false
        if (ownerId != other.ownerId) return false
        if (deleted != other.deleted) return false
        if (createTime != other.createTime) return false
        if (createBy != other.createBy) return false
        if (updateTime != other.updateTime) return false
        return updateBy == other.updateBy
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + breedId.hashCode()
        result = 31 * result + ownerId.hashCode()
        result = 31 * result + deleted.hashCode()
        result = 31 * result + createTime.hashCode()
        result = 31 * result + createBy.hashCode()
        result = 31 * result + updateTime.hashCode()
        result = 31 * result + updateBy.hashCode()
        return result
    }

    override fun toString(): String {
        return "Pet(id='$id', name='$name', breedId='$breedId', ownerId='$ownerId', deleted=$deleted, createTime=$createTime, createBy='$createBy', updateTime=$updateTime, updateBy='$updateBy')"
    }


}


