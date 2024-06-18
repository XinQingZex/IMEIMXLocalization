package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.ProcedureMapper;
import com.agile.qdmp.standalong.model.entity.integrator.Procedure;
import com.agile.qdmp.standalong.service.integrator.IProcedureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Procedure 服务类
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:46
 */
@Service
public class ProcedureServiceImpl extends ServiceImpl<ProcedureMapper, Procedure> implements IProcedureService {

    @Override
    public Boolean createProcedureMulti(List<Procedure> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Map<String, Procedure> dataList = new HashMap<>(10);
        for(Procedure dto : list) {
            dataList.put(dto.getFlag(), dto);
        }
        List<Procedure> exists = lambdaQuery().in(Procedure::getFlag, dataList.keySet()).select(Procedure::getFlag).list();
        if(exists.size() == dataList.size()) {
            return true;
        }
        for(Procedure company : exists) {
            dataList.remove(company.getFlag());
        }

        Map<String, Procedure> guidList = new HashMap<>(10);
        for(Procedure data : dataList.values()) {
            guidList.put(data.getGuid(), data);
        }

        // 不存在的
        List<Procedure> checkGuidList = lambdaQuery().in(Procedure::getGuid, guidList.keySet()).select(Procedure::getGuid, Procedure::getId).list();
        if(checkGuidList != null && checkGuidList.size() > 0) {
            List<Procedure> updates = new ArrayList<>();
            for(Procedure procedure : checkGuidList) {
                Procedure update = guidList.get(procedure.getGuid());
                update.setId(procedure.getId());
                updates.add(update);
                guidList.remove(procedure.getGuid());
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
}
