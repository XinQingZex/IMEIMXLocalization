package com.agile.qdmp.standalong.mapper.integrator;

import com.agile.qdmp.standalong.model.entity.integrator.NcrResult;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * NCR_RESULT
 *
 * @author hyzh code generator
 * @date 2022-12-18 14:33:57
 */
public interface NcrResultMapper extends BaseMapper<NcrResult> {

    @Select("SELECT " +
            " r.ID AS resultId,r.LinkReportID,r.LinkActualID,r.LinkDefCodeID,r.LinkDefectValue," +
            " dr.ActualPartInstanceID,dr.ActualDimID,dr.ActualCode," +
            " dc.DefCodeTypeID,dc.DefCodeNumber,dc.DefCodeComments" +
            " FROM " +
            " Objects_NCReportActualLinks r " +
            " LEFT JOIN Objects_Actuals dr ON dr.ID=r.LinkActualID" +
            " LEFT JOIN Objects_DefectCodes dc ON dc.ID=r.LinkDefCodeID" +
            " WHERE" +
            " LinkReportID = #{id}")
    List<JSONObject> listById(String id);
}
