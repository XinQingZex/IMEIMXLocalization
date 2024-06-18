package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.query.LotQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Lot;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * LOT
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:16
 */
public interface ILotService extends IService<Lot> {

    /**
     * 批量新增Lot
     * @param list
     * @return
     */
    Boolean createLotMulti(List<Lot> list);

    List<JSONObject> listByQuery(LotQueryDTO queryDTO);

    JSONObject getByLotId(String lotId);

    /**
     * 创建Job后修改第一个Lot
     * @param guid
     * @return
     */
    JSONObject getFirstLot(String guid);

    /**
     * 直接修改Lot的数据
     * @param id
     * @param size
     * @param qualityStage
     */
    int modifyFirstLot(Integer id, Integer size, Integer qualityStage);

    /**
     * 根据ContactID查询
     * @param queryDTO
     * @return
     */
    List<JSONObject> listByContactId(LotQueryDTO queryDTO);
}
