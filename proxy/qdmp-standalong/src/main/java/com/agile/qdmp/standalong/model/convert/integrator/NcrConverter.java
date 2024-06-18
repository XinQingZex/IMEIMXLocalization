package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.NcrDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Ncr;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * NCR 数据转换
 *
 * @author hyzh code generator
 * @date 2022-11-28 14:18:46
 */
@Mapper
public interface NcrConverter extends Convert<Ncr, NcrDTO> {
    NcrConverter INSTANCE = Mappers.getMapper(NcrConverter.class);
}
