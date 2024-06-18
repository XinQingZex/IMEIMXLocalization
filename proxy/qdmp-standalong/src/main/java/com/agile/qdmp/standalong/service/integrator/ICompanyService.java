package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.query.CompanyQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Company;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * COMPANY
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:59
 */
public interface ICompanyService extends IService<Company> {

    /**
     * 批量新增Company
     * @param list
     * @return
     */
    Boolean createCompanyMulti(List<Company> list);

    /**
     * 查询
     * @param queryDTO
     * @return
     */
    List<JSONObject> listByQuery(CompanyQueryDTO queryDTO);
}
