package com.agile.qdmp.standalong.mapper.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.query.LotQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Lot;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * LOT
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:16
 */
public interface LotMapper extends BaseMapper<Lot> {

    List<JSONObject> listByQuery(@Param("query") LotQueryDTO queryDTO);

    @Select("select l.* from Objects_Lots l where l.ID=#{lotId}")
    JSONObject getByLotId(String lotId);

    @Select("select top 1 l.ID from Objects_Lots l LEFT JOIN Objects_WorkOrderLines j ON l.LotJobID=j.ID WHERE j.GlobalID=#{guid}")
    JSONObject getFirstLot(@Param("guid") String guid);

    @Update("update Objects_Lots set LotSize=#{size},LotQualityStage=#{qualityStage} WHERE ID=#{id}")
    int modifyFirstLot(@Param("id")Integer id, @Param("size")Integer size, @Param("qualityStage")Integer qualityStage);

    List<JSONObject> listByContactId(@Param("query") LotQueryDTO queryDTO);
}
