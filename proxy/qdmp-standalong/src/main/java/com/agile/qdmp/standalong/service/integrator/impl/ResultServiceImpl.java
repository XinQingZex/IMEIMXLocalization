package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.ResultMapper;
import com.agile.qdmp.standalong.model.dto.integrator.ResultCount;
import com.agile.qdmp.standalong.model.dto.integrator.query.ResultQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Result;
import com.agile.qdmp.standalong.service.integrator.IResultService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * RESULT 服务类
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:06
 */
@Service
public class ResultServiceImpl extends ServiceImpl<ResultMapper, Result> implements IResultService {

    @Override
    public Boolean createResultMulti(List<Result> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Map<String, Result> dataList = new HashMap<>(10);
        for(Result dto : list) {
            dataList.put(dto.getFlag(), dto);
        }
        // 没有变化的
        List<Result> exists = lambdaQuery().in(Result::getFlag, dataList.keySet()).select(Result::getFlag, Result::getGuid).list();
        if(exists.size() == dataList.size()) {
            return true;
        }
        for(Result result : exists) {
            dataList.remove(result.getFlag());
        }

        Map<String, Result> guidList = new HashMap<>(10);
        for(Result data : dataList.values()) {
            guidList.put(data.getGuid(), data);
        }

        // 不存在的
        List<Result> checkGuidList = lambdaQuery().in(Result::getGuid, guidList.keySet()).select(Result::getGuid, Result::getId).list();
        if(checkGuidList != null && checkGuidList.size() > 0) {
            List<Result> updates = new ArrayList<>();
            for(Result result : checkGuidList) {
                Result update = guidList.get(result.getGuid());
                update.setId(result.getId());
                update.setData(null);
                update.setStatus(null);
                update.setStatusText(null);
                updates.add(update);
                guidList.remove(result.getGuid());
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
    public Boolean updateResultMulti(List<Result> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Map<String, Result> dataList = new HashMap<>(10);
        for(Result dto : list) {
            dataList.put(dto.getFailedFlag(), dto);
        }
        // 没有变化的
        List<Result> exists = lambdaQuery().in(Result::getFailedFlag, dataList.keySet()).select(Result::getFailedFlag, Result::getGuid).list();
        if(exists.size() == list.size()) {
            return true;
        }
        for(Result result : exists) {
            dataList.remove(result.getFailedFlag());
        }

        Map<String, Result> guidList = new HashMap<>(10);
        for(Result data : dataList.values()) {
            guidList.put(data.getGuid(), data);
        }

        // 不存在的
        List<Result> checkGuidList = lambdaQuery().in(Result::getGuid, guidList.keySet()).select(Result::getGuid, Result::getId).list();
        if(checkGuidList != null && checkGuidList.size() > 0) {
            List<Result> updates = new ArrayList<>();
            for(Result result : checkGuidList) {
                Result update = guidList.get(result.getGuid());
                update.setId(result.getId());
                updates.add(update);
                guidList.remove(result.getGuid());
            }
            if(updates.size() > 0) {
                updateBatchById(updates);
            }
        }
//        if(guidList.size() > 0) {
//            saveBatch(guidList.values());
//        }
        return true;
    }

    @Override
    public List<ResultCount> customCountBySampleGuid(String sampleGuid) {
        return baseMapper.customCountBySampleGuid(sampleGuid);
    }

    @Override
    public List<JSONObject> customCount(ResultQueryDTO queryDTO) {
        return baseMapper.customCount(queryDTO);
    }

    @Override
    public List<JSONObject> customCountByJob(ResultQueryDTO queryDTO) {
        return baseMapper.customCountByJob(queryDTO);
    }

//    @Override
//    public List<ResultCount> customCountByLotGuid(String lotGuid) {
//        return baseMapper.customCountByLotGuid(lotGuid);
//    }
}
