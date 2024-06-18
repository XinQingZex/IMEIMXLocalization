package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.OperationDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Operation;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Operation 数据转换
 *
 * @author wenbinglei
 * @date 2022-10-21 11:47:53
 */
@Mapper
public interface OperationConverter extends Convert<Operation, OperationDTO> {
    OperationConverter INSTANCE = Mappers.getMapper(OperationConverter.class);
}
