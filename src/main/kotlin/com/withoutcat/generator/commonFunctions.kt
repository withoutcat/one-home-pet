/**
 * 这个文件中包含了一些公共的函数，比如获取git用户信息，调用mybatis plus的逆向工程生成实体类等
 */
package com.withoutcat.generator

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.generator.FastAutoGenerator
import com.baomidou.mybatisplus.generator.config.*
import com.baomidou.mybatisplus.generator.config.po.TableField.MetaInfo
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery
import com.baomidou.mybatisplus.generator.config.rules.DateType
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler
import com.baomidou.mybatisplus.generator.type.TypeRegistry
import com.withoutcat.dto.DataSource
import com.withoutcat.dto.GitUser
import org.springframework.core.io.ClassPathResource
import java.io.BufferedReader
import java.io.InputStreamReader
import java.sql.Types
import java.util.*

fun main() {

}

fun readSelfResources(): String {
    ClassPathResource("application-common-dev.yaml").inputStream.use {
        val properties = Properties()
        properties.load(it)
        val url = properties.getProperty("spring.datasource.url")
        val hello = properties.getProperty("hello")
        val username = properties.getProperty("spring.datasource.username")
        val password = properties.getProperty("spring.datasource.password")
        return hello
    }
}


/**
 * 获取当前环境中的git用户信息
 * 返回git用户信息的简单封装
 */
val gitUser = lazy {
    // 调用终端命令行
    // 执行命令：git config --global  --list
    // 正常来说应该返回两行信息
    // user.name=withoutcat
    // user.email=sunziwens@gmail.com
    // 取第一行信息，使用println()打印到控制台
    val process = Runtime.getRuntime().exec("git config --global  --list")
    val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))
    var line = bufferedReader.readLine()
    val userName = line.split("=")[1]
    // 读取第二行信息
    line = bufferedReader.readLine()
    val userEmail = line.split("=")[1]
    GitUser(userName, userEmail)
}.value

/**
 * 调用Mybatis Plus的逆向工程生成实体类
 *
 */
fun entityGenerator(dataSource: DataSource, tables: Array<String>) {
    FastAutoGenerator.create(
        dataSource.url,
        dataSource.username,
        dataSource.password
    ).globalConfig { builder: GlobalConfig.Builder -> // 全局配置
        builder.author(gitUser.userName) // 设置作者
            .enableSpringdoc() // 开启 springdoc 模式 springdoc使用的是openAPI3.0，它是swagger2.0的迭代升级版本并且改了名字，是新的规范
//            .enableSwagger() // 开启 swagger 模式
            .enableKotlin() // 开启 Kotlin 模式
            .dateType(DateType.TIME_PACK) // 设置日期类型为 java8 下的 java.time 类型
            .disableOpenDir() // 禁止生成后打开输出目录
//            .fileOverride() // 覆盖已生成文件 已替换成enableFileOverride()
            .outputDir("${System.getProperty("user.dir")}/src/main/kotlin") // 指定输出目录
    }.dataSourceConfig { builder: DataSourceConfig.Builder -> // 可选配置
        builder.typeConvertHandler { globalConfig: GlobalConfig?, typeRegistry: TypeRegistry, metaInfo: MetaInfo ->
            val typeCode = metaInfo.jdbcType.TYPE_CODE
            if (typeCode == Types.SMALLINT) {
                // 自定义类型转换
                return@typeConvertHandler DbColumnType.INTEGER
            }
            typeRegistry.getColumnType(metaInfo)
        }.schema("one_hom_pet_dev_user") // 设置数据库 schema，默认不设置
            .dbQuery(MySqlQuery())
            .keyWordsHandler(MySqlKeyWordsHandler())
    }.packageConfig { builder: PackageConfig.Builder ->
        builder.parent("com.withoutcat.user") // 设置父包名
//            .moduleName("module-user-service") // 设置父包模块名
            .entity("entity") // 设置Entity包名，默认entity
            .service("service") // 设置Service包名，默认service
            .serviceImpl("service.impl") // 设置Service Impl包名，默认service.impl
            .mapper("mapper") // 设置Mapper包名，默认mapper
            .mapper("mapper") // 设置Mapper Xml包名，默认mapper.xml
            .controller("controller") // 设置Controller包名，默认controller
            .pathInfo(
                Collections.singletonMap(
                    OutputFile.xml,
                    "${System.getProperty("user.dir")}/src/main/resources/mapper"
                )
            ) // 设置mapperXml生成路径
    }.strategyConfig { builder: StrategyConfig.Builder ->
        builder.addInclude(*tables) // 设置需要生成的表名
            .addTablePrefix("t_", "c_", "v_") // 设置过滤表前缀

            .entityBuilder() // Entity 策略配置
            .enableTableFieldAnnotation()       // 开启生成实体时生成字段注解
            .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：下划线转驼峰命
            .columnNaming(NamingStrategy.underline_to_camel)    //数据库表字段映射到实体的命名策略：下划线转驼峰命
            .disableSerialVersionUID()  //不实现 Serializable 接口，不生产 SerialVersionUID
            // .enableChainModel() // 	开启链式模型 对kotlin不生效，kt模板里没有对lombok的判断
            // .enableLombok() // 开启 lombok 模型 对kotlin不生效，kt模板里没有对lombok的判断
            .enableActiveRecord() // 开启 ActiveRecord 模式
            .idType(IdType.ASSIGN_UUID) // 主键类型
            .enableFileOverride() // 覆盖已生成文件 注意只有实体类才有覆盖的必要，其他层里会有业务代码不要开启覆盖！

            .serviceBuilder() // Service 策略配置
            .formatServiceFileName("%sService") //格式化 service 接口文件名称，%s进行匹配表名，如 UserService
            .formatServiceImplFileName("%sServiceImpl") //格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl

            .controllerBuilder() // Controller 策略配置
            .formatFileName("%sController") //格式化 Controller 类文件名称，%s进行匹配表名，如 UserController
            .enableRestStyle()  //开启生成 @RestController 控制器

            .mapperBuilder() // Mapper 策略配置
            .superClass(BaseMapper::class.java) //设置父类
    }.templateEngine(FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板，注意必须手动引入依赖
        .execute()
}

