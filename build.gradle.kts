plugins {
    java
    id("org.springframework.boot") version "3.0.9"
    // io.spring.dependency-management 插件主要提供了一个标准方式来导入 Bill of Materials (BOM)
    // 它不包含具体的 Spring Boot 或 Spring Cloud 的依赖管理。
    id("io.spring.dependency-management") version "1.1.2"
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
    set("springCloudAlibabaVersion", "2022.0.0.0-RC2")
    set("springBootVersion", "3.0.9")
    set("springCloudStarterVer", "4.0.3")
    set("mybatisPlusVersion", "3.5.3.1")
//    set("eurekaVersion", "4.0.2")
    set("lombokVersion", "1.18.28")
    set("kotlinxJsonVersion", "1.5.1")
    set("nacosVer", "2.2.3")
    set("nacosConfVer", "0.2.12")
}

// 依赖管理，定义了全局的依赖版本，子工程会继承这里定义好的版本号，除非重写
// dependencyManagement只是对全局依赖进行版本声明，不会引入任何依赖
dependencyManagement {
    imports {
        mavenBom("org.jetbrains.kotlinx:kotlinx-serialization-json:${property("kotlinxJsonVersion")}")

        // ------------- spring cloud 套件 -------------
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
//        mavenBom("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:${property("eurekaVersion")}")
//        mavenBom("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:${property("eurekaVersion")}")
        mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:${property("springCloudAlibabaVersion")}")
        mavenBom("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:${property("springCloudAlibabaVersion")}")
        // nacos配置中心，项目启动时会先去bootstrap.yaml里读取nacos地址等信息，然后再去nacos里读取配置，然后再和application.yaml文件合并，最后启动项目
        mavenBom("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:${property("springCloudAlibabaVersion")}")
//        mavenBom("com.alibaba.boot:nacos-config-spring-boot-starter:${property("nacosConfVer")}")

        // 这个依赖可以保证项目启动时优先去读取bootstrap.yaml文件
        mavenBom("org.springframework.cloud:spring-cloud-starter-bootstrap:${property("springCloudStarterVer")}")
        mavenBom("org.springframework.cloud:spring-cloud-starter-loadbalancer:${property("springCloudStarterVer")}")
        mavenBom("org.springframework.cloud:spring-cloud-starter-openfeign:${property("springCloudStarterVer")}")

        // ------------- springboot 套件 -------------
        mavenBom("org.springframework.boot:spring-boot-starter:${property("springBootVersion")}")
        mavenBom("org.springframework.boot:spring-boot-dependencies:${property("springBootVersion")}")
        mavenBom("org.springframework.boot:spring-boot-starter-test:${property("springBootVersion")}")
        mavenBom("org.springframework.boot:spring-boot-starter-web:${property("springBootVersion")}")
        mavenBom("org.springframework.boot:spring-boot-starter-webflux:${property("springBootVersion")}")
        mavenBom("org.springframework.boot:spring-boot-devtools:${property("springBootVersion")}")
        mavenBom("org.springframework.boot:spring-boot-starter-data-jdbc:${property("springBootVersion")}")
        mavenBom("org.springframework.boot:spring-boot-starter-data-redis:${property("springBootVersion")}")
        mavenBom("org.springframework.boot:spring-boot-configuration-processor:${property("springBootVersion")}")
        // controller 参数校验
        mavenBom("org.springframework.boot:spring-boot-starter-validation:${property("springBootVersion")}")
        // runtimeOnly
        mavenBom("com.h2database:h2:2.2.220")
        // runtimeOnly
        mavenBom("org.springframework.boot:spring-boot-devtools:${property("springBootVersion")}")

        // druid
        mavenBom("com.alibaba:druid:1.2.18")
        // mysql驱动，注意要跟mysql数据库实例版本保持一致
        mavenBom("mysql:mysql-connector-java:8.0.33")
        mavenBom("com.baomidou:mybatis-plus-boot-starter:${property("mybatisPlusVersion")}")
        // kotlin 套件
        mavenBom("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
        mavenBom("org.jetbrains.kotlin:kotlin-reflect:1.9.0")

        // mybatis-plus
        mavenBom("com.baomidou:mybatis-plus-boot-starter:${property("mybatisPlusVersion")}")
        // 逆向工程
        mavenBom("com.baomidou:mybatis-plus-generator:${property("mybatisPlusVersion")}")

//        mavenBom("io.swagger:swagger-annotations:1.6.11")

        mavenBom("org.freemarker:freemarker:2.3.32")
        // log
        mavenBom("org.slf4j:slf4j-api:2.0.7")
        mavenBom("org.projectlombok:lombok:1.18.28")
        mavenBom("org.apache.velocity:velocity-engine-core:2.3")
        mavenBom("io.projectreactor:reactor-tools:3.5.7")
        mavenBom("org.junit.jupiter:junit-jupiter-api:5.9.3")
        mavenBom("io.swagger.core.v3:swagger-annotations:2.2.15")
    }
}


