package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.InspCenterMapper;
import com.agile.qdmp.standalong.model.entity.integrator.InspCenter;
import com.agile.qdmp.standalong.service.integrator.IInspCenterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * InspCenters 服务类
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:50
 */
@Service
public class InspCenterServiceImpl extends ServiceImpl<InspCenterMapper, InspCenter> implements IInspCenterService {

    @Override
    public Boolean createInspCenterMulti(List<InspCenter> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Map<String, InspCenter> dataList = new HashMap<>(10);
        for(InspCenter dto : list) {
            dataList.put(dto.getFlag(), dto);
        }
        List<InspCenter> exists = lambdaQuery().in(InspCenter::getFlag, dataList.keySet()).select(InspCenter::getFlag).list();
        if(exists.size() == dataList.size()) {
            return true;
        }
        for(InspCenter inspCenter : exists) {
            dataList.remove(inspCenter.getFlag());
        }

        Map<String, InspCenter> guidList = new HashMap<>(10);
        for(InspCenter data : dataList.values()) {
            guidList.put(data.getGuid(), data);
        }

        // 不存在的
        List<InspCenter> checkGuidList = lambdaQuery().in(InspCenter::getGuid, guidList.keySet()).select(InspCenter::getGuid, InspCenter::getId).list();
        if(checkGuidList != null && checkGuidList.size() > 0) {
            List<InspCenter> updates = new ArrayList<>();
            for(InspCenter inspCenter : checkGuidList) {
                InspCenter update = guidList.get(inspCenter.getGuid());
                update.setId(inspCenter.getId());
                updates.add(update);
                guidList.remove(inspCenter.getGuid());
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
