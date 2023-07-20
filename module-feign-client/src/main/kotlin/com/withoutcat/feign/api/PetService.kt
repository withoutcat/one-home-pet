package com.withoutcat.feign.api

import com.withoutcat.feign.dto.pet.PetDTO
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


@FeignClient("pet-service", path = "/pet")
interface PetService {
    @GetMapping("/owner/{id}")
    fun getPetByOwner(@PathVariable("id") id: String): List<PetDTO>

    @GetMapping("/health")
    fun healthCheck(): String

}