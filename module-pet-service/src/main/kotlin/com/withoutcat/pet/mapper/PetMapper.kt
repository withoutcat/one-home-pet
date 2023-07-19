package com.withoutcat.pet.mapper

import com.baomidou.mybatisplus.core.conditions.Wrapper
import com.withoutcat.pet.data.entity.Pet
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.toolkit.Constants
import com.withoutcat.pet.data.vo.PetVO
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author withoutcat
 * @since 2023-07-17
 */

@Mapper
interface PetMapper : BaseMapper<Pet> {


    /**
     * 构造一个查询条件wrapper ，使其可以命中多个字段
     *
     * @param queryWrapper
     * @return
     */
    fun selectPetVOList(@Param(Constants.WRAPPER) queryWrapper: Wrapper<PetVO>): List<PetVO>


}
