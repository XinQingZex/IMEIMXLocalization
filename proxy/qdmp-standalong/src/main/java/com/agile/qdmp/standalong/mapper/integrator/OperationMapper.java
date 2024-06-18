package com.agile.qdmp.standalong.mapper.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.query.OperationQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Operation;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Operation
 *
 * @author wenbinglei
 * @date 2022-10-21 11:47:53
 */
public interface OperationMapper extends BaseMapper<Operation> {

    List<JSONObject> listByQuery(@Param("query") OperationQueryDTO queryDTO);
}
