package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.PartDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Part;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * PART 数据转换
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:11
 */
@Mapper
public interface PartConverter extends Convert<Part, PartDTO> {
    PartConverter INSTANCE = Mappers.getMapper(PartConverter.class);
}
