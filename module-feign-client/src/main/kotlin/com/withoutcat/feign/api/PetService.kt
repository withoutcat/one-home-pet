package com.withoutcat.feign.api

import com.withoutcat.feign.dto.pet.PetDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody


/**
 * ("pet-service", path = "/pet") 这里的path属性对应的是配置文件里的context-path
 * 如果把controller中的@RequestMapping("/pet")拿过来，虽然url可以拼接成功，但是@PathVariable的参数会解释失败
 * 所以正确的做法是把controller中的@RequestMapping("/pet")直接拼接到@GetMapping里
 */
@FeignClient("pet-service")
@ResponseBody
interface PetService {
    @GetMapping("pet/owner/{id}")
    fun getPetByOwner(@PathVariable("id") id: String): List<PetDTO>

    @GetMapping("pet/health")
    fun healthCheck(): String

}