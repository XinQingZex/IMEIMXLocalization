package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.LotDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Lot;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * LOT 数据转换
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:16
 */
@Mapper
public interface LotConverter extends Convert<Lot, LotDTO> {
    LotConverter INSTANCE = Mappers.getMapper(LotConverter.class);
}
