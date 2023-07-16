import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.serialization")
	kotlin("plugin.spring")
}

group="com.withoutcat.user"

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

dependencies {
	// springboot 套件
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:${property("eurekaVersion")}")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	// 这里不应该指定版本号，但是不知道是不是BUG,不给版本号就编译失败
	implementation("com.baomidou:mybatis-plus-boot-starter:${property("mybatisPlusVersion")}")

	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok:${property("lombokVersion")}")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation(project(":module-generator-lib"))
	implementation("io.swagger.core.v3:swagger-annotations:2.2.15")
	developmentOnly("io.projectreactor:reactor-tools")
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
