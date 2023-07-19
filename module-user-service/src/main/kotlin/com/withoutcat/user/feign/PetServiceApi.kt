package com.withoutcat.user.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@FeignClient("pet-service")
interface PetServiceApi {
    @GetMapping("/owner/{id}")
    fun getPetByOwner(@PathVariable("id") id: String): Mono<String>

}