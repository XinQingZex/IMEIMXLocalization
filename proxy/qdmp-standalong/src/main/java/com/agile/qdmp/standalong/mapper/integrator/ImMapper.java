package com.agile.qdmp.standalong.mapper.integrator;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * Im Mapper
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:16
 */
public interface ImMapper {
    @Select("select p.ID,p.ProjectName,p.GlobalID from Objects_Projects p where p.DELETED=0 AND p.IsDeleted=0")
    List<JSONObject> listProjectsByQuery(@Param("query") HashMap<String, String> queryDTO);

    @Select("select p.* from Objects_Operations p where p.IsDeleted=0 AND p.OperationPartID=#{query.partId}")
    List<JSONObject> listOperationsByQuery(@Param("query") HashMap<String, String> queryDTO);

    @Select("SELECT DISTINCT " +
            " p.ID as PartId,p.PartName,p.PartNumber,p.PartRevisionLevel,dw.DrawingRevisionLevel,j.ID as JobId,j.JobNumber" +
            " FROM " +
            "Objects_Parts p " +
            "LEFT JOIN Objects_Drawings dw ON dw.DrawingPartID= p.ID and dw.IsDeleted=0 " +
            "LEFT JOIN Objects_WorkOrderLines j ON j.JobPartID= p.ID and j.IsDeleted=0 " +
            "WHERE " +
            " p.isDeleted= 0 " +
            " and (j.JobNumber like '%'+#{query.keyWord}+'%')")
//            " and (p.PartNumber like '%'+#{query.keyWord}+'%' or p.PartName like '%'+#{query.keyWord}+'%' or j.JobNumber like '%'+#{query.keyWord}+'%')")
    List<JSONObject> search(@Param("query") HashMap<String, String> queryDTO);

    @Select("select a.ID,a.AQLHeaderNumber,a.AQLHeaderMode,a.AQLHeaderStatus,a.GlobalID from Dictionary_AQLHeaders a WHERE a.IsDeleted=0")
    List<JSONObject> listAQLHeaderByQuery(HashMap<String, String> queryDTO);
}
