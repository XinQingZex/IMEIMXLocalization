package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.query.JobQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Job;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * JOB
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:43
 */
public interface IJobService extends IService<Job> {

    /**
     * 批量新增JOB
     * @param list
     * @return
     */
    Boolean createJobMulti(List<Job> list);

    /**
     * 更新PartData
     * @return
     */
    int updatePartData();

    /**
     * 查询Job
     * @param queryDTO
     * @return
     */
    List<JSONObject> listByQuery(JobQueryDTO queryDTO);
    List<JSONObject> listAll();
    JSONObject getByJobId(String jobId);
}
