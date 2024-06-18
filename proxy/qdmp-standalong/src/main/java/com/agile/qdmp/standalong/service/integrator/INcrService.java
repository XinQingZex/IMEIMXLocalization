package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.query.NcrQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Ncr;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * NCR
 *
 * @author hyzh code generator
 * @date 2022-11-28 14:18:46
 */
public interface INcrService extends IService<Ncr> {

    /**
     * 批量新增Ncr
     * @param ncrList
     * @return
     */
    Boolean createNcrMulti(List<Ncr> ncrList);

    /**
     * 查询列表
     * @param queryDTO
     * @return
     */
    List<JSONObject> listByQuery(NcrQueryDTO queryDTO);
}
