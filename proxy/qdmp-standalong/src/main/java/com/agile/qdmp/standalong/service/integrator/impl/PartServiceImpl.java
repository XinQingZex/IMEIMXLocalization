package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.PartMapper;
import com.agile.qdmp.standalong.model.dto.integrator.FullLotDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.PartQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Part;
import com.agile.qdmp.standalong.service.integrator.IPartService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * PART 服务类
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:11
 */
@Service
public class PartServiceImpl extends ServiceImpl<PartMapper, Part> implements IPartService {

    @Override
    public List<JSONObject> listByQuery(PartQueryDTO queryDTO) {
        return baseMapper.listByQuery(queryDTO);
    }

    @Override
    public JSONObject getByPartId(String id) {
        return baseMapper.getById(id);
    }

    @Override
    public List<JSONObject> searchWithDrawingByQuery(PartQueryDTO queryDTO, Integer cursor, Integer limit) {
        return baseMapper.searchWithDrawingByQuery(queryDTO, cursor, limit);
    }
//    @Override
//    public List<Part> listByQuery(PartQueryDTO queryDTO) {
//        return baseMapper.listByQuery(queryDTO);
//    }

    @Override
    public Boolean createPartMulti(List<Part> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Map<String, Part> dataList = new HashMap<>(10);
        for(Part dto : list) {
            dataList.put(dto.getFlag(), dto);
        }
        List<Part> exists = lambdaQuery().in(Part::getFlag, dataList.keySet()).select(Part::getFlag).list();
        if(exists.size() == dataList.size()) {
            return true;
        }

        for(Part part : exists) {
            dataList.remove(part.getFlag());
        }
        Map<String, Part> guidList = new HashMap<>(10);
        for(Part part : dataList.values()) {
            part.setHandleState(false);
            guidList.put(part.getGuid(), part);
        }

        // 不存在的
        List<Part> checkGuidList = lambdaQuery().in(Part::getGuid, guidList.keySet()).select(Part::getGuid, Part::getId).list();
        if(checkGuidList != null && checkGuidList.size() > 0) {
            List<Part> updates = new ArrayList<>();
            for(Part part : checkGuidList) {
                Part update = guidList.get(part.getGuid());
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
    public List<FullLotDTO> listByInspCenterGuidQuery(PartQueryDTO queryDTO) {
        return baseMapper.listByInspCenterGuidQuery(queryDTO);
    }

    @Override
    public int updateCustomerName() {
        return baseMapper.updateCustomerName();
    }

    @Override
    public int updateTotalNum() {
        return baseMapper.updateTotalNum();
    }
}
