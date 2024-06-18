package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.entity.integrator.InspCenter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * InspCenters
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:50
 */
public interface IInspCenterService extends IService<InspCenter> {

    /**
     * 批量保存InspCenter
     * @param list
     * @return
     */
    Boolean createInspCenterMulti(List<InspCenter> list);
}
