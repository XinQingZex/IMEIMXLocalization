package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.query.OperationQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Operation;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Operation
 *
 * @author wenbinglei
 * @date 2022-10-21 11:47:53
 */
public interface IOperationService extends IService<Operation> {

    /**
     * 批量新增Operation
     * @param list
     * @return
     */
    Boolean createOperationMulti(List<Operation> list);

    /**
     * 查询列表
     * @param queryDTO
     * @return
     */
    List<JSONObject> listByQuery(OperationQueryDTO queryDTO);
}
