package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.GageMapper;
import com.agile.qdmp.standalong.model.dto.integrator.query.GageQueryDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.LotQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Gage;
import com.agile.qdmp.standalong.service.integrator.IGageService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GAGE 服务类
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:55
 */
@Service
public class GageServiceImpl extends ServiceImpl<GageMapper, Gage> implements IGageService {

    @Override
    public List<Gage> queryDistinctRow(Long serverId) {
        return baseMapper.distinctRow(serverId);
    }

    @Override
    public Boolean createGageMulti(List<Gage> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Map<String, Gage> dataList = new HashMap<>(10);
        for(Gage dto : list) {
            dataList.put(dto.getFlag(), dto);
        }
        List<Gage> exists = lambdaQuery().in(Gage::getFlag, dataList.keySet()).select(Gage::getFlag).list();
        if(exists.size() == dataList.size()) {
            return true;
        }
        for(Gage company : exists) {
            dataList.remove(company.getFlag());
        }

        Map<String, Gage> guidList = new HashMap<>(10);
        for(Gage data : dataList.values()) {
            guidList.put(data.getGuid(), data);
        }

        // 不存在的
        List<Gage> checkGuidList = lambdaQuery().in(Gage::getGuid, guidList.keySet()).select(Gage::getGuid, Gage::getId).list();
        if(checkGuidList != null && checkGuidList.size() > 0) {
            List<Gage> updates = new ArrayList<>();
            for(Gage gage : checkGuidList) {
                Gage update = guidList.get(gage.getGuid());
                update.setId(gage.getId());
                updates.add(update);
                guidList.remove(gage.getGuid());
            }
            if(updates.size() > 0) {
                updateBatchById(updates);
            }
        }
        if(guidList.size() > 0) {
            saveBatch(guidList.values());
        }
        return true;
    }

    @Override
    public List<JSONObject> listByQuery(GageQueryDTO queryDTO, Integer cursor, Integer limit) {
        return baseMapper.listByQuery(queryDTO, cursor, limit);
    }
}
