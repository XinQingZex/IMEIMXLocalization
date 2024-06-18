package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.WorkCellDTO;
import com.agile.qdmp.standalong.model.entity.integrator.WorkCell;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * WorkCell 数据转换
 *
 * @author wenbinglei
 * @date 2022-10-21 11:48:07
 */
@Mapper
public interface WorkCellConverter extends Convert<WorkCell, WorkCellDTO> {
    WorkCellConverter INSTANCE = Mappers.getMapper(WorkCellConverter.class);
}
