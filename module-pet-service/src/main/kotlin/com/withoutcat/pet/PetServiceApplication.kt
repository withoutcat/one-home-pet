package com.withoutcat.pet

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@MapperScan("com.withoutcat.pet.mapper")
@SpringBootApplication
class PetServiceApplication

fun main(args: Array<String>) {
    runApplication<PetServiceApplication>(*args)
}