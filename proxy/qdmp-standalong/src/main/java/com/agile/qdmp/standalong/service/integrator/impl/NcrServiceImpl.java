package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.NcrMapper;
import com.agile.qdmp.standalong.model.dto.integrator.query.NcrQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Ncr;
import com.agile.qdmp.standalong.service.integrator.INcrService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NCR 服务类
 *
 * @author hyzh code generator
 * @date 2022-11-28 14:18:46
 */
@Service
public class NcrServiceImpl extends ServiceImpl<NcrMapper, Ncr> implements INcrService {

    @Override
    public Boolean createNcrMulti(List<Ncr> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Map<String, Ncr> dataList = new HashMap<>(10);
        for(Ncr dto : list) {
            dataList.put(dto.getFlag(), dto);
        }
        List<Ncr> exists = lambdaQuery().in(Ncr::getFlag, dataList.keySet()).select(Ncr::getFlag).list();
        if(exists.size() == dataList.size()) {
            return true;
        }
        for(Ncr company : exists) {
            dataList.remove(company.getFlag());
        }

        Map<String, Ncr> guidList = new HashMap<>(10);
        for(Ncr data : dataList.values()) {
            guidList.put(data.getGuid(), data);
        }

        // 不存在的
        List<Ncr> checkGuidList = lambdaQuery().in(Ncr::getGuid, guidList.keySet()).select(Ncr::getGuid, Ncr::getId).list();
        if(checkGuidList != null && checkGuidList.size() > 0) {
            List<Ncr> updates = new ArrayList<>();
            for(Ncr ncr : checkGuidList) {
                Ncr update = guidList.get(ncr.getGuid());
                update.setId(ncr.getId());
                updates.add(update);
                guidList.remove(ncr.getGuid());
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
    public List<JSONObject> listByQuery(NcrQueryDTO queryDTO) {
        return baseMapper.listByQuery(queryDTO);
    }
}
