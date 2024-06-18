package com.agile.qdmp.standalong.mapper.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.query.NcrQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Ncr;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * NCR
 *
 * @author hyzh code generator
 * @date 2022-11-28 14:18:46
 */
public interface NcrMapper extends BaseMapper<Ncr> {

    @Select({
    "<script>",
    "select n.* from Objects_NCReports n where n.IsDeleted=0 " +
    "<if test='query.jobId!= null and query.jobId !=\"\" '> AND n.NCRJobID=#{ query.jobId } </if>" +
    "<if test='query.lotId!= null and query.lotId !=\"\" '> AND n.NCRLotID=#{ query.lotId } </if>" +
    "</script>"
    })
    List<JSONObject> listByQuery(@Param("query") NcrQueryDTO queryDTO);
}
