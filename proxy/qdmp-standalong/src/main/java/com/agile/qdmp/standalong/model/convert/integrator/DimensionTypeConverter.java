package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.DimensionTypeDTO;
import com.agile.qdmp.standalong.model.entity.integrator.DimensionType;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * DimensionType 数据转换
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:59
 */
@Mapper
public interface DimensionTypeConverter extends Convert<DimensionType, DimensionTypeDTO> {
    DimensionTypeConverter INSTANCE = Mappers.getMapper(DimensionTypeConverter.class);
}
