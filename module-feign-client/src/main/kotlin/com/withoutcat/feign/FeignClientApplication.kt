package com.withoutcat.feign

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.openfeign.EnableFeignClients



// 这两个注解可以保证依赖此工程的工程能够扫描到此工程的bean
@EnableFeignClients
@SpringBootApplication
class FeignClientApplication

// 因为这不是一个springboot web工程，所以不需要有main方法启动，只作为一个依赖工程存在

