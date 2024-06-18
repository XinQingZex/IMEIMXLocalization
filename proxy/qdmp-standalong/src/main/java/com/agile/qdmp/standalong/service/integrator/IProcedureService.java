package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.entity.integrator.Procedure;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Procedure
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:46
 */
public interface IProcedureService extends IService<Procedure> {

    /**
     * 批量新增Procedure
     * @param list
     * @return
     */
    Boolean createProcedureMulti(List<Procedure> list);
}
