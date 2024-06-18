package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.FullLotDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.PartQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Part;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * PART
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:11
 */
public interface IPartService extends IService<Part> {

    /**
     * 获取合并后零件列表
     * @param queryDTO
     * @return
     */
    List<JSONObject> listByQuery(PartQueryDTO queryDTO);
//    List<Part> listByQuery(PartQueryDTO queryDTO);
    JSONObject getByPartId(String id);

    /**
     * 图纸检索
     * @param queryDTO
     * @return
     */
    List<JSONObject> searchWithDrawingByQuery(PartQueryDTO queryDTO, Integer cursor, Integer limit);
    /**
     * 批量创建
     * @param list
     * @return
     */
    Boolean createPartMulti(List<Part> list);


    /**
     * 根据InspCenterGuid获取
     * @param queryDTO
     * @return
     */
    List<FullLotDTO> listByInspCenterGuidQuery(PartQueryDTO queryDTO);

    /**
     * 更新CustomerName
     */
    int updateCustomerName();

    /**
     * 更新Total数据
     * @return
     */
    int updateTotalNum();
}
