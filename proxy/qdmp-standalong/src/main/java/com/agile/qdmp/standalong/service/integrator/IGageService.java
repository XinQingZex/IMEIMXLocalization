package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.query.GageQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Gage;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * GAGE
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:55
 */
public interface IGageService extends IService<Gage> {

    /**
     * 获取所有不重复量具记录
     * @param serverId
     * @return
     */
    List<Gage> queryDistinctRow(Long serverId);

    /**
     * 批量新增Gage
     * @param list
     * @return
     */
    Boolean createGageMulti(List<Gage> list);

    List<JSONObject> listByQuery(GageQueryDTO queryDTO, Integer cursor, Integer limit);
}
