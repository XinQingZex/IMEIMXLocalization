package com.agile.qdmp.standalong.mapper.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.query.JobQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Job;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * JOB
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:43
 */
public interface JobMapper extends BaseMapper<Job> {

    @Update("update im_job j set (part_name,part_number,part_revision) = (select part_name,part_number,part_revision_level from im_part p where p.guid=j.part_guid) where j.part_name=''")
    int updatePartData();

    List<JSONObject> listByQuery(@Param("query") JobQueryDTO queryDTO);

    List<JSONObject> listAll();
    @Select("select j.* from Objects_WorkOrderLines j where j.ID=#{jobId}")
    JSONObject getByJobId(String jobId);
}
