package com.withoutcat.dto

import lombok.Data


@Data
class DataSource(
    val url: String,
    val username: String,
    val password: String,
)