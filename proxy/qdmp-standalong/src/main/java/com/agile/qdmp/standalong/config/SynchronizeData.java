package com.agile.qdmp.standalong.config;

import com.agile.qdmp.standalong.model.enums.CommonConstants;
import com.agile.qdmp.standalong.service.integrator.IImApiRecordService;
import com.agile.qdmp.standalong.tool.api.IMService;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: wenbinglei
 * @Date: 2022/11/10 19:35
 * @Description:
 */
@Component
@Slf4j
public class SynchronizeData implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private IMService imService;
    @Autowired
    private IImApiRecordService imApiRecordService;
    @Autowired
    private SysConfig sysConfig;

//    @Value("#{environment.QDMP_HOME}")
//    private String test1;
//    @Value("${environment.QDMP_HOME}")
//    private String test;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        System.out.println("容器初始化完毕");
//        String serverUri = sysConfig.getServerUri();
//        JSONObject loginData = new JSONObject();
//        loginData.put("Username", sysConfig.getUserName());
//        loginData.put("Password", sysConfig.getPassword());
//        try {
//            String apiResult = imService.post(serverUri + "/api/auth/token", null, loginData);
//            JSONObject res = JSONObject.parseObject(apiResult);
//            if(res.containsKey(CommonConstants.ACCESS_TOKEN_KEY) && StringUtils.isNotBlank(res.getString(CommonConstants.ACCESS_TOKEN_KEY))) {
//                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(CommonConstants.ACCESS_TOKEN_KEY), null, null, false);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(ACCESS_TOKEN_KEY), "company", null);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(CommonConstants.ACCESS_TOKEN_KEY), "part", null);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(ACCESS_TOKEN_KEY), "job", null);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(ACCESS_TOKEN_KEY), "lot", null);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(ACCESS_TOKEN_KEY), "sample", null);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(ACCESS_TOKEN_KEY), "drawing", null);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(ACCESS_TOKEN_KEY), "dim", null);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(ACCESS_TOKEN_KEY), "result", null);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(ACCESS_TOKEN_KEY), "gage", null);
//            }
//        } catch (IMErrorException e) {
//            log.error(e.getMessage());
//        }
    }
}