package com.withoutcat.pet

import com.withoutcat.generator.data.dto.DataSource
import com.withoutcat.generator.entityGenerator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class PetServiceGenerator {
    @Value("\${spring.datasource.url}")
    lateinit var url: String

    @Value("\${spring.datasource.username}")
    lateinit var username: String

    @Value("\${spring.datasource.password}")
    lateinit var password: String

    @Test
    fun generator() {
        entityGenerator(
            DataSource(url, username, password),
            "pet",
            "t_pet_breed",
        )
    }
}