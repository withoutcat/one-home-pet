package com.withoutcat.feign.dto.pet


data class PetDTO(
    val id: String,
    val name: String,
    val breedId: String,
    val breedName: String,
    val breedFamily: String,
    val ownerId: String,
)
