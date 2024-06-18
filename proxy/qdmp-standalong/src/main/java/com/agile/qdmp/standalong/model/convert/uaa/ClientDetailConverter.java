package com.agile.qdmp.standalong.model.convert.uaa;

import com.agile.tem.common.core.model.Convert;
import com.agile.qdmp.standalong.model.dto.uaa.ClientDetailDTO;
import com.agile.qdmp.standalong.model.entity.uaa.ClientDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 客户端信息 数据转换
 *
 * @author wenbinglei
 * @date 2021-02-01 15:04:38
 */
@Mapper
public interface ClientDetailConverter extends Convert<ClientDetail, ClientDetailDTO> {
    ClientDetailConverter INSTANCE = Mappers.getMapper(ClientDetailConverter.class);
}
