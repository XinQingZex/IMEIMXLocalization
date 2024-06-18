package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.JobMapper;
import com.agile.qdmp.standalong.model.dto.integrator.query.JobQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Job;
import com.agile.qdmp.standalong.service.integrator.IJobService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * JOB 服务类
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:43
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {

    @Override
    public Boolean createJobMulti(List<Job> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Map<String, Job> dataList = new HashMap<>(10);
        for(Job dto : list) {
            dataList.put(dto.getFlag(), dto);
        }
        List<Job> exists = lambdaQuery().in(Job::getFlag, dataList.keySet()).select(Job::getFlag).list();
        if(exists.size() == dataList.size()) {
            return true;
        }
        for(Job job : exists) {
            dataList.remove(job.getFlag());
        }

        Map<String, Job> guidList = new HashMap<>(10);
        for(Job data : dataList.values()) {
            data.setHandleState(false);
            guidList.put(data.getGuid(), data);
        }

        // 不存在的
        List<Job> checkGuidList = lambdaQuery().in(Job::getGuid, guidList.keySet()).select(Job::getGuid, Job::getId).list();
        if(checkGuidList != null && checkGuidList.size() > 0) {
            List<Job> updates = new ArrayList<>();
            for(Job job : checkGuidList) {
                Job update = guidList.get(job.getGuid());
                update.setId(job.getId());
                updates.add(update);
                guidList.remove(job.getGuid());
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
    public int updatePartData() {
        return baseMapper.updatePartData();
    }

    @Override
    public List<JSONObject> listByQuery(JobQueryDTO queryDTO) {
        return baseMapper.listByQuery(queryDTO);
    }

    @Override
    public List<JSONObject> listAll() {
        return baseMapper.listAll();
    }

    @Override
    public JSONObject getByJobId(String jobId) {
        return baseMapper.getByJobId(jobId);
    }
}
