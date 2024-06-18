package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.ReceiverDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Receiver;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Receivers 数据转换
 *
 * @author hyzh code generator
 * @date 2022-11-22 18:41:33
 */
@Mapper
public interface ReceiverConverter extends Convert<Receiver, ReceiverDTO> {
    ReceiverConverter INSTANCE = Mappers.getMapper(ReceiverConverter.class);
}
