import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    id("org.springframework.boot")
    // 不加这个配置文件里的spring.devtools.restart.enabled就不生效
    id("io.spring.dependency-management")
    kotlin("plugin.serialization")
    kotlin("jvm")
    kotlin("plugin.spring")
}

group = "com.withoutcat.feign"



dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${property("kotlinxJsonVersion")}")
//    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:${property("feignVersion")}")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:${property("eurekaVersion")}")



    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // 提供更多的注解还有Lambda表达式，如果是junit4的话，需要在测试类上加@RunWith(SpringRunner::class)
    testImplementation("org.junit.jupiter:junit-jupiter-api")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

/**
 * 使kotlin编译后的字节码文件可以运行在jdk17的虚拟机里
 * 并且使用JSR-305注解进行更严格的空值检查
 *
 * 具体来说，这段代码使用了Kotlin DSL语法，它的作用是：
 *
 * 使用tasks.withType方法，找到所有类型为KotlinCompile的任务。
 * 对于每个找到的KotlinCompile任务，使用kotlinOptions方法来设置编译选项。
 * 在编译选项中添加一个名为freeCompilerArgs的属性，该属性指定了Kotlin编译器的命令行参数。
 * 在freeCompilerArgs的值中添加了一个参数"-Xjsr305=strict"，该参数告诉Kotlin编译器使用JSR-305注解进行更严格的空值检查。
 * JSR-305是一项Java规范，用于提供更严格的空值检查功能。在Kotlin中，可以通过使用该规范中的注解来启用更严格的空值检查。使用该注解可以帮助开发人员更早地发现空值异常，从而提高代码的可靠性和安全性。
 *
 * 因此，这段代码的作用是为所有Kotlin编译任务添加一个编译选项，使得Kotlin编译器使用JSR-305注解进行更严格的空值检查，从而提高代码的可靠性和安全性。
 */
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}