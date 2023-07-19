package com.withoutcat.user.controller

import com.withoutcat.user.data.vo.UserCustomerVO
import com.withoutcat.user.data.dto.LoginRequestDTO
import com.withoutcat.user.feign.PetServiceApi
import com.withoutcat.user.service.UserCustomerService
import com.withoutcat.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

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

    @Autowired
    private val petServiceApi: PetServiceApi,

    @Autowired
    private val userService: UserService,
) {

    @PostMapping("/login")
    fun login(@Validated @RequestBody loginRequestDTO: LoginRequestDTO): Mono<UserCustomerVO> {

        val user = UserCustomerVO(loginRequestDTO.account).apply {
            password = loginRequestDTO.password
        }

        val res = userService.getUserByAccount(user) as UserCustomerVO?
        return res?.let {u ->
            Mono.just(u).publishOn(Schedulers.boundedElastic()).doOnNext {

                petServiceApi.getPetByOwner(it.id).subscribe { pet ->

                }
            }
        } ?: Mono.empty()
    }

}
