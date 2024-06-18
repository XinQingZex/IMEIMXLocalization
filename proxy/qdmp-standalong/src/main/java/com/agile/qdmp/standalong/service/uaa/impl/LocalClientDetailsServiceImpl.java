package com.agile.qdmp.standalong.service.uaa.impl;

import com.agile.tem.common.core.constant.CacheConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Author: wenbinglei
 * @Date: 2020/9/23 14:23
 * @Description:
 */
@Service
@Transactional
@Slf4j
public class LocalClientDetailsServiceImpl extends JdbcClientDetailsService {
    public LocalClientDetailsServiceImpl(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 重写原生方法支持redis缓存
     * @param clientId
     * @return ClientDetails
     * @throws InvalidClientException
     */
    @Override
    @Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    public ClientDetails loadClientByClientId(String clientId) {
        return super.loadClientByClientId(clientId);
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        return super.listClientDetails();
    }
}
