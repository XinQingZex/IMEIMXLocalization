package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.NcrResultMapper;
import com.agile.qdmp.standalong.model.entity.integrator.NcrResult;
import com.agile.qdmp.standalong.service.integrator.INcrResultService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NCR_RESULT 服务类
 *
 * @author hyzh code generator
 * @date 2022-12-18 14:33:57
 */
@Service
public class NcrResultServiceImpl extends ServiceImpl<NcrResultMapper, NcrResult> implements INcrResultService {

    @Override
    public Boolean createNcrResultMulti(List<NcrResult> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Map<String, NcrResult> dataList = new HashMap<>(10);
        for(NcrResult dto : list) {
            dataList.put(dto.getFlag(), dto);
        }
        List<NcrResult> exists = lambdaQuery().in(NcrResult::getFlag, dataList.keySet()).select(NcrResult::getFlag).list();
        if(exists.size() == dataList.size()) {
            return true;
        }
        for(NcrResult ncrResult : exists) {
            dataList.remove(ncrResult.getFlag());
        }

        Map<String, NcrResult> guidList = new HashMap<>(10);
        for(NcrResult data : dataList.values()) {
            guidList.put(data.getResultGuid(), data);
        }

        // 不存在的
        List<NcrResult> checkGuidList = lambdaQuery().in(NcrResult::getResultGuid, guidList.keySet()).select(NcrResult::getResultGuid, NcrResult::getId).list();
        if(checkGuidList != null && checkGuidList.size() > 0) {
            List<NcrResult> updates = new ArrayList<>();
            for(NcrResult ncr : checkGuidList) {
                NcrResult update = guidList.get(ncr.getResultGuid());
                update.setId(ncr.getId());
                updates.add(update);
                guidList.remove(ncr.getResultGuid());
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
    public List<JSONObject> listById(String id) {
        return baseMapper.listById(id);
    }
}
