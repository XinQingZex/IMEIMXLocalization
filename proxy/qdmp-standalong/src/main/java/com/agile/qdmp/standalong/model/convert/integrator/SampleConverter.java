package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.SampleDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Sample;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * SAMPLE 数据转换
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:00
 */
@Mapper
public interface SampleConverter extends Convert<Sample, SampleDTO> {
    SampleConverter INSTANCE = Mappers.getMapper(SampleConverter.class);
}
