package com.agile.qdmp.standalong.mapper.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.query.CompanyQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Company;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * COMPANY
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:59
 */
public interface CompanyMapper extends BaseMapper<Company> {

    @Select("select a.ID, a.OrgShortName from Objects_Organizations a WHERE a.IsDeleted=0")
    List<JSONObject> listByQuery(@Param("query") CompanyQueryDTO queryDTO);
}
