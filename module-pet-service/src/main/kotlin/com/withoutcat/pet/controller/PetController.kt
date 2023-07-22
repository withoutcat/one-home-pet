package com.withoutcat.pet.controller

import com.withoutcat.feign.dto.pet.PetDTO
import com.withoutcat.pet.service.PetService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    val petService: PetService,

    @Autowired
    val environment: Environment,

    @Autowired
    val applicationContext: ApplicationContext
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/health")
    fun healthCheck(): String {
        // 获取pet-service启动的端口号
        return environment.getProperty("local.server.port")
            .also { log.info("pet-service启动的端口号：$it") }
            ?: "端口号未知"
    }

    /**
     * 根据主人id获取宠物列表
     *
     * @param id
     * @return
     */
    @GetMapping("/owner/{id}")
    fun getPetByOwner(@PathVariable("id") id: String): List<PetDTO> {
        log.info("获取宠物列表，主人id: $id")
        val petList = petService.getPetVOsByOwnerId(id)
        return petList.map { pet ->
            PetDTO(
                id = pet.id,
                name = pet.name,
                breedId = pet.breedId,
                breedName = pet.breedName,
                breedFamily = pet.breedFamily.desc,
                ownerId = pet.ownerId,
            )
        }.also { log.info("宠物列表：$it") }
    }

}
