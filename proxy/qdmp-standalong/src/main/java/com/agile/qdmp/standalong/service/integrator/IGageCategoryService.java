package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.query.GageCategoryQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.GageCategory;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * GAGE_CATEGORY
 *
 * @author wenbinglei
 * @date 2022-10-21 14:06:08
 */
public interface IGageCategoryService extends IService<GageCategory> {

    /**
     * 根据gage结果整理category
     * @param serverId
     * @return
     */
    Boolean normalizeGageCategoryData(Long serverId);

    List<JSONObject> listByQuery(GageCategoryQueryDTO queryDTO);
}
