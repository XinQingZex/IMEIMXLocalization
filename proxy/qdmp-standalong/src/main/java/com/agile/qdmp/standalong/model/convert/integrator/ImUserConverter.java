package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.ImUserDTO;
import com.agile.qdmp.standalong.model.entity.integrator.ImUser;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 质量管理大师用户信息表 数据转换
 *
 * @author hyzh code generator
 * @date 2022-09-19 09:56:46
 */
@Mapper
public interface ImUserConverter extends Convert<ImUser, ImUserDTO> {
    ImUserConverter INSTANCE = Mappers.getMapper(ImUserConverter.class);
}
