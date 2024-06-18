package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.DimensionMapper;
import com.agile.qdmp.standalong.model.dto.integrator.DimCount;
import com.agile.qdmp.standalong.model.dto.integrator.DimensionDTO;
import com.agile.qdmp.standalong.model.entity.integrator.CharacterDesignator;
import com.agile.qdmp.standalong.model.entity.integrator.Dimension;
import com.agile.qdmp.standalong.model.entity.integrator.DimensionType;
import com.agile.qdmp.standalong.service.integrator.IDimensionService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * DIMENSION 服务类
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:56
 */
@Service
public class DimensionServiceImpl extends ServiceImpl<DimensionMapper, Dimension> implements IDimensionService {

    @Override
    public Boolean createDimensionMulti(List<Dimension> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Map<String, Dimension> dataList = new HashMap<>(10);
        for(Dimension dto : list) {
            dataList.put(dto.getFlag(), dto);
        }
        List<Dimension> exists = lambdaQuery().in(Dimension::getFlag, dataList.keySet()).select(Dimension::getFlag, Dimension::getGuid).list();
        if(exists.size() == dataList.size()) {
            return true;
        }
        for(Dimension dimension : exists) {
            dataList.remove(dimension.getFlag());
        }
        Set<String> guids = new HashSet<>();
        for(Dimension dimension : dataList.values()) {
            guids.add(dimension.getGuid());
        }

        Map<String, Dimension> guidList = new HashMap<>(10);
        for(Dimension data : dataList.values()) {
            guidList.put(data.getGuid(), data);
        }
        if(guidList.size() == 0) {
            return true;
        }
        // 不存在的
        List<Dimension> checkGuidList = lambdaQuery().in(Dimension::getGuid, guidList.keySet()).select(Dimension::getGuid, Dimension::getId).list();
        if(checkGuidList != null && checkGuidList.size() > 0) {
            List<Dimension> updates = new ArrayList<>();
            for(Dimension part : checkGuidList) {
                Dimension update = guidList.get(part.getGuid());
                update.setId(part.getId());
                updates.add(update);
                guidList.remove(part.getGuid());
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
    public List<CharacterDesignator> listCharacterDesignator() {
        return baseMapper.listCharacterDesignator();
    }

    @Override
    public List<DimensionType> listDimensionTypes() {
        return baseMapper.listDimensionTypes();
    }

    @Override
    public List<DimCount> customCountByPartGuid(String partGuid) {
        return baseMapper.customCountByPartGuid(partGuid);
    }

    @Override
    public List<JSONObject> listBySampleAndOperation(String sampleId, String operationId, String procedureId) {
        return baseMapper.listBySampleAndOperation(sampleId, operationId, procedureId);
    }

    @Override
    public List<Dimension> listBySample(String operationGuid, String[] guids) {
        return baseMapper.listBySample(operationGuid, guids);
    }

    @Override
    public int updatePartGuidbyDrawing() {
        return baseMapper.updatePartGuidbyDrawing();
    }
}
