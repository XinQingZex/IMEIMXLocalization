package com.agile.qdmp.standalong.mapper.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.query.GageCategoryQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.GageCategory;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * GAGE_CATEGORY
 *
 * @author wenbinglei
 * @date 2022-10-21 14:06:08
 */
public interface GageCategoryMapper extends BaseMapper<GageCategory> {

    List<JSONObject> listByQuery(@Param("query") GageCategoryQueryDTO queryDTO);
}
