package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.LocationDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Location;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * LOCATION 数据转换
 *
 * @author wenbinglei
 * @date 2022-10-21 11:48:01
 */
@Mapper
public interface LocationConverter extends Convert<Location, LocationDTO> {
    LocationConverter INSTANCE = Mappers.getMapper(LocationConverter.class);
}
