package com.withoutcat.user.controller

import com.withoutcat.user.domain.LoginUser
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

/**
 *
 * @author withoutcat
 * @since 2023-07-11
 */
@RestController
@RequestMapping("/user")
class UserController
