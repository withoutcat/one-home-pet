package com.withoutcat.user.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import org.springframework.context.annotation.Configuration

//@Configuration
//@ConstructorBinding
@ConfigurationProperties(prefix = "customer")
data class ProjectProperty(
    val testString: String,
    val env: String,
)
