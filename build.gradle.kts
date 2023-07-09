import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    kotlin("plugin.spring") version "1.9.0"
    kotlin("plugin.jpa") version "1.9.0"
}

// 全局指定jdk版本
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}


configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

group = "com.withoutcat"
version = "1.0-SNAPSHOT"

// 全局指定仓库
allprojects {
    // 把 https://developer.aliyun.com/mvn/view 这里的仓库全部搬过来了
    // 重新排序了一下
    repositories {
        maven("https://maven.aliyun.com/repository/public")
        maven("https://maven.aliyun.com/repository/jcenter")
        mavenCentral()
        maven("https://maven.aliyun.com/repository/google")
        maven("https://maven.aliyun.com/repository/central")
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        maven("https://maven.aliyun.com/repository/spring")
        maven("https://maven.aliyun.com/repository/spring-plugin")
        maven("https://maven.aliyun.com/repository/apache-snapshots")
        maven("https://maven.aliyun.com/repository/releases")
        maven("https://maven.aliyun.com/repository/snapshots")
        maven("https://maven.aliyun.com/repository/grails-core")
        maven("https://maven.aliyun.com/repository/mapr-public")
        maven("https://maven.aliyun.com/repository/staging-alpha")
        maven("https://maven.aliyun.com/repository/staging-alpha-group")
    }
}

ext {
    set("junitVersion", "5.9.1")
    set("springCloudVersion", "2022.0.3")
    set("springBootVersion", "3.1.1")
    set("mybatisPlusVersion", "3.5.3.1")
}

// 依赖管理，定义了全局的依赖版本，子工程会继承这里定义好的版本号，除非重写
// dependencyManagement只是对全局依赖进行版本声明，不会引入任何依赖
dependencyManagement {
    imports {
        // spring cloud 套件
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        // springboot 套件
        mavenBom("org.springframework.boot:spring-boot-starter:${property("springBootVersion")}")
        mavenBom("org.springframework.boot:spring-boot-dependencies:${property("springBootVersion")}")
        mavenBom("org.springframework.boot:spring-boot-starter-test:${property("springBootVersion")}")
        mavenBom("org.springframework.boot:spring-boot-starter-web:${property("springBootVersion")}")
        mavenBom("org.springframework.boot:spring-boot-devtools:${property("springBootVersion")}")
        mavenBom("org.springframework.boot:spring-boot-starter-webflux:${property("springBootVersion")}")
        mavenBom("org.springframework.boot:spring-boot-starter-data-jdbc:${property("springBootVersion")}")
        // runtimeOnly
        mavenBom("com.h2database:h2:2.2.220")
        // runtimeOnly
        mavenBom("org.springframework.boot:spring-boot-devtools:${property("springBootVersion")}")

        // spring cloud alibaba
        mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:2021.0.4.0")
        // druid
        mavenBom("com.alibaba:druid:1.2.18")
        // mysql驱动，注意要跟mysql数据库实例版本保持一致
        mavenBom("mysql:mysql-connector-java:8.0.33")
        mavenBom("com.baomidou:mybatis-plus-boot-starter:3.5.3.1")
        // kotlin 套件
        mavenBom("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
        mavenBom("org.jetbrains.kotlin:kotlin-reflect:1.9.0")

        // mybatis-plus
        mavenBom("com.baomidou:mybatis-plus-boot-starter:${property("mybatisPlusVersion")}")
        // 逆向工程
        mavenBom("com.baomidou:mybatis-plus-generator:${property("mybatisPlusVersion")}")

        mavenBom("io.swagger.core.v3:swagger-annotations:2.2.14")
    }
}

// 这是才是真正地依赖引入，因为最外层没有实际的代码所以暂时不引入任何依赖
dependencies {
    implementation("com.baomidou:mybatis-plus-boot-starter:${property("mybatisPlusVersion")}")
    implementation("com.baomidou:mybatis-plus-generator:${property("mybatisPlusVersion")}")
}

/**
 * 这段代码是一个Gradle构建脚本中的配置，它的作用是向所有类型为KotlinCompile的任务（即Kotlin编译任务）添加一个编译选项，使得Kotlin编译器使用JSR-305注解进行更严格的空值检查。
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
