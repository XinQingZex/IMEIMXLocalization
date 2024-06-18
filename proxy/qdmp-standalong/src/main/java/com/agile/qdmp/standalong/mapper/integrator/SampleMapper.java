package com.agile.qdmp.standalong.mapper.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.SampleCount;
import com.agile.qdmp.standalong.model.dto.integrator.query.SampleQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Sample;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * SAMPLE
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:00
 */
public interface SampleMapper extends BaseMapper<Sample> {

    /**
     * 查询sample数量
     * @return
     */
    @Select("select part_guid,job_guid,lot_guid,status,count(0) total from im_sample group by part_guid,job_guid,lot_guid,status")
    List<SampleCount> customCount();

    @Select("select job_guid,status,count(0) total from im_sample where deleted=0 and job_guid=#{jobGuid} group by job_guid,status")
    List<SampleCount> customCountByJobGuid(String jobGuid);

    List<JSONObject> listByQuery(@Param("query") SampleQueryDTO queryDTO);
    @Select("select s.* from Objects_PartInstances s where s.ID=#{sampleId}")
    JSONObject getBySampleId(String sampleId);
    @Select({
    "<script>",
        "SELECT s.PartInstanceJobID, s.PartInstanceLotID, COUNT ( s.ID ) AS total FROM Objects_Parts p " +
            " LEFT JOIN Objects_WorkOrderLines j ON j.JobPartID = p.ID AND j.IsDeleted = 0 " +
            " LEFT JOIN Objects_PartInstances s ON s.PartInstanceJobID= j.ID AND s.IsDeleted = 0 " +
            "WHERE" +
            " p.IsDeleted= 0 AND p.PartIsDeleted=0" +
        "<if test='query.partName!= null and query.partName !=\"\" '> AND p.PartName=#{ query.partName } </if>" +
        "<if test='query.partNumber!= null and query.partNumber !=\"\" '> AND p.PartNumber=#{ query.partNumber } </if>" +
        "<if test='query.partRevision!= null and query.partRevision !=\"\" '> AND p.PartRevisionLevel=#{ query.partRevision } </if>" +
        "GROUP BY s.PartInstanceJobID, s.PartInstanceLotID" +
//        "GROUP BY s.PartInstanceJobID, s.PartInstanceLotID HAVING COUNT ( s.ID ) > 0" +
    "</script>"
    })
    List<JSONObject> customCountByPart(@Param("query") SampleQueryDTO queryDTO);
}
