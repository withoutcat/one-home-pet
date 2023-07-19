package com.withoutcat.pet.controller

import com.withoutcat.pet.data.vo.PetVO
import com.withoutcat.pet.service.PetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux

/**
 * <p>
 * 客户的宠物表 前端控制器
 * </p>
 *
 * @author withoutcat
 * @since 2023-07-19
 */
@RestController
@RequestMapping("/pet")
class PetController(
    @Autowired
    val petService: PetService
) {

    /**
     * 根据主人id获取宠物列表
     *
     * @param id
     * @return
     */
    @GetMapping("/owner/{id}")
    fun getPetByOwner(@PathVariable("id") id: String): Flux<PetVO> {
        val petList = petService.getPetVOsByOwnerId(id)
        return Flux.fromIterable(petList)
    }

}
