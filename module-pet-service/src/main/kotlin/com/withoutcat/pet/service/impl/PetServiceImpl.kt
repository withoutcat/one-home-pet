package com.withoutcat.pet.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.withoutcat.pet.data.entity.Pet
import com.withoutcat.pet.mapper.PetMapper
import com.withoutcat.pet.service.PetService
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.withoutcat.pet.data.vo.PetVO
import org.springframework.beans.factory.annotation.Autowired
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
class PetServiceImpl(
    @Autowired
    val petMapper: PetMapper
) : ServiceImpl<PetMapper, Pet>(), PetService {
    override fun getPetVOsByOwnerId(id: String): List<PetVO> {
        val wrapper = QueryWrapper<PetVO>().eq("p.owner_id", id)
        return petMapper.selectPetVOList(wrapper)
    }


}
