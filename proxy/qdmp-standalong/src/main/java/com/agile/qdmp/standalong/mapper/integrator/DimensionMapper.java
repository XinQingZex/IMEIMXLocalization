package com.agile.qdmp.standalong.mapper.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.DimCount;
import com.agile.qdmp.standalong.model.dto.integrator.DimensionDTO;
import com.agile.qdmp.standalong.model.entity.integrator.CharacterDesignator;
import com.agile.qdmp.standalong.model.entity.integrator.Dimension;
import com.agile.qdmp.standalong.model.entity.integrator.DimensionType;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * DIMENSION
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:56
 */
public interface DimensionMapper extends BaseMapper<Dimension> {

    @Select("select DISTINCT designator_guid as guid,designator as name from im_dimension")
    List<CharacterDesignator> listCharacterDesignator();
    @Select("select DISTINCT type as guid,type_text as name from im_dimension")
    List<DimensionType> listDimensionTypes();

    @Select("select operation_guid,count(0) as total from im_dimension where part_guid=#{partGuid} group by operation_guid")
    List<DimCount> customCountByPartGuid(String partGuid);

    /**
     * 根据SampleGuid和OperationGuid查询尺寸数量
     * @return
     */
//    @Select("SELECT d.*,r.res_no as result_res_no FROM im_result r left join im_dimension d ON d.guid = r.dim_guid WHERE r.sample_guid = #{sampleGuid} and d.operation_guid=#{operationGuid}")
    List<JSONObject> listBySampleAndOperation(@Param("sampleId")String sampleId, @Param("operationId")String operationId, @Param("procedureId")String procedureId);

    /**
     * 根据SampleGuid获取重复的Dim
     * @return
     */
    @Select({
        "<script>",
            "SELECT * FROM im_dimension WHERE operation_guid = #{operationGuid} AND deleted = 0 ",
            "AND dim_no IN (",
            " SELECT d.dim_no FROM im_result r LEFT JOIN im_dimension d ON d.guid = r.dim_guid WHERE",
            "  r.sample_guid IN ",
            "<foreach collection='guids' item='item' open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "  AND r.deleted = 0 AND d.deleted = 0 ",
            " GROUP BY d.dim_no HAVING count( 0 )> 1 ",
            ")",
        "</script>"
    })
    List<Dimension> listBySample(@Param("operationGuid") String operationGuid, @Param("guids") String[] guids);

    @Update("update im_dimension dim set part_guid=(select part_guid from im_drawing dw where dw.guid=dim.drawing_guid) where dim.part_guid=''")
    int updatePartGuidbyDrawing();
}
