package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.InspCenterDTO;
import com.agile.qdmp.standalong.model.entity.integrator.InspCenter;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * InspCenters 数据转换
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:50
 */
@Mapper
public interface InspCenterConverter extends Convert<InspCenter, InspCenterDTO> {
    InspCenterConverter INSTANCE = Mappers.getMapper(InspCenterConverter.class);
}
