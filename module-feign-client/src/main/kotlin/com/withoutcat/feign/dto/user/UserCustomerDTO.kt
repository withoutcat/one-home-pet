package com.withoutcat.feign.dto.user

import com.withoutcat.feign.dto.pet.PetDTO

data class UserCustomerDTO(
    val id: String,
    val account: String,
    val name: String,
    val phone: String,
    val address: String,
    val type: String,
    var pets: List<PetDTO> = emptyList(),

    // 客户信息
    val balance: Double,
    val vipLevel: Int,
    // TODO:折扣信息
)
