package com.withoutcat.pet.data.enums

import com.baomidou.mybatisplus.annotation.EnumValue
import com.fasterxml.jackson.annotation.JsonValue


enum class BreedFamily(desc: String) {
    @EnumValue
    CAT("猫科"),

    @EnumValue
    DOG("犬科"),

    @EnumValue
    OTHER("其他科");

    /**
     * mybatis映射到枚举类时会遍历BreedFamily.entries
     * 两种方式去选择实例
     * - 实现了IEnum接口: 读value这个属性
     * - @EnumValue()注解: 读被标记的属性
     */
    @EnumValue
    val value = this.name

    /**
     * enum实例转JSON时
     * - 默认调用枚举的name属性
     * - 使用@JsonValue标记的属性
     */
    @JsonValue
    val desc = desc

}