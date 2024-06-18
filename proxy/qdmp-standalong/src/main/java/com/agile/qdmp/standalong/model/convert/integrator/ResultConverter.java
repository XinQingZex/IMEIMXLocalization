package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.ResultDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Result;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * RESULT 数据转换
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:06
 */
@Mapper
public interface ResultConverter extends Convert<Result, ResultDTO> {
    ResultConverter INSTANCE = Mappers.getMapper(ResultConverter.class);
}
