package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.LocationMapper;
import com.agile.qdmp.standalong.model.entity.integrator.Location;
import com.agile.qdmp.standalong.service.integrator.ILocationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * LOCATION 服务类
 *
 * @author wenbinglei
 * @date 2022-10-21 11:48:01
 */
@Service
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Location> implements ILocationService {

}
