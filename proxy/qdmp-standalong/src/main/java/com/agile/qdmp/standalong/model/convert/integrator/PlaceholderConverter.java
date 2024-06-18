package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.PlaceholderDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Placeholder;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Placeholder 数据转换
 *
 * @author wenbinglei
 * @date 2022-10-21 11:48:14
 */
@Mapper
public interface PlaceholderConverter extends Convert<Placeholder, PlaceholderDTO> {
    PlaceholderConverter INSTANCE = Mappers.getMapper(PlaceholderConverter.class);
}
