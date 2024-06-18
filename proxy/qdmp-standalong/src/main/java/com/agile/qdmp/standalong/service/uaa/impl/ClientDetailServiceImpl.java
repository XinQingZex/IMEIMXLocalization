package com.agile.qdmp.standalong.service.uaa.impl;

import com.agile.qdmp.standalong.mapper.uaa.ClientDetailMapper;
import com.agile.qdmp.standalong.model.entity.uaa.ClientDetail;
import com.agile.qdmp.standalong.service.uaa.IClientDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 客户端信息 服务类
 *
 * @author wenbinglei
 * @date 2021-02-01 15:04:38
 */
@Service
public class ClientDetailServiceImpl extends ServiceImpl<ClientDetailMapper, ClientDetail> implements IClientDetailService {

}
