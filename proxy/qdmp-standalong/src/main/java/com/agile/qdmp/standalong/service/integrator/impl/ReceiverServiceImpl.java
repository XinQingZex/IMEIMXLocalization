package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.ReceiverMapper;
import com.agile.qdmp.standalong.model.entity.integrator.Receiver;
import com.agile.qdmp.standalong.service.integrator.IReceiverService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Receivers 服务类
 *
 * @author hyzh code generator
 * @date 2022-11-22 18:41:33
 */
@Service
public class ReceiverServiceImpl extends ServiceImpl<ReceiverMapper, Receiver> implements IReceiverService {

    @Override
    public Boolean createReceiverMulti(List<Receiver> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Map<String, Receiver> dataList = new HashMap<>(10);
        for(Receiver dto : list) {
            dataList.put(dto.getFlag(), dto);
        }
        List<Receiver> exists = lambdaQuery().in(Receiver::getFlag, dataList.keySet()).select(Receiver::getFlag).list();
        if(exists.size() == dataList.size()) {
            return true;
        }
        for(Receiver company : exists) {
            dataList.remove(company.getFlag());
        }

        Map<String, Receiver> guidList = new HashMap<>(10);
        for(Receiver data : dataList.values()) {
            guidList.put(data.getGuid(), data);
        }

        // 不存在的
        List<Receiver> checkGuidList = lambdaQuery().in(Receiver::getGuid, guidList.keySet()).select(Receiver::getGuid, Receiver::getId).list();
        if(checkGuidList != null && checkGuidList.size() > 0) {
            List<Receiver> updates = new ArrayList<>();
            for(Receiver receiver : checkGuidList) {
                Receiver update = guidList.get(receiver.getGuid());
                update.setId(receiver.getId());
                updates.add(update);
                guidList.remove(receiver.getGuid());
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
