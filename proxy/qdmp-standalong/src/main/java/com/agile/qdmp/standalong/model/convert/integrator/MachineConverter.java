package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.MachineDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Machine;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Machine 数据转换
 *
 * @author wenbinglei
 * @date 2022-10-21 11:47:57
 */
@Mapper
public interface MachineConverter extends Convert<Machine, MachineDTO> {
    MachineConverter INSTANCE = Mappers.getMapper(MachineConverter.class);
}
