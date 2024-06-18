package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.ImServerDTO;
import com.agile.qdmp.standalong.model.entity.integrator.ImServer;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 企业质量管理大师信息表 数据转换
 *
 * @author hyzh code generator
 * @date 2022-09-19 09:56:51
 */
@Mapper
public interface ImServerConverter extends Convert<ImServer, ImServerDTO> {
    ImServerConverter INSTANCE = Mappers.getMapper(ImServerConverter.class);
}
