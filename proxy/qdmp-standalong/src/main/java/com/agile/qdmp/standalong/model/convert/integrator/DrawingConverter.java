package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.DrawingDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Drawing;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * DRAWING 数据转换
 *
 * @author wenbinglei
 * @date 2022-10-22 15:03:30
 */
@Mapper
public interface DrawingConverter extends Convert<Drawing, DrawingDTO> {
    DrawingConverter INSTANCE = Mappers.getMapper(DrawingConverter.class);
}
