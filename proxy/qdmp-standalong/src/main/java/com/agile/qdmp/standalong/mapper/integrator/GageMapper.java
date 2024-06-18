package com.agile.qdmp.standalong.mapper.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.query.GageQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Gage;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * GAGE
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:55
 */
public interface GageMapper extends BaseMapper<Gage> {

    /**
     * 获取所有不重复记录，更新category使用
     * @param serverId
     * @return
     */
//    @Select("select DISTINCT company_id,company_name,server_id,server_name,category_code from im_gage where server_id=#{serverId}")
    @Select("select DISTINCT company_id,company_name,server_id,server_name,category_code from im_gage")
    List<Gage> distinctRow(Long serverId);

    List<JSONObject> listByQuery(@Param("query") GageQueryDTO queryDTO, @Param("cursor") Integer cursor, @Param("limit") Integer limit);
}
