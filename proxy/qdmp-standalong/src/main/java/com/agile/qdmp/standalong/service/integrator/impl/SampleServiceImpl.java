package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.SampleMapper;
import com.agile.qdmp.standalong.model.dto.integrator.SampleCount;
import com.agile.qdmp.standalong.model.dto.integrator.query.SampleQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Sample;
import com.agile.qdmp.standalong.service.integrator.ISampleService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * SAMPLE 服务类
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:00
 */
@Service
public class SampleServiceImpl extends ServiceImpl<SampleMapper, Sample> implements ISampleService {

    @Override
    public Boolean createSampleMulti(List<Sample> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Map<String, Sample> dataList = new HashMap<>(10);
        for(Sample dto : list) {
            dataList.put(dto.getFlag(), dto);
        }
        List<Sample> exists = lambdaQuery().in(Sample::getFlag, dataList.keySet()).select(Sample::getFlag).list();
        if(exists.size() == dataList.size()) {
            return true;
        }
        for(Sample sample : exists) {
            dataList.remove(sample.getFlag());
        }

        Map<String, Sample> guidList = new HashMap<>(10);
        for(Sample data : dataList.values()) {
            data.setHandleState(false);
            guidList.put(data.getGuid(), data);
        }

        // 不存在的
        List<Sample> checkGuidList = lambdaQuery().in(Sample::getGuid, guidList.keySet()).select(Sample::getGuid, Sample::getId).list();
        if(checkGuidList != null && checkGuidList.size() > 0) {
            List<Sample> updates = new ArrayList<>();
            for(Sample sample : checkGuidList) {
                Sample update = guidList.get(sample.getGuid());
                update.setId(sample.getId());
                updates.add(update);
                guidList.remove(sample.getGuid());
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
    public List<SampleCount> customCount() {
        return baseMapper.customCount();
    }

    @Override
    public List<SampleCount> customCountByJobGuid(String jobGuid) {
        return baseMapper.customCountByJobGuid(jobGuid);
    }

    @Override
    public List<JSONObject> listByQuery(SampleQueryDTO queryDTO) {
        return baseMapper.listByQuery(queryDTO);
    }

    @Override
    public JSONObject getBySampleId(String sampleId) {
        return baseMapper.getBySampleId(sampleId);
    }

    @Override
    public List<JSONObject> customCountByPart(SampleQueryDTO queryDTO) {
        return baseMapper.customCountByPart(queryDTO);
    }
}
