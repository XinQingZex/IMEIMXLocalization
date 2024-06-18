package com.agile.qdmp.standalong.mapper.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.ResultCount;
import com.agile.qdmp.standalong.model.dto.integrator.query.ResultQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Result;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * RESULT
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:06
 */
public interface ResultMapper extends BaseMapper<Result> {

    /**
     * 根据Sample统计Result各个类型结果
     * @param sampleGuid
     * @return
     */
    @Select("select status,count(0) as total from im_result where sample_guid=#{sampleGuid} group by status")
    List<ResultCount> customCountBySampleGuid(String sampleGuid);

    /**
     * 根据Job统计Sample结果
     * @param queryDTO
     * @return
     */
//    @Select("select sample_guid,group_concat(DISTINCT status) agg from im_result where job_guid=#{jobGuid} group by sample_guid ")
//    @Select("select s.ID,r.ActualResult status,count(0) as total from Objects_Actuals r LEFT JOIN Objects_PartInstances s ON r.ActualPartInstanceID=s.ID AND s.IsDeleted =0 where r.IsDeleted=0 and s.PartInstanceJobID=#{jobId} GROUP BY s.ID,r.ActualResult order by s.ID")
    @Select({
        "<script>",
        "select s.ID,ISNULL(r.ActualResult,0) status,count(r.ActualResult) as total from Objects_PartInstances s LEFT JOIN Objects_Actuals r ON r.ActualPartInstanceID=s.ID AND r.IsDeleted =0 where s.IsDeleted=0 \n" +
            "<if test='query.jobId!= null and query.jobId !=\"\" '> AND s.PartInstanceJobID=#{ query.jobId } </if>" +
            "<if test='query.lotId!= null and query.lotId !=\"\" '> AND s.PartInstanceLotID=#{ query.lotId } </if>" +
            "<if test='query.sampleId!= null and query.sampleId !=\"\" '> AND s.PartInstanceID=#{ query.sampleId } </if>" +
        " GROUP BY s.ID,r.ActualResult" +
        "</script>"
    })
    List<JSONObject> customCount(@Param("query") ResultQueryDTO queryDTO);

    @Select({
        "<script>",
        "select s.PartInstanceLotID,s.ID, ISNULL(r.ActualResult,0) status,count(r.ActualResult) as total from Objects_PartInstances s LEFT JOIN Objects_Actuals r ON r.ActualPartInstanceID=s.ID AND r.IsDeleted =0 where s.IsDeleted=0 " +
            "<if test='query.jobId!= null and query.jobId !=\"\" '> AND s.PartInstanceJobID=#{ query.jobId } </if>" +
        " GROUP BY s.PartInstanceLotID,s.ID, r.ActualResult" +
        "</script>"
    })
    List<JSONObject> customCountByJob(@Param("query") ResultQueryDTO queryDTO);

//    /**
//     * 根据LotGuid统计Result结果
//     * @param lotGuid
//     * @return
//     */
//    @Select("SELECT status,count(0) as total FROM IM_RESULT where sample_guid in (select guid from im_sample where lot_guid=#{lotGuid}) group by status")
//    List<ResultCount> customCountByLotGuid(String lotGuid);
}
