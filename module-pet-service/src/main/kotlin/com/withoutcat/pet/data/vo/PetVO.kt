package com.withoutcat.pet.data.vo

import com.withoutcat.pet.data.entity.Pet
import com.withoutcat.pet.data.enums.BreedFamily
import kotlinx.serialization.Serializable
import java.time.LocalDateTime



class PetVO(
    id: String,
    name: String,
    breedId: String,
    val breedName: String,

    val breedFamily: BreedFamily,
    ownerId: String,
    deleted: Boolean,
    createTime: LocalDateTime,
    createBy: String,
    updateTime: LocalDateTime,
    updateBy: String,
) : Pet(id, name, breedId, ownerId, deleted, createTime, createBy, updateTime, updateBy) {

}
