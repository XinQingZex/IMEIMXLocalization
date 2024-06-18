package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.query.ItemQueryDTO;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * LOT
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:16
 */
public interface IImService {
    /**
     * 查询Projects
     * @param queryDTO
     * @return
     */
    List<JSONObject> listProjectsByQuery(HashMap<String, String> queryDTO);

    /**
     * Operations
     * @param queryDTO
     * @return
     */
    List<JSONObject> listOperationsByQuery(HashMap<String, String> queryDTO);

    /**
     * 全局检索
     * @param queryDTO
     * @return
     */
    List<JSONObject> search(HashMap<String, String> queryDTO);

    /**
     *
     * @param queryDTO
     * @return
     */
    List<JSONObject> listAQLHeaderByQuery(HashMap<String, String> queryDTO);
}
