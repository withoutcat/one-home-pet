package com.withoutcat.user.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class PetDTO(
    val id: String,
    val name: String,
    val breedId: String,
    val breedName: String,
    val breedFamily: String,
    val ownerId: String,

)
