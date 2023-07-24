package com.withoutcat.pet

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@EnableConfigurationProperties
@ConfigurationPropertiesScan
@MapperScan("com.withoutcat.pet.mapper")
@SpringBootApplication
@ComponentScan("com.withoutcat.*")
class PetServiceApplication

fun main(args: Array<String>) {
    runApplication<PetServiceApplication>(*args)
}