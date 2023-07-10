package com.withoutcat.dto

import lombok.Value

@Value
class GitUser(
    val userName: String,
    val userEmail: String,
)