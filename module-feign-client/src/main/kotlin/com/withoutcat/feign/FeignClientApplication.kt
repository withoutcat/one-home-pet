package com.withoutcat.feign

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients


@EnableFeignClients
@SpringBootApplication
class FeignClientApplication

fun main(args: Array<String>) {
    runApplication<FeignClientApplication>(*args)
}