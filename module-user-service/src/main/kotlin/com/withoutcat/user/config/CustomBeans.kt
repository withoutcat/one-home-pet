package com.withoutcat.user.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class CustomBeans {

    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()

}