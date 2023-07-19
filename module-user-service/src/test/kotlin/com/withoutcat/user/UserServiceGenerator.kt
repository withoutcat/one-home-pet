package com.withoutcat.user


import com.withoutcat.generator.data.dto.DataSource
import com.withoutcat.generator.entityGenerator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import java.util.*


/**
 * 指定当前生效的配置文件( active profile)，如果是 application-dev.yaml 则 dev
 * 默认的话会根据 application.yaml 中的include来决定
 */
//@ActiveProfiles("dev")
/** 指定  @SpringBootApplication  启动类 和 端口  **/
@SpringBootTest(
    classes = [UserServiceApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class UserServiceGenerator {
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
            "user",
            "t_user", "t_user_customer", "t_user_staff",
        )
    }
}