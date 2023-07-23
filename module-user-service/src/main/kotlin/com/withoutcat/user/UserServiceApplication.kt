package com.withoutcat.user

//import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import java.util.*

// 扫描自己和feign工程的bean
//@ComponentScan("com.withoutcat.user", "com.withoutcat.feign")
//@EnableFeignClients("com.withoutcat.feign")
//@NacosPropertySource(dataId = "user-service-dev.yaml", autoRefreshed = true)
@MapperScan("com.withoutcat.user.mapper")
@SpringBootApplication
@ComponentScan("com.withoutcat.*")
class UserServiceApplication

fun main(args: Array<String>) {
    runApplication<UserServiceApplication>(*args)
}





