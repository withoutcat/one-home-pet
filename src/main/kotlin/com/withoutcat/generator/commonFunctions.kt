/**
 * 这个文件中包含了一些公共的函数，比如获取git用户信息，调用mybatis plus的逆向工程生成实体类等
 */
package com.withoutcat.generator

import com.baomidou.mybatisplus.generator.FastAutoGenerator
import com.baomidou.mybatisplus.generator.config.*
import com.baomidou.mybatisplus.generator.config.po.TableField.MetaInfo
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler
import com.baomidou.mybatisplus.generator.type.TypeRegistry
import com.withoutcat.dto.DataSource
import com.withoutcat.dto.GitUser
import java.io.BufferedReader
import java.io.InputStreamReader
import java.sql.Types
import java.util.*

fun main() {

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
            .enableSwagger() // 开启 swagger 模式
            .enableKotlin() // 开启 Kotlin 模式
            .disableOpenDir() // 禁止生成后打开输出目录
//            .fileOverride() // 覆盖已生成文件 已替换成enableFileOverride()
            .outputDir("D://") // 指定输出目录
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
            .mapper("mapper.xml") // 设置Mapper Xml包名，默认mapper.xml
            .controller("controller") // 设置Controller包名，默认controller
            .pathInfo(
                Collections.singletonMap(
                    OutputFile.xml,
                    "C://mapper"
                )
            ) // 设置mapperXml生成路径
    }.strategyConfig { builder: StrategyConfig.Builder ->
        builder.addInclude(*tables) // 设置需要生成的表名
            .addTablePrefix("t_", "c_", "v_") // 设置过滤表前缀
            .entityBuilder() // Entity 策略配置
//            .superClass() // 自定义继承的Entity类全称，带包名
            .enableChainModel() // 	开启链式模型
            .enableLombok() // 开启 lombok 模型
            .enableFileOverride() // 覆盖已生成文件

            .serviceBuilder() // Service 策略配置
            .enableFileOverride() // 覆盖已生成文件

            .controllerBuilder() // Controller 策略配置
            .enableFileOverride() // 覆盖已生成文件

            .mapperBuilder() // Mapper 策略配置
            .enableFileOverride() // 覆盖已生成文件
    }.templateEngine(FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板，注意必须手动引入依赖
        .execute()
}

