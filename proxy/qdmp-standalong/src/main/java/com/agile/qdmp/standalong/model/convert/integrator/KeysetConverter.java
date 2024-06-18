package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.KeysetDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Keyset;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Keyset 数据转换
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:59
 */
@Mapper
public interface KeysetConverter extends Convert<Keyset, KeysetDTO> {
    KeysetConverter INSTANCE = Mappers.getMapper(KeysetConverter.class);
}
