package com.withoutcat

import com.baomidou.mybatisplus.generator.FastAutoGenerator
import com.baomidou.mybatisplus.generator.config.*
import com.baomidou.mybatisplus.generator.config.po.TableField.MetaInfo
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine
import com.baomidou.mybatisplus.generator.type.TypeRegistry
import java.sql.Types
import java.util.*


fun entityGenerator() {
    FastAutoGenerator.create("url", "username", "password")
        .globalConfig { builder: GlobalConfig.Builder ->
            builder.author("baomidou") // 设置作者
                .enableSwagger() // 开启 swagger 模式
                .fileOverride() // 覆盖已生成文件
                .outputDir("D://") // 指定输出目录
        }
        .dataSourceConfig { builder: DataSourceConfig.Builder ->
            builder.typeConvertHandler { globalConfig: GlobalConfig?, typeRegistry: TypeRegistry, metaInfo: MetaInfo ->
                val typeCode = metaInfo.jdbcType.TYPE_CODE
                if (typeCode == Types.SMALLINT) {
                    // 自定义类型转换
                    return@typeConvertHandler DbColumnType.INTEGER
                }
                typeRegistry.getColumnType(metaInfo)
            }
        }
        .packageConfig { builder: PackageConfig.Builder ->
            builder.parent("com.baomidou.mybatisplus.samples.generator") // 设置父包名
                .moduleName("system") // 设置父包模块名
                .pathInfo(
                    Collections.singletonMap(
                        OutputFile.xml,
                        "D://"
                    )
                ) // 设置mapperXml生成路径
        }
        .strategyConfig { builder: StrategyConfig.Builder ->
            builder.addInclude("t_simple") // 设置需要生成的表名
                .addTablePrefix("t_", "c_", "v_") // 设置过滤表前缀
        }
        .templateEngine(FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
        .execute()
}