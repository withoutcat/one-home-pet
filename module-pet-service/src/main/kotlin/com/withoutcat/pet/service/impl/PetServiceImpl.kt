package com.withoutcat.pet.service.impl

import com.withoutcat.pet.entity.Pet
import com.withoutcat.pet.mapper.PetMapper
import com.withoutcat.pet.service.PetService
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author withoutcat
 * @since 2023-07-17
 */
@Service
open class PetServiceImpl : ServiceImpl<PetMapper, Pet>(), PetService {

}
