package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.CompanyMapper;
import com.agile.qdmp.standalong.model.dto.integrator.query.CompanyQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Company;
import com.agile.qdmp.standalong.service.integrator.ICompanyService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * COMPANY 服务类
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:59
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

    @Override
    public Boolean createCompanyMulti(List<Company> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Map<String, Company> dataList = new HashMap<>(10);
        for(Company dto : list) {
            dataList.put(dto.getFlag(), dto);
        }
        List<Company> exists = lambdaQuery().in(Company::getFlag, dataList.keySet()).select(Company::getFlag).list();
        if(exists.size() == dataList.size()) {
            return true;
        }
        for(Company company : exists) {
            dataList.remove(company.getFlag());
        }

        Map<String, Company> guidList = new HashMap<>(10);
        for(Company data : dataList.values()) {
            guidList.put(data.getGuid(), data);
        }

        // 不存在的
        List<Company> checkGuidList = lambdaQuery().in(Company::getGuid, guidList.keySet()).select(Company::getGuid, Company::getId).list();
        if(checkGuidList != null && checkGuidList.size() > 0) {
            List<Company> updates = new ArrayList<>();
            for(Company part : checkGuidList) {
                Company update = guidList.get(part.getGuid());
                update.setId(part.getId());
                updates.add(update);
                guidList.remove(part.getGuid());
            }
            if(updates.size() > 0) {
                updateBatchById(updates);
            }
        }
        if(guidList.size() > 0) {
            saveBatch(guidList.values());
        }
        return true;
    }

    @Override
    public List<JSONObject> listByQuery(CompanyQueryDTO queryDTO) {
        return baseMapper.listByQuery(queryDTO);
    }
}
