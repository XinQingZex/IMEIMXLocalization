package com.agile.qdmp.standalong.mapper.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.FullLotDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.PartQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Part;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * PART
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:11
 */
public interface PartMapper extends BaseMapper<Part> {

    /**
     * 获取合并后零件列表
     * @param queryDTO
     * @return
     */
//    List<Part> listByQuery(@Param("query") PartQueryDTO queryDTO);
    List<JSONObject> listByQuery(@Param("query") PartQueryDTO queryDTO);
    @Select("select p.* from Objects_Parts p where p.ID=#{id}")
    JSONObject getById(String id);
    /**
     * 根据InspCenterGuid获取
     * @param queryDTO
     * @return
     */
    @Select({
            "<script>",
            "select p.guid as part_guid, p.part_name,p.part_number,p.part_revision_level,j.guid as job_guid,j.number as job_number,l.guid as lot_guid,l.number as lot_number from im_part p left join im_job j on j.part_name=p.part_name and j.part_number=p.part_number and j.part_revision=p.part_revision_level and j.total_samples>0 left join im_lot l on j.guid=l.job_guid where p.guid in (" +
            " select  distinct d.part_guid  from im_dimension d where insp_center_guid =#{query.inspCenterGuid})" +
            "<if test='query.keyWord!= null and query.keyWord !=\"\" '>" +
            " and (p.part_name like CONCAT('%',#{query.keyWord}, '%') or j.number like CONCAT('%',#{query.keyWord}, '%') or l.number like CONCAT('%',#{query.keyWord}, '%'))" +
            "</if>",
            "</script>"
    })
    List<FullLotDTO> listByInspCenterGuidQuery(@Param("query") PartQueryDTO queryDTO);

    @Update("update im_part part set customer_name=(select name from im_company c where c.guid=part.customer_guid) where part.customer_name=''\n")
    int updateCustomerName();

    @Update("update im_part p set (job_total,lot_total,sample_total) = (select count(0),sum(total_lots),sum(total_samples) from im_job j where j.part_name=p.part_name and j.part_number=p.part_number and j.part_revision=p.part_revision_level )")
    int updateTotalNum();

    @Select({
            "<script>",
            "select p.ID,p.PartName,p.PartNumber,p.PartRevisionLevel,f.OrigExt,f.FullPath from Objects_Parts p LEFT JOIN Core_Files f on f.FolderID=p.PartPhoto WHERE p.isDeleted=0 and p.PartIsDeleted=0 " +
            "<if test=\"query.customerId != null and query.customerId != ''\">" +
            "     and p.PartCustomerID=#{query.customerId} " +
            "</if>" +
            "<if test=\"query.keyWord != null and query.keyWord != ''\">" +
            "     and (p.PartNumber like '%'+#{query.keyWord}+'%' or p.PartName like '%'+#{query.keyWord}+'%')" +
            "</if>" +
            "order by p.ID desc offset (#{cursor}-1) * #{limit} rows fetch next #{limit} rows only" +
            "</script>"
    })
    List<JSONObject> searchWithDrawingByQuery(@Param("query") PartQueryDTO queryDTO, @Param("cursor") Integer cursor, @Param("limit") Integer limit);
}
