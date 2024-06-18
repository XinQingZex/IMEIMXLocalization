package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.GageCategoryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.GageCategory;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * GAGE_CATEGORY 数据转换
 *
 * @author wenbinglei
 * @date 2022-10-21 14:06:08
 */
@Mapper
public interface GageCategoryConverter extends Convert<GageCategory, GageCategoryDTO> {
    GageCategoryConverter INSTANCE = Mappers.getMapper(GageCategoryConverter.class);
}
