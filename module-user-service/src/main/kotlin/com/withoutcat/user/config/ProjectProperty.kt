package com.withoutcat.user.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "customer")
data class ProjectProperty(
    var testString: String = ""
)
