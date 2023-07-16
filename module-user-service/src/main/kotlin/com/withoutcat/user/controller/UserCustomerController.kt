package com.withoutcat.user.controller

import com.withoutcat.user.data.dto.LoginRequestDTO
import com.withoutcat.user.data.dto.LoginResponseDTO
import com.withoutcat.user.service.UserCustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

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
class UserCustomerController(
    @Autowired
    private val userCustomerService: UserCustomerService,
) {

//    @PostMapping("/login")
//    fun login(@Validated @RequestBody loginRequestDTO: LoginRequestDTO): Mono<LoginResponseDTO> {
//        // 访问
//        userCustomerService
//    }

}
