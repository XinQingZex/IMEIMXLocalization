package com.agile.qdmp.standalong.config;

import com.agile.qdmp.standalong.model.enums.ApiEnum;
import com.agile.qdmp.standalong.model.enums.CommonConstants;
import com.agile.qdmp.standalong.service.integrator.IDrawingService;
import com.agile.qdmp.standalong.service.integrator.IImApiRecordService;
import com.agile.qdmp.standalong.tool.api.IMService;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: wenbinglei
 * @Date: 2022/12/9 16:53
 * @Description:
 */
@Component
@Slf4j
public class SynchronizeTask {
    @Autowired
    private IMService imService;
    @Autowired
    private IImApiRecordService imApiRecordService;
    @Autowired
    private IDrawingService drawingService;
    @Autowired
    private SysConfig sysConfig;
//
////    @Scheduled(cron ="0 */10 0 * * * ?")
//    @Scheduled(fixedDelay = 1000 * 60 * 20)
//    public void executeSynchronizeIM() {
//        String serverUri = sysConfig.getServerUri();
//        JSONObject loginData = new JSONObject();
//        loginData.put("Username", sysConfig.getUserName());
//        loginData.put("Password", sysConfig.getPassword());
//        try {
//            String apiResult = imService.post(serverUri + "/api/auth/token", null, loginData);
//            JSONObject res = JSONObject.parseObject(apiResult);
//            if(res.containsKey(CommonConstants.ACCESS_TOKEN_KEY) && StringUtils.isNotBlank(res.getString(CommonConstants.ACCESS_TOKEN_KEY))) {
//                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(CommonConstants.ACCESS_TOKEN_KEY), null, null, false, null);
//
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(ACCESS_TOKEN_KEY), "company", null);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(ACCESS_TOKEN_KEY), "part", null);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(CommonConstants.ACCESS_TOKEN_KEY), "job", null, false, null);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(CommonConstants.ACCESS_TOKEN_KEY), "lot", null, false, null);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(ACCESS_TOKEN_KEY), "sample", null);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(CommonConstants.ACCESS_TOKEN_KEY), "drawing", null, false, null);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(CommonConstants.ACCESS_TOKEN_KEY), "dim", null, false, null);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(ACCESS_TOKEN_KEY), "result", null);
////                imApiRecordService.synchronizeServerData(-1L, serverUri, res.getString(CommonConstants.ACCESS_TOKEN_KEY), "gage", null, false, null);
//            }
//        } catch (IMErrorException e) {
//            log.error(e.getMessage());
//        }
//    }
//
//
//    @Scheduled(fixedDelay = 1000 * 60 * 20)
//    public void executeDrawingImage() {
//        drawingService.buildImage();
//    }
//
//    //    @Scheduled(cron ="0 */10 0 * * * ?")
//    @Scheduled(fixedDelay = 1000 * 60 * 30)
//    public void executeNormalizeDimData() {
//        String serverUri = sysConfig.getServerUri();
//        JSONObject loginData = new JSONObject();
//        loginData.put("Username", sysConfig.getUserName());
//        loginData.put("Password", sysConfig.getPassword());
//        try {
//            String apiResult = imService.post(serverUri + "/api/auth/token", null, loginData);
//            JSONObject res = JSONObject.parseObject(apiResult);
//            if(res.containsKey(CommonConstants.ACCESS_TOKEN_KEY) && StringUtils.isNotBlank(res.getString(CommonConstants.ACCESS_TOKEN_KEY))) {
//                imApiRecordService.normalizeDimData(serverUri, ApiEnum.PROCEDURE.getDesc(), res.getString(CommonConstants.ACCESS_TOKEN_KEY), null);
//            }
//        } catch (IMErrorException e) {
//            log.error(e.getMessage());
//        }
//    }
}
