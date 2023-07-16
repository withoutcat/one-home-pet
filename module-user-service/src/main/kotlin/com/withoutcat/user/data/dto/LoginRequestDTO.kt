package com.withoutcat.user.data.dto


import jakarta.validation.constraints.Size


data class LoginRequestDTO(
    @Size(min = 6, max = 20, message = "用户名长度必须在6-20之间")
    val username: String,
    @Size(min = 4, max = 8, message = "密码长度必须在4-8之间")
    val password: String,
)