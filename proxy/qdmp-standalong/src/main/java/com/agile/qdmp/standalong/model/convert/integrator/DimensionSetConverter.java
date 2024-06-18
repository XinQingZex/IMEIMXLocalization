package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.DimensionSetDTO;
import com.agile.qdmp.standalong.model.entity.integrator.DimensionSet;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * DIMENSION_SET 数据转换
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:51
 */
@Mapper
public interface DimensionSetConverter extends Convert<DimensionSet, DimensionSetDTO> {
    DimensionSetConverter INSTANCE = Mappers.getMapper(DimensionSetConverter.class);
}
