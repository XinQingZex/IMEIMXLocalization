package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.CompanyDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Company;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * COMPANY 数据转换
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:59
 */
@Mapper
public interface CompanyConverter extends Convert<Company, CompanyDTO> {
    CompanyConverter INSTANCE = Mappers.getMapper(CompanyConverter.class);
}
