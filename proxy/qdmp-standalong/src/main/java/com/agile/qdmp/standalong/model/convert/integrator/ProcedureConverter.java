package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.ProcedureDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Procedure;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Procedure 数据转换
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:46
 */
@Mapper
public interface ProcedureConverter extends Convert<Procedure, ProcedureDTO> {
    ProcedureConverter INSTANCE = Mappers.getMapper(ProcedureConverter.class);
}
