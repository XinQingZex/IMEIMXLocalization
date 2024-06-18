package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.PlaceDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Place;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * PLACE 数据转换
 *
 * @author wenbinglei
 * @date 2022-10-21 11:47:49
 */
@Mapper
public interface PlaceConverter extends Convert<Place, PlaceDTO> {
    PlaceConverter INSTANCE = Mappers.getMapper(PlaceConverter.class);
}
