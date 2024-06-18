package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.CharacterDesignatorDTO;
import com.agile.qdmp.standalong.model.entity.integrator.CharacterDesignator;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * CharacterDesignator 数据转换
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:59
 */
@Mapper
public interface CharacterDesignatorConverter extends Convert<CharacterDesignator, CharacterDesignatorDTO> {
    CharacterDesignatorConverter INSTANCE = Mappers.getMapper(CharacterDesignatorConverter.class);
}
