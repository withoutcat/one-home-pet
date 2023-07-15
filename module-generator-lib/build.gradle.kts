import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    id("org.springframework.boot") version "3.1.1"
    // io.spring.dependency-management 插件主要提供了一个标准方式来导入 Bill of Materials (BOM)
    // 它不包含具体的 Spring Boot 或 Spring Cloud 的依赖管理。
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    kotlin("plugin.spring") version "1.9.0"
    kotlin("plugin.jpa") version "1.9.0"
}

group = "com.withoutcat.generator"
version = "1.0-SNAPSHOT"


// 这是才是真正地依赖引入，这里的依赖除了是父工程自己使用意外，在打包时会进入每一个模块的发布件中，这相当于是共同依赖，为了避免重复声明
dependencies {
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("com.baomidou:mybatis-plus-boot-starter:${property("mybatisPlusVersion")}")
    implementation("com.baomidou:mybatis-plus-generator:${property("mybatisPlusVersion")}")
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("org.freemarker:freemarker")
    implementation("org.slf4j:slf4j-api")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
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

tasks.withType<Test> {
    useJUnitPlatform()
}