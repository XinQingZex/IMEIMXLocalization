package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.GageCategoryMapper;
import com.agile.qdmp.standalong.model.dto.integrator.query.GageCategoryQueryDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.LotQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Gage;
import com.agile.qdmp.standalong.model.entity.integrator.GageCategory;
import com.agile.qdmp.standalong.service.integrator.IGageCategoryService;
import com.agile.qdmp.standalong.service.integrator.IGageService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * GAGE_CATEGORY 服务类
 *
 * @author wenbinglei
 * @date 2022-10-21 14:06:08
 */
@Service
public class GageCategoryServiceImpl extends ServiceImpl<GageCategoryMapper, GageCategory> implements IGageCategoryService {

    @Resource
    IGageService gageService;

    @Override
    public Boolean normalizeGageCategoryData(Long serverId) {
        List<Gage> gages = gageService.queryDistinctRow(serverId);
        for(Gage gage : gages) {
            if(StringUtils.isNotBlank(gage.getCategoryCode())) {
                List<GageCategory> gageCategories = lambdaQuery().eq(GageCategory::getCategoryCode, gage.getCategoryCode()).list();
                if(gageCategories == null || gageCategories.size() == 0) {
                    GageCategory gageCategory = new GageCategory();
                    gageCategory.setServerId(gage.getServerId());
                    gageCategory.setServerName(gage.getServerName());
                    gageCategory.setCompanyId(gage.getCompanyId());
                    gageCategory.setCompanyName(gage.getCompanyName());
                    gageCategory.setCategoryCode(gage.getCategoryCode());
                    gageCategory.setName(gage.getCategoryCode());
                    gageCategory.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                    save(gageCategory);
                }
            }
        }
        return true;
    }

    @Override
    public List<JSONObject> listByQuery(GageCategoryQueryDTO queryDTO) {
        return baseMapper.listByQuery(queryDTO);
    }
}
