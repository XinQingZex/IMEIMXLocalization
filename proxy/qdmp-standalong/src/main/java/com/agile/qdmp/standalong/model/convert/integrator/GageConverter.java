package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.GageDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Gage;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * GAGE 数据转换
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:55
 */
@Mapper
public interface GageConverter extends Convert<Gage, GageDTO> {
    GageConverter INSTANCE = Mappers.getMapper(GageConverter.class);
}
