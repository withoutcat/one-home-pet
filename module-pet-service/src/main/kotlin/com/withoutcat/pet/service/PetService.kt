package com.withoutcat.pet.service

import com.withoutcat.pet.data.entity.Pet
import com.baomidou.mybatisplus.extension.service.IService
import com.withoutcat.pet.data.vo.PetVO

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author withoutcat
 * @since 2023-07-17
 */
interface PetService : IService<Pet> {
    fun getPetVOsByOwnerId(id: String): List<PetVO>
}
