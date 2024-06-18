package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.LotMapper;
import com.agile.qdmp.standalong.model.dto.integrator.query.LotQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Lot;
import com.agile.qdmp.standalong.service.integrator.ILotService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * LOT 服务类
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:16
 */
@Service
public class LotServiceImpl extends ServiceImpl<LotMapper, Lot> implements ILotService {

    @Override
    public Boolean createLotMulti(List<Lot> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Map<String, Lot> dataList = new HashMap<>(10);
        for(Lot dto : list) {
            dataList.put(dto.getFlag(), dto);
        }
        List<Lot> exists = lambdaQuery().in(Lot::getFlag, dataList.keySet()).select(Lot::getFlag).list();
        if(exists.size() == dataList.size()) {
            return true;
        }
        for(Lot company : exists) {
            dataList.remove(company.getFlag());
        }

        Map<String, Lot> guidList = new HashMap<>(10);
        for(Lot data : dataList.values()) {
            data.setHandleState(false);
            guidList.put(data.getGuid(), data);
        }

        // 不存在的
        List<Lot> checkGuidList = lambdaQuery().in(Lot::getGuid, guidList.keySet()).select(Lot::getGuid, Lot::getId).list();
        if(checkGuidList != null && checkGuidList.size() > 0) {
            List<Lot> updates = new ArrayList<>();
            for(Lot lot : checkGuidList) {
                Lot update = guidList.get(lot.getGuid());
                update.setId(lot.getId());
                updates.add(update);
                guidList.remove(lot.getGuid());
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
    public List<JSONObject> listByQuery(LotQueryDTO queryDTO) {
        return baseMapper.listByQuery(queryDTO);
    }

    @Override
    public JSONObject getByLotId(String lotId) {
        return baseMapper.getByLotId(lotId);
    }

    @Override
    public JSONObject getFirstLot(String guid) {
        return baseMapper.getFirstLot(guid);
    }

    @Override
    public int modifyFirstLot(Integer id, Integer size, Integer qualityStage) {
        return baseMapper.modifyFirstLot(id, size, qualityStage);
    }

    @Override
    public List<JSONObject> listByContactId(LotQueryDTO queryDTO) {
        return baseMapper.listByContactId(queryDTO);
    }
}
