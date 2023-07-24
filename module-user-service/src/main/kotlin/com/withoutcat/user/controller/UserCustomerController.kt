package com.withoutcat.user.controller

import com.withoutcat.feign.api.PetService
import com.withoutcat.feign.dto.user.UserCustomerDTO
import com.withoutcat.user.config.ProjectProperty
import com.withoutcat.user.data.vo.UserCustomerVO
import com.withoutcat.user.data.dto.LoginRequestDTO
import com.withoutcat.user.service.UserCustomerService
import com.withoutcat.user.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.core.env.Environment
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author withoutcat
 * @since 2023-07-11
 */
@RestController
@RequestMapping("/customer")
@RefreshScope
class UserCustomerController(
    @Autowired
    private val userCustomerService: UserCustomerService,

    @Autowired
    private val petService: PetService,

    @Autowired
    private val userService: UserService,

    @Autowired
    private val environment: Environment,

    @Value("\${customer.testString}")
    private var testString: String?,

    @Autowired
    private val projectProperty: ProjectProperty,
) {

    val logger: Logger = LoggerFactory.getLogger(this::class.java)



    @GetMapping("/health")
    fun healthCheck(): String {
        return petService.healthCheck()
    }

    @GetMapping("/test")
    fun testString(): String {
        return projectProperty.toString()
    }


    @PostMapping("/login")
    fun login(@Validated @RequestBody loginRequestDTO: LoginRequestDTO): UserCustomerDTO {
        logger.info("#### /customer/login #### 开始，请求参数 : $loginRequestDTO")
        return UserCustomerVO(loginRequestDTO.account)
            .apply { password = loginRequestDTO.password }
            .let { userService.getUserByAccount(it) as UserCustomerVO? }
            ?.let { vo ->
                vo.toUserCustomerDTO()
                    .also { logger.info("请求pet服务") }
                    .apply { this.pets = petService.getPetByOwner(id) }
                    .also {
                        logger.info("pet信息: ${it.pets}")
                        logger.info("#### /customer/login #### 结束")
                    }
            } ?: throw Exception("用户不存在")
    }
}