package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.DimensionDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Dimension;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * DIMENSION 数据转换
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:56
 */
@Mapper
public interface DimensionConverter extends Convert<Dimension, DimensionDTO> {
    DimensionConverter INSTANCE = Mappers.getMapper(DimensionConverter.class);
}
