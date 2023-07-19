package com.withoutcat.pet.cliens

import com.withoutcat.pet.data.vo.PetVO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import reactor.core.publisher.Flux


@FeignClient("pet-service")
interface PetServiceApi {
    @GetMapping("/owner/{id}")
    fun getPetByOwner(@PathVariable("id") id: String): Flux<PetVO>

}