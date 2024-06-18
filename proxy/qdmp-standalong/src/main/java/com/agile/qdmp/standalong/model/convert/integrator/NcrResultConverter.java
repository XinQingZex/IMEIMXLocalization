package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.NcrResultDTO;
import com.agile.qdmp.standalong.model.entity.integrator.NcrResult;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * NCR_RESULT 数据转换
 *
 * @author hyzh code generator
 * @date 2022-12-18 14:33:57
 */
@Mapper
public interface NcrResultConverter extends Convert<NcrResult, NcrResultDTO> {
    NcrResultConverter INSTANCE = Mappers.getMapper(NcrResultConverter.class);
}
