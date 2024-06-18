package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.ContactDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Contact;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Contacts 数据转换
 *
 * @author hyzh code generator
 * @date 2022-12-02 11:15:44
 */
@Mapper
public interface ContactConverter extends Convert<Contact, ContactDTO> {
    ContactConverter INSTANCE = Mappers.getMapper(ContactConverter.class);
}
