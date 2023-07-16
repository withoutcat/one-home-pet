package ${package.Entity}

<#list table.importPackages as pkg>
import ${pkg}
</#list>
<#if springdoc>
import io.swagger.v3.oas.annotations.media.Schema
<#elseif swagger>
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
</#if>

/**
<#-- 在kotlin中使用Markdown语法编写注释，非Javadoc中的HTML语法，这里使用了二级标题 -->
 * ## ${table.comment}
 *
 * @author ${author}
 * @since ${date}
 */
<#if table.convert>
@TableName("${schemaName}${table.name}")
</#if>
<#if springdoc>
@Schema(name = "${entity}对象", description = "${table.comment!}")
<#elseif swagger>
@ApiModel(value = "${entity}对象", description = "${table.comment!}")
</#if>
data class ${entity} (
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>
    <#if field.comment!?length gt 0>
        <#if springdoc>
    @Schema(description = "${field.comment}")
        <#elseif swagger>
    @ApiModelProperty("${field.comment}")
        <#else>
    /**
    * ${field.comment}
    */
        </#if>
    </#if>
    <#if field.keyFlag>
    <#-- 主键 -->
        <#if field.keyIdentityFlag>
    @TableId(value = "${field.annotationColumnName}", type = IdType.AUTO)
        <#elseif idType ??>
    @TableId(value = "${field.annotationColumnName}", type = IdType.${idType})
        <#elseif field.convert>
    @TableId("${field.annotationColumnName}")
        </#if>
    <#-- 普通字段 -->
    <#elseif field.fill??>
    <#-- -----   存在字段填充设置   ----->
        <#if field.convert>
    @TableField(value = "${field.annotationColumnName}", fill = FieldFill.${field.fill})
        <#else>
    @TableField(fill = FieldFill.${field.fill})
        </#if>
    <#elseif field.convert>
    @TableField("${field.annotationColumnName}")
    </#if>
<#-- 乐观锁注解 -->
    <#if field.versionField>

    @Version
    </#if>
<#-- 逻辑删除注解 -->
    <#if field.logicDeleteField>
    @TableLogic
    </#if>
<#-- 数据库所有字段均为not null -->
    <#if field.propertyType == "Integer">
    val ${field.propertyName}: Int,
    <#elseif field.propertyType == "Object">
    val ${field.propertyName}: Any,
    <#else>
    val ${field.propertyName}: ${field.propertyType},
    </#if>
</#list>
<#-- ----------  END 字段循环遍历  ---------->
)<#if superEntityClass??>
 : ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
 : Model<${entity}>() {
<#elseif entitySerialVersionUID>
 : Serializable {
<#else>
 {
</#if>

<#if entityColumnConstant>
    companion object {
<#list table.fields as field>

        const val ${field.name?upper_case} : String = "${field.name}"

</#list>
    }

</#if>
<#if activeRecord>
    override fun pkVal(): Serializable? {
<#if keyPropertyName??>
        return ${keyPropertyName}
<#else>
        return null
</#if>
    }
</#if>

}


