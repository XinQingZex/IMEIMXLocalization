package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.ImApiRecordDTO;
import com.agile.qdmp.standalong.model.entity.integrator.ImApiRecord;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * IM_API_RECORD 数据转换
 *
 * @author hyzh code generator
 * @date 2022-09-19 09:56:55
 */
@Mapper
public interface ImApiRecordConverter extends Convert<ImApiRecord, ImApiRecordDTO> {
    ImApiRecordConverter INSTANCE = Mappers.getMapper(ImApiRecordConverter.class);
}
