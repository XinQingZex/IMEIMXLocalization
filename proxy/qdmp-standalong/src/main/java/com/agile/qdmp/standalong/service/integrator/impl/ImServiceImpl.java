package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.GageMapper;
import com.agile.qdmp.standalong.mapper.integrator.ImMapper;
import com.agile.qdmp.standalong.model.dto.integrator.query.GageQueryDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.ItemQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Gage;
import com.agile.qdmp.standalong.service.integrator.IGageService;
import com.agile.qdmp.standalong.service.integrator.IImService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GAGE 服务类
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:55
 */
@Service
public class ImServiceImpl implements IImService {

    @Resource
    ImMapper imMapper;

    @Override
    public List<JSONObject> listProjectsByQuery(HashMap<String, String> queryDTO) {
        return imMapper.listProjectsByQuery(queryDTO);
    }

    @Override
    public List<JSONObject> listOperationsByQuery(HashMap<String, String> queryDTO) {
        return imMapper.listOperationsByQuery(queryDTO);
    }

    @Override
    public List<JSONObject> search(HashMap<String, String> queryDTO) {
        return imMapper.search(queryDTO);
    }

    @Override
    public List<JSONObject> listAQLHeaderByQuery(HashMap<String, String> queryDTO) {
        return imMapper.listAQLHeaderByQuery(queryDTO);
    }
}
