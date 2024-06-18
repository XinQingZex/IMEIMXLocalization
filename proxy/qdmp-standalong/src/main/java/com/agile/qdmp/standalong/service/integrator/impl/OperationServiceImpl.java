package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.OperationMapper;
import com.agile.qdmp.standalong.model.dto.integrator.query.OperationQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Operation;
import com.agile.qdmp.standalong.service.integrator.IOperationService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Operation 服务类
 *
 * @author wenbinglei
 * @date 2022-10-21 11:47:53
 */
@Service
public class OperationServiceImpl extends ServiceImpl<OperationMapper, Operation> implements IOperationService {


    @Override
    public Boolean createOperationMulti(List<Operation> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Map<String, Operation> dataList = new HashMap<>(10);
        for(Operation dto : list) {
            dataList.put(dto.getFlag(), dto);
        }
        List<Operation> exists = lambdaQuery().in(Operation::getFlag, dataList.keySet()).select(Operation::getFlag).list();
        if(exists.size() == dataList.size()) {
            return true;
        }
        for(Operation sample : exists) {
            dataList.remove(sample.getFlag());
        }
        Map<String, Operation> guidList = new HashMap<>(10);
        for(Operation data : dataList.values()) {
            guidList.put(data.getGuid(), data);
        }

        // 不存在的
        List<Operation> checkGuidList = lambdaQuery().in(Operation::getGuid, guidList.keySet()).select(Operation::getGuid, Operation::getId).list();
        if(checkGuidList != null && checkGuidList.size() > 0) {
            List<Operation> updates = new ArrayList<>();
            for(Operation operation : checkGuidList) {
                Operation update = guidList.get(operation.getGuid());
                update.setId(operation.getId());
                updates.add(update);
                guidList.remove(operation.getGuid());
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
    public List<JSONObject> listByQuery(OperationQueryDTO queryDTO) {
        return baseMapper.listByQuery(queryDTO);
    }
}
