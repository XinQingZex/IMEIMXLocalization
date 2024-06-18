package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.config.SysConfig;
import com.agile.qdmp.standalong.mapper.integrator.ImApiRecordMapper;
import com.agile.qdmp.standalong.model.dto.integrator.*;
import com.agile.qdmp.standalong.model.entity.integrator.*;
import com.agile.qdmp.standalong.model.enums.ApiEnum;
import com.agile.qdmp.standalong.model.enums.CommonConstants;
import com.agile.qdmp.standalong.service.integrator.*;
import com.agile.qdmp.standalong.tool.api.IMService;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.agile.tem.common.core.util.R;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * IM_API_RECORD 服务类
 *
 * @author hyzh code generator
 * @date 2022-09-19 09:56:55
 */
@Service
@Slf4j
public class ImApiRecordServiceImpl extends ServiceImpl<ImApiRecordMapper, ImApiRecord> implements IImApiRecordService {
    private final static List<ApiData> apiDataList = new LinkedList<>();
//    private final static List<ApiRtData> apiRtDataList = new LinkedList<>();
    private final static int apiDataBatchNumber = 5000;
    @Resource
    private IMService imService;
    @Resource
    private IImServerService imServerService;
    @Resource
    private IApiDataService apiDataService;
    @Resource
    private IApiRtDataService apiRtDataService;
    @Resource
    private IDrawingService drawingService;
    @Resource
    private IPartService partService;
    @Resource
    private IJobService jobService;
    @Resource
    private ILotService lotService;
    @Resource
    private ISampleService sampleService;
    @Resource
    private IDimensionService dimensionService;
    @Resource
    private IResultService resultService;
    @Resource
    private ICompanyService companyService;
    @Resource
    private IGageService gageService;
    @Resource
    private IGageCategoryService gageCategoryService;
    @Resource
    private ICharacterDesignatorService characterDesignatorService;
    @Resource
    private IDimensionTypeService dimensionTypeService;
    @Resource
    private IReceiverService receiverService;
    @Resource
    private IOperationService operationService;
    @Resource
    private INcrService ncrService;
    @Resource
    private INcrResultService ncrResultService;
    @Resource
    private IContactService contactService;
    @Resource
    private IProcedureService procedureService;
    @Resource
    private IInspCenterService inspCenterService;
    @Resource
    private SysConfig sysConfig;


    @Override
    public R<JSONObject> handle(RequestDTO params) {
        try {
            String apiResult = imService.post(sysConfig.getServerUri() + params.getApiUri(), params.getToken(), params.getData());
            JSONObject res = JSONObject.parseObject(apiResult);
//            if(params.getApiUri().equalsIgnoreCase("/api/jobs/set")) {
//                JSONObject newJob = res.getJSONObject("OutputJob");
//                if(Objects.nonNull(newJob)) {
//                    JSONObject lotParams = new JSONObject();
//                    lotParams.put("JobGUID", newJob.getString(CommonConstants.GUID));
//                    synchronizeServerData(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.LOT_STR, lotParams, true, newJob.getString(CommonConstants.GUID));
//
//                    JSONObject partParams = new JSONObject();
//                    partParams.put("PartGUIDs", newJob.getString("PartGUID"));
//                    synchronizeServerData(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.PART_STR, partParams, true, newJob.getString(CommonConstants.GUID));
//                    partParams.put("PartGUID", newJob.getString("PartGUID"));
////                    synchronizeServerData(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.JOB_STR, partParams, true, newJob.getString(CommonConstants.GUID));
//                    synchronizeServerData(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.DRAWING_STR, partParams, true, newJob.getString("PartGUID"));
//
////                    synchronizeServerDataRt(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.SAMPLE_STR, partParams, true);
////                    synchronizeServerDataRt(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.DIM_STR, partParams, true);
////                    synchronizeServerDataRt(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.RESULT_STR, lotParams, true);
//                }
//            }
//            if(params.getApiUri().equalsIgnoreCase("/api/lots/set")) {
//                JSONObject newLot = res.getJSONObject("OutputLot");
//                if(Objects.nonNull(newLot)) {
//                    JSONObject lotParams = new JSONObject();
//                    lotParams.put("JobGUID", newLot.getString("JobGUID"));
//                    synchronizeServerData(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.LOT_STR, lotParams, true, newLot.getString(CommonConstants.GUID));
//                    JSONObject partParams = new JSONObject();
//                    partParams.put("JobGUIDs", newLot.getString("JobGUID"));
//                    synchronizeServerData(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.JOB_STR, partParams, true, newLot.getString(CommonConstants.GUID));
////                    synchronizeServerDataRt(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.RESULT_STR, lotParams, true);
//                }
//            }
//            if(params.getApiUri().equalsIgnoreCase("/api/samples/set")) {
//                JSONObject newSample = res.getJSONObject("OutputSample");
//                if(Objects.nonNull(newSample)) {
//                    JSONObject lotParams = new JSONObject();
//                    lotParams.put("LotGUID", newSample.getString("LotGUID"));
//                    synchronizeServerData(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.SAMPLE_STR, lotParams, true, newSample.getString("LotGUID"));
//                }
//
////                JSONObject resultParams = new JSONObject();
////                resultParams.put("SampleGUID", res.getJSONObject("OutputSample").getString("GUID"));
////                synchronizeServerDataRt(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.RESULT_STR, resultParams, true);
//            }
//
//            if(params.getApiUri().equalsIgnoreCase("/api/samples/createaqlsamples")) {
//                // 此处Result较多，需要注意执行效率
////                JSONObject resultParams = new JSONObject();
////                resultParams.put("Status", 0);
////                resultParams.put("JobGUID", params.getData().getString("JobGUID"));
////                synchronizeServerData(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.RESULT_STR, resultParams, true, params.getData().getString("JobGUID"));
//
////                JSONArray operations = params.getData().getJSONArray("Operations");
//                // 根据OperationGUID 批量删除DIMS
//
//
//
//                JSONObject lotParams = new JSONObject();
//                lotParams.put("LotGUID", params.getData().getString("LotGUID"));
//                synchronizeServerData(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.SAMPLE_STR, lotParams, true, params.getData().getString("LotGUID"));
//                lotParams.put("LotGUIDs", params.getData().getString("LotGUID"));
//                synchronizeServerData(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.LOT_STR, lotParams, true, params.getData().getString("LotGUID"));
//                // 此处有延迟，接口查询Job返回的样品数为0
//                JSONObject jobParams = new JSONObject();
//                jobParams.put("JobGUIDs", params.getData().getString("JobGUID"));
//                synchronizeServerData(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.JOB_STR, jobParams, true, params.getData().getString("JobGUID"));
////                Integer totalSampleCount = lotService.lambdaQuery().eq(Lot::getJobGuid, params.getData().getString("JobGUID")).count();
////                jobService.lambdaUpdate().eq(Job::getGuid, params.getData().getString("JobGUID")).set(Job::getTotalSamples, totalSampleCount).update();
//
//            }
//            if(params.getApiUri().equalsIgnoreCase("/api/results/bulkload")) {
//                JSONObject resultParams = new JSONObject();
//                resultParams.put("SampleGUID", params.getData().getString("SampleGUID"));
//                synchronizeServerData(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.RESULT_STR, resultParams, true, params.getData().getString("SampleGUID"));
//            }
//            if(params.getApiUri().equalsIgnoreCase("/api/ncr/set")) {
//                JSONObject resultParams = new JSONObject();
//                resultParams.put("LotGUID", params.getData().getString("LotGUID"));
//                synchronizeServerData(params.getServerId(), sysConfig.getServerUri(), params.getToken(),CommonConstants.NCR_STR, resultParams, true, params.getData().getString("LotGUID"));
//            }
            return R.ok(res);
        } catch (IMErrorException e) {
            log.error(e.getMessage());
            return R.failed(e.getMessage());
        }
    }

//    @Async
    @Override
    public Boolean synchronizeServerData(Long serverId, String serverUri, String accessToken, String type, JSONObject params, Boolean realtime, String rtKey) {
        serverUri = sysConfig.getServerUri();
        Integer startTime = (int) System.currentTimeMillis() / 1000;
        ImServer server = imServerService.getById(serverId);
        if(server == null) {
            server = new ImServer();
            server.setId(serverId);
            server.setName("LOCAL");
            server.setUpdateStatus(true);
            server.setUpdateTime((int) (System.currentTimeMillis() / 1000));
            imServerService.save(server);
        } else {
            if(!realtime && server.getUpdateStatus()) {
                return false;
            }
            server.setId(serverId);
            server.setUpdateStatus(true);
            server.setUpdateTime((int) (System.currentTimeMillis() / 1000));
            imServerService.updateById(server);
        }

        Boolean isSuccess = true;
        if(StringUtils.isBlank(type) || type.equalsIgnoreCase(CommonConstants.PART_STR)) {
            if(realtime) { apiRtDataService.removeByType(ApiEnum.PART.getDesc(), rtKey); }
            else { apiDataService.removeByType(ApiEnum.PART.getDesc()); }
            isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.PART.getDesc(), params, realtime, rtKey);
            buildApiRecord(CommonConstants.PART_STR, serverUri);
        }
        if(isSuccess && (StringUtils.isBlank(type) || type.equalsIgnoreCase(CommonConstants.DRAWING_STR)) ) {
            if(realtime) { apiRtDataService.removeByType(ApiEnum.DRAWING.getDesc(), rtKey);}
            else { apiDataService.removeByType(ApiEnum.DRAWING.getDesc());}
            isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.DRAWING.getDesc(), params, realtime, rtKey);
            buildApiRecord(CommonConstants.DRAWING_STR, serverUri);
        }
        if(isSuccess && (StringUtils.isBlank(type) || type.equalsIgnoreCase(CommonConstants.INSP_CENTER_STR))) {
            if(realtime) { apiRtDataService.removeByType(ApiEnum.INSPCENTER.getDesc(), rtKey); }
            else {apiDataService.removeByType(ApiEnum.INSPCENTER.getDesc()); }
            isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.INSPCENTER.getDesc(), params, realtime, rtKey);
            buildApiRecord(CommonConstants.INSP_CENTER_STR, serverUri);
        }
        if(isSuccess && (StringUtils.isBlank(type) || type.equalsIgnoreCase(CommonConstants.PROCEDURE_STR))) {
            if(realtime) { apiRtDataService.removeByType(ApiEnum.PROCEDURE.getDesc(), rtKey); }
            else {apiDataService.removeByType(ApiEnum.PROCEDURE.getDesc());}
            isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.PROCEDURE.getDesc(), params, realtime, rtKey);
            buildApiRecord(CommonConstants.PROCEDURE_STR, serverUri);
        }
        if(isSuccess && (StringUtils.isBlank(type) || type.equalsIgnoreCase(CommonConstants.OPERATION_STR))) {
            if(realtime) { apiRtDataService.removeByType(ApiEnum.OPERATION.getDesc(), rtKey);}
            else {apiDataService.removeByType(ApiEnum.OPERATION.getDesc());}
            isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.OPERATION.getDesc(), params, realtime, rtKey);
            buildApiRecord(CommonConstants.OPERATION_STR, serverUri);
        }
        if(isSuccess && (StringUtils.isBlank(type) || type.equalsIgnoreCase(CommonConstants.JOB_STR)) ) {
            if(realtime) { apiRtDataService.removeByType(ApiEnum.JOB.getDesc(), rtKey);}
            else {apiDataService.removeByType(ApiEnum.JOB.getDesc());}
            isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.JOB.getDesc(), params, realtime, rtKey);
            buildApiRecord(CommonConstants.JOB_STR, serverUri);
            normalizeIMServerData(CommonConstants.JOB_STR);
        }
        if(isSuccess && (StringUtils.isBlank(type) || type.equalsIgnoreCase(CommonConstants.COMPANY_STR)) ) {
            if(realtime) { apiRtDataService.removeByType(ApiEnum.COMPANY.getDesc(), rtKey);}
            else { apiDataService.removeByType(ApiEnum.COMPANY.getDesc()); }
            isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.COMPANY.getDesc(), params, realtime, rtKey);
            buildApiRecord(CommonConstants.COMPANY_STR, serverUri);
            normalizeIMServerData(CommonConstants.COMPANY_STR);
        }
        if(isSuccess && (StringUtils.isBlank(type) || type.equalsIgnoreCase(CommonConstants.LOT_STR)) ) {
            if(realtime) { apiRtDataService.removeByType(ApiEnum.LOT.getDesc(), rtKey);}
            else {apiDataService.removeByType(ApiEnum.LOT.getDesc());}
            isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.LOT.getDesc(), params, realtime, rtKey);
            buildApiRecord(CommonConstants.LOT_STR, serverUri);
        }
        if(isSuccess && (StringUtils.isBlank(type) || type.equalsIgnoreCase(CommonConstants.SAMPLE_STR)) ) {
            if(realtime) { apiRtDataService.removeByType(ApiEnum.SAMPLE.getDesc(), rtKey);}
            else {apiDataService.removeByType(ApiEnum.SAMPLE.getDesc());}
            isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.SAMPLE.getDesc(), params, realtime, rtKey);
            buildApiRecord(CommonConstants.SAMPLE_STR, serverUri);
        }

        if(isSuccess && (StringUtils.isBlank(type) || type.equalsIgnoreCase(CommonConstants.DIM_STR)) ) {
            if(realtime) { apiRtDataService.removeByType(ApiEnum.DIM.getDesc(), rtKey);}
            else { apiDataService.removeByType(ApiEnum.DIM.getDesc());}
            isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.DIM.getDesc(), params, realtime, rtKey);
            buildApiRecord(CommonConstants.DIM_STR, serverUri);
            normalizeIMServerData(CommonConstants.DIM_STR);
            normalizeIMServerData("designator");
            normalizeIMServerData("dimType");
//            normalizeDimData(serverUri, ApiEnum.PROCEDURE.getDesc(), accessToken, rtKey);
        }
        if(isSuccess && (StringUtils.isBlank(type) || type.equalsIgnoreCase(CommonConstants.RESULT_STR)) ) {
            if(realtime) {
                apiRtDataService.removeByType(ApiEnum.RESULT.getDesc(), rtKey);
                isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.RESULT.getDesc(), params, realtime, rtKey);
            } else {
                apiDataService.removeByType(ApiEnum.RESULT.getDesc());
            }
            if(realtime) {
                apiRtDataService.removeByType(ApiEnum.FAILED_RESULT.getDesc(), rtKey);
                isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.FAILED_RESULT.getDesc(), params, realtime, rtKey);
            } else {
                apiDataService.removeByType(ApiEnum.FAILED_RESULT.getDesc());
            }
            buildApiRecord(CommonConstants.RESULT_STR, serverUri);
        }

        if(isSuccess && (StringUtils.isBlank(type) || type.equalsIgnoreCase(CommonConstants.GAGE_STR))) {
            if(realtime) { apiRtDataService.removeByType(ApiEnum.GAGE.getDesc(), rtKey);}
            else {apiDataService.removeByType(ApiEnum.GAGE.getDesc());}
            isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.GAGE.getDesc(), params, realtime, rtKey);
            gageCategoryService.normalizeGageCategoryData(serverId);
            buildApiRecord(CommonConstants.GAGE_STR, serverUri);
        }

        if(isSuccess && (StringUtils.isBlank(type) || type.equalsIgnoreCase(CommonConstants.RECEIVER_STR))) {
            if(realtime) { apiRtDataService.removeByType(ApiEnum.RECEIVER.getDesc(), rtKey);}
            else { apiDataService.removeByType(ApiEnum.RECEIVER.getDesc());}
            isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.RECEIVER.getDesc(), params, realtime, rtKey);
            buildApiRecord(CommonConstants.RECEIVER_STR, serverUri);
        }
        if(isSuccess && (StringUtils.isBlank(type) || type.equalsIgnoreCase(CommonConstants.NCR_STR))) {
            if(realtime) { apiRtDataService.removeByType(ApiEnum.NCR.getDesc(), rtKey);}
            else { apiDataService.removeByType(ApiEnum.NCR.getDesc());}
            isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.NCR.getDesc(), params, realtime, rtKey);
            if(isSuccess) {
                apiDataService.removeByType(ApiEnum.NCR_RESULT.getDesc());
                apiRtDataService.removeByType(ApiEnum.NCR_RESULT.getDesc(), rtKey);
                List<Ncr> ncrList = ncrService.lambdaQuery().select(Ncr::getGuid).list();
                for(Ncr ncr : ncrList) {
                    JSONObject ncrResultParams = new JSONObject();
                    ncrResultParams.put("NCRGUID", ncr.getGuid());
                    isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.NCR_RESULT.getDesc(), ncrResultParams, true, ncr.getGuid());
                }
//                apiDataService.cleanData(ApiEnum.NCR_RESULT.getDesc());
//                handleData(ApiEnum.NCR_RESULT.getDesc(), serverId, serverUri, accessToken);
            }
            buildApiRecord(CommonConstants.NCR_STR, serverUri);
        }
        if(isSuccess && (StringUtils.isBlank(type) || type.equalsIgnoreCase(CommonConstants.CONTACT_STR))) {
            if(realtime) { apiRtDataService.removeByType(ApiEnum.CONTACT.getDesc(), rtKey);}
            else { apiDataService.removeByType(ApiEnum.CONTACT.getDesc());}
            isSuccess = synchronizeIMRt(serverId, accessToken, serverUri, ApiEnum.CONTACT.getDesc(), params, realtime, rtKey);
            buildApiRecord(CommonConstants.CONTACT_STR, serverUri);
        }
        server.setUpdateStatus(false);
        server.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        imServerService.updateById(server);

        Integer endTime = (int) System.currentTimeMillis() / 1000;

        log.info("更新完毕 花费时间 {} 秒", endTime - startTime);
        return isSuccess;
    }

    /**
     * 同步数据
     * @param serverUri
     * @param accessToken
     * @return
     */
    private Boolean synchronizeIMRt(Long serverId, String accessToken, String serverUri, String apiUri, JSONObject params, Boolean realtime, String rtKey) {
        List<ApiRtData> apiRtDataList = new LinkedList<>();
        JSONObject queryParams = new JSONObject();
        queryParams.put(CommonConstants.PAGE_KEY, CommonConstants.EMPTY_STR);
        if(Objects.nonNull(params)) {
            for (Map.Entry entry : params.entrySet()) {
                queryParams.put(entry.getKey().toString(), entry.getValue());
            }
        }
        try {
            String apiResult = imService.post(serverUri + apiUri, accessToken, queryParams);
            JSONObject res = JSONObject.parseObject(apiResult);
            storeData(res, apiUri, params, realtime, rtKey, apiRtDataList);
            String nextPageId = res.getString(CommonConstants.NEXT_PAGE_KEY);
            while(StringUtils.isNotBlank(nextPageId)) {
                queryParams.put(CommonConstants.PAGE_KEY, nextPageId);
                apiResult = imService.post(serverUri + apiUri, accessToken, queryParams);
                res = JSONObject.parseObject(apiResult);
                storeData(res, apiUri, params, realtime, rtKey, apiRtDataList);
                nextPageId = res.getString(CommonConstants.NEXT_PAGE_KEY);
            }
            if(realtime) {
                if (apiRtDataList.size() > 0 && apiRtDataService.saveBatch(apiRtDataList)) {
                    apiRtDataList.clear();
                }
            } else {
                if (apiDataList.size() > 0 && apiDataService.saveBatch(apiDataList)) {
                    apiDataList.clear();
                }
                apiDataService.cleanData(apiUri);
            }
            handleData(apiUri, serverId, serverUri, accessToken, realtime, rtKey);
            return true;
        } catch (IMErrorException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 整理数据
     * @param type
     * @return
     */
    private Boolean normalizeIMServerData(String type) {
        if(StringUtils.isBlank(type)) {
            return true;
        }
        if(type.equalsIgnoreCase(CommonConstants.COMPANY_STR)) {
            partService.updateCustomerName();
        }
        if(type.equalsIgnoreCase(CommonConstants.JOB_STR)) {
            jobService.updatePartData();
            partService.updateTotalNum();
        }
        if(type.equalsIgnoreCase(CommonConstants.DIM_STR)) {
            dimensionService.updatePartGuidbyDrawing();
        }

//        if(type.equalsIgnoreCase(SAMPLE_STR)) {
//            List<SampleCount> list = sampleService.customCount();
//            for (SampleCount sampleCount : list) {
//                dimensionService.lambdaUpdate().set(Dimension::getPartGuid, drawing.getPartGuid()).eq(Dimension::getDrawingGuid, drawing.getGuid()).update();
//            }
//        }

        if(type.equalsIgnoreCase("designator")) {
            List<CharacterDesignator> characterDesignators = dimensionService.listCharacterDesignator();
            for(CharacterDesignator cd : characterDesignators) {
                CharacterDesignator current = characterDesignatorService.lambdaQuery().eq(CharacterDesignator::getName, cd.getName()).one();
                if(current == null) {
                    characterDesignatorService.save(cd);
                }
            }
        }

        if(type.equalsIgnoreCase("dimType")) {
            List<DimensionType> dimensionTypes = dimensionService.listDimensionTypes();
            for(DimensionType dt : dimensionTypes) {
                DimensionType current = dimensionTypeService.lambdaQuery().eq(DimensionType::getGuid, dt.getGuid()).one();
                if(current == null) {
                    dimensionTypeService.save(dt);
                }
            }
        }
//        this.baseMapper.normalizePartCompany();
//        this.baseMapper.normalizeJob();
//        this.baseMapper.normalizePartTotal();
//        this.baseMapper.normalizeDim();
////        this.baseMapper.normalizeSample();
////        this.baseMapper.normalizeLot();
        return true;
    }

    /**
     * 根据Procedure检测Dim属于哪个InspCenter
     * @return
     */
    @Override
    public Boolean normalizeDimData(String serverUri, String apiUri, String accessToken, String partGuid) {
        JSONObject queryParams = new JSONObject();
        try {
            List<Procedure> ps = procedureService.lambdaQuery().eq(Procedure::getHandleState, false).eq(StringUtils.isNotBlank(partGuid), Procedure::getPartGuid, partGuid).select(Procedure::getId, Procedure::getGuid, Procedure::getPartGuid).list();
            for(Procedure procedure : ps) {
                List<Dimension> dimensions = dimensionService.lambdaQuery().eq(Dimension::getPartGuid, procedure.getPartGuid()).eq(Dimension::getInspCenterGuid, "").select(Dimension::getId, Dimension::getGuid).list();
                List<Dimension> needUpdate = new ArrayList<>();
                for(Dimension dim : dimensions) {
                    queryParams.put("DimGUID", dim.getGuid());
                    String apiResult = imService.post(serverUri + apiUri, accessToken, queryParams);
                    JSONObject res = JSONObject.parseObject(apiResult);
                    if(res.getJSONArray("Procedures").size() > 0) {
                        dim.setInspCenterGuid(res.getJSONArray("Procedures").getJSONObject(0).getString("InspCenterGUID"));
                        dim.setProcedureGuid(procedure.getGuid());
                        needUpdate.add(dim);
                    }
                }
                procedure.setHandleState(true);
                procedureService.updateById(procedure);
                dimensionService.updateBatchById(needUpdate);
            }
            return true;
        } catch (IMErrorException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 存储数据
     * @param data
     * @param apiUri
     * @return
     */
    private Boolean storeData(JSONObject data, String apiUri, JSONObject params, Boolean realtime, String rtKey, List<ApiRtData> apiRtDataList) {
        JSONArray list = getDataList(apiUri, data);
        if(Objects.isNull(list)) {
            return true;
        }
        // 接口返回的数据有GUID和ResultGUID重复的现象
        Map<String, ApiData> storeMap = new HashMap<>();
        Map<String, ApiRtData> storeMapRt = new HashMap<>();
        for(int i = 0; i < list.size(); i++) {
            JSONObject j = list.getJSONObject(i);
            if(!apiUri.equalsIgnoreCase(ApiEnum.NCR_RESULT.getDesc())) {
                if(!j.containsKey(CommonConstants.GUID)) {
                    continue;
                }
            }

            // 特殊处理Contact start
            if(apiUri.equalsIgnoreCase(ApiEnum.CONTACT.getDesc())) {
                if(j.getString("FirstName").equalsIgnoreCase("Service")) {
                    continue;
                }
            }

            if(realtime) {
                ApiRtData apiRtData = new ApiRtData();
                // 特殊处理NCR RESULT
                if(apiUri.equalsIgnoreCase(ApiEnum.NCR_RESULT.getDesc())) {
                    apiRtData.setGuid(j.getString("ResultGUID"));
                    j.put("ncrGuid", params.getString("NCRGUID"));
                } else {
                    apiRtData.setGuid(j.getString(CommonConstants.GUID));
                }
                apiRtData.setFlag(DigestUtils.md5DigestAsHex(j.toJSONString().getBytes()));
                apiRtData.setType(apiUri);
                apiRtData.setSource(rtKey);
                apiRtData.setContent(j.toJSONString());
                storeMapRt.put(apiRtData.getGuid(), apiRtData);
            } else {
                ApiData apiData = new ApiData();
                // 特殊处理NCR RESULT
                if(apiUri.equalsIgnoreCase(ApiEnum.NCR_RESULT.getDesc())) {
                    apiData.setId(j.getString("ResultGUID"));
                    j.put("ncrGuid", params.getString("NCRGUID"));
                } else {
                    apiData.setId(j.getString(CommonConstants.GUID));
                }
                apiData.setFlag(DigestUtils.md5DigestAsHex(j.toJSONString().getBytes()));
                apiData.setType(apiUri);
                apiData.setContent(j.toJSONString());
                storeMap.put(apiData.getId(), apiData);
            }
        }
        if(realtime) {
//            for(ApiRtData apiRtData : storeMapRt.values()) {
//                apiRtDataList.add(apiRtData);
//            }
            apiRtDataList.addAll(storeMapRt.values());
            if (apiRtDataList.size() >= apiDataBatchNumber && apiRtDataService.saveBatch(apiRtDataList)) {
                apiRtDataList.clear();
            }
        } else {
//            for(ApiData apiData : storeMap.values()) {
//                apiDataList.add(apiData);
//            }
            apiDataList.addAll(storeMap.values());
            if (apiDataList.size() >= apiDataBatchNumber && apiDataService.saveBatch(apiDataList)) {
                apiDataList.clear();
            }
        }
        return true;
    }

    /**
     * 更新数据库
     * @param apiUri
     * @return
     */
    private Boolean handleData(String apiUri, Long serverId, String serverUri, String accessToken, Boolean realtime, String rtKey) {
        List<ApiData> list = new ArrayList<>();
        List<ApiRtData> listRt = new ArrayList<>();
        List<String> removedIds = new ArrayList<>();

        switch (ApiEnum.getByDesc(apiUri)) {
            case PART:
                List<Part> partList = new ArrayList<>();
                formatList(ApiEnum.PART.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatPart(list, listRt, partList, removedIds);
                partService.createPartMulti(partList);
                break;
            case JOB:
                List<Job> jobList = new ArrayList<>();
                formatList(ApiEnum.JOB.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatJob(list, listRt, jobList, removedIds);
                jobService.createJobMulti(jobList);
                break;
            case LOT:
                List<Lot> lotList = new ArrayList<>();
                formatList(ApiEnum.LOT.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatLot(list, listRt, lotList, removedIds);
                lotService.createLotMulti(lotList);
                break;
            case SAMPLE:
                List<Sample> sampleList = new ArrayList<>();
                formatList(ApiEnum.SAMPLE.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatSample(list, listRt, sampleList, removedIds);
                sampleService.createSampleMulti(sampleList);
                break;
            case DIM:
                List<Dimension> dimList = new ArrayList<>();
                formatList(ApiEnum.DIM.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatDim(list, listRt, dimList, removedIds);
                dimensionService.createDimensionMulti(dimList);
                break;
            case RESULT:
                List<Result> resultList = new ArrayList<>();
                formatList(ApiEnum.RESULT.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatResult(list, listRt, resultList, removedIds);
                resultService.createResultMulti(resultList);
                break;
            case FAILED_RESULT:
                List<Result> failedResultList = new ArrayList<>();
                formatList(ApiEnum.FAILED_RESULT.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatFailedResult(list, listRt, failedResultList, removedIds);
                resultService.updateResultMulti(failedResultList);
                break;
            case COMPANY:
                List<Company> companyList = new ArrayList<>();
                formatList(ApiEnum.COMPANY.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatCompany(list, listRt, companyList, removedIds);
                companyService.createCompanyMulti(companyList);
                break;
            case RECEIVER:
                List<Receiver> receiverList = new ArrayList<>();
                formatList(ApiEnum.RECEIVER.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatReceiver(list, listRt, receiverList, removedIds);
                receiverService.createReceiverMulti(receiverList);
                break;
            case INSPCENTER:
                List<InspCenter> inspCenterList = new ArrayList<>();
                formatList(ApiEnum.INSPCENTER.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatInspCenter(list, listRt, inspCenterList, removedIds);
                inspCenterService.createInspCenterMulti(inspCenterList);
                break;
            case NCR:
                List<Ncr> ncrList = new ArrayList<>();
                formatList(ApiEnum.NCR.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatNcr(list, listRt, ncrList, removedIds);
                ncrService.createNcrMulti(ncrList);
                break;
            case NCR_RESULT:
                List<NcrResult> failedNcrResultList = new ArrayList<>();
                formatList(ApiEnum.NCR_RESULT.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatNcrResult(list, listRt, failedNcrResultList, removedIds);
                ncrResultService.createNcrResultMulti(failedNcrResultList);
                break;
            case CONTACT:
                List<Contact> contactList = new ArrayList<>();
                formatList(ApiEnum.CONTACT.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatContact(list, listRt, contactList, removedIds);
                contactService.createContactMulti(contactList);
                break;
            case PROCEDURE:
                List<Procedure> procedureList = new ArrayList<>();
                formatList(ApiEnum.PROCEDURE.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatProcedure(list, listRt, procedureList, removedIds);
                procedureService.createProcedureMulti(procedureList);
                break;
            case OPERATION:
                List<Operation> operationList = new ArrayList<>();
                formatList(ApiEnum.OPERATION.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatOperation(list, listRt, operationList, removedIds);
                operationService.createOperationMulti(operationList);
                break;
            case GAGE:
                List<Gage> gageList = new ArrayList<>();
                formatList(ApiEnum.GAGE.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatGage(list, listRt, gageList, removedIds);
                gageService.createGageMulti(gageList);
                break;
            case DRAWING:
                formatList(ApiEnum.DRAWING.getDesc(), realtime, list, listRt, rtKey);
                if(Objects.isNull(list) && Objects.isNull(listRt) ) { break; }
                formatDrawing(list, listRt, removedIds, serverUri, accessToken);
                break;
            default:
                break;
        }
        if(removedIds.size() > 0) {
            if(realtime) {
                apiRtDataService.delByIds(removedIds);
            } else {
                apiDataService.delByIds(removedIds);
            }
        }
        return true;
    }

    /**
     * 获取数据列表
     * @param apiUri
     * @param data
     * @return
     */
    private JSONArray getDataList(String apiUri, JSONObject data) {
        JSONArray list = null;
        switch (ApiEnum.getByDesc(apiUri)) {
            case PART:
                list = data.getJSONArray(ApiEnum.PART.getValue());
                break;
            case JOB:
                list = data.getJSONArray(ApiEnum.JOB.getValue());
                break;
            case LOT:
                list = data.getJSONArray(ApiEnum.LOT.getValue());
                break;
            case SAMPLE:
                list = data.getJSONArray(ApiEnum.SAMPLE.getValue());
                break;
            case DIM:
                list = data.getJSONArray(ApiEnum.DIM.getValue());
                break;
            case RESULT:
                list = data.getJSONArray(ApiEnum.RESULT.getValue());
                break;
            case FAILED_RESULT:
                list = data.getJSONArray(ApiEnum.FAILED_RESULT.getValue());
                break;
            case COMPANY:
                list = data.getJSONArray(ApiEnum.COMPANY.getValue());
                break;
            case RECEIVER:
                list = data.getJSONArray(ApiEnum.RECEIVER.getValue());
                break;
            case INSPCENTER:
                list = data.getJSONArray(ApiEnum.INSPCENTER.getValue());
                break;
            case NCR:
                list = data.getJSONArray(ApiEnum.NCR.getValue());
                break;
            case NCR_RESULT:
                list = data.getJSONArray(ApiEnum.NCR_RESULT.getValue());
                break;
            case CONTACT:
                list = data.getJSONArray(ApiEnum.CONTACT.getValue());
                break;
            case PROCEDURE:
                list = data.getJSONArray(ApiEnum.PROCEDURE.getValue());
                break;
            case OPERATION:
                list = data.getJSONArray(ApiEnum.OPERATION.getValue());
                break;
            case GAGE:
                list = data.getJSONArray(ApiEnum.GAGE.getValue());
                break;
            case DRAWING:
                list = data.getJSONArray(ApiEnum.DRAWING.getValue());
                break;
            default:
                break;
        }
        return list;
    }
    /**
     * 整理List
     * @param desc
     * @param realtime
     * @param list
     * @param listRt
     */
    private void formatList(String desc, Boolean realtime, List<ApiData> list, List<ApiRtData> listRt, String rtKey) {
        if(realtime) {
            listRt.addAll(apiRtDataService.lambdaQuery().eq(ApiRtData::getType, desc).eq(ApiRtData::getSource, rtKey).list());
        } else {
            list.addAll(apiDataService.lambdaQuery().eq(ApiData::getType, desc).list());
        }
    }

    /**
     * 整理Drawing
     * @param list
     * @param listRt
     * @param removedIds
     * @param serverUri
     * @param accessToken
     */
    private void formatDrawing(List<ApiData> list, List<ApiRtData> listRt, List<String> removedIds, String serverUri, String accessToken) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                Drawing dto = JSONObject.toJavaObject(j, Drawing.class);
                dto.setFlag(apiData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                drawingService.completeDrawingData(dto, serverUri, accessToken);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                Drawing dto = JSONObject.toJavaObject(j, Drawing.class);
                dto.setFlag(apiRtData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                drawingService.completeDrawingData(dto, serverUri, accessToken);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }

    /**
     * 整理Operation
     * @param list
     * @param listRt
     * @param operationList
     * @param removedIds
     */
    private void formatOperation(List<ApiData> list, List<ApiRtData> listRt, List<Operation> operationList, List<String> removedIds) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                Operation dto = JSONObject.toJavaObject(j, Operation.class);
                dto.setFlag(apiData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                operationList.add(dto);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                Operation dto = JSONObject.toJavaObject(j, Operation.class);
                dto.setFlag(apiRtData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                operationList.add(dto);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }

    /**
     * 整理Procedure
     * @param list
     * @param listRt
     * @param procedureList
     * @param removedIds
     */
    private void formatProcedure(List<ApiData> list, List<ApiRtData> listRt, List<Procedure> procedureList, List<String> removedIds) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                Procedure dto = JSONObject.toJavaObject(j, Procedure.class);
                dto.setFlag(apiData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                procedureList.add(dto);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                Procedure dto = JSONObject.toJavaObject(j, Procedure.class);
                dto.setFlag(apiRtData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                procedureList.add(dto);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }

    /**
     * 整理 Contact
     * @param list
     * @param listRt
     * @param contactList
     * @param removedIds
     */
    private void formatContact(List<ApiData> list, List<ApiRtData> listRt, List<Contact> contactList, List<String> removedIds) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                Contact dto = JSONObject.toJavaObject(j, Contact.class);
                dto.setFlag(apiData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                contactList.add(dto);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                Contact dto = JSONObject.toJavaObject(j, Contact.class);
                dto.setFlag(apiRtData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                contactList.add(dto);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }

    /**
     * 整理NcrResult
     * @param list
     * @param listRt
     * @param failedNcrResultList
     * @param removedIds
     */
    private void formatNcrResult(List<ApiData> list, List<ApiRtData> listRt, List<NcrResult> failedNcrResultList, List<String> removedIds) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                NcrResult dto = JSONObject.toJavaObject(j, NcrResult.class);
                dto.setFlag(apiData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                failedNcrResultList.add(dto);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                NcrResult dto = JSONObject.toJavaObject(j, NcrResult.class);
                dto.setFlag(apiRtData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                failedNcrResultList.add(dto);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }

    /**
     * 整理Ncr
     * @param list
     * @param listRt
     * @param ncrList
     * @param removedIds
     */
    private void formatNcr(List<ApiData> list, List<ApiRtData> listRt, List<Ncr> ncrList, List<String> removedIds) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                LocalDateTime creationDate = normalizeDate(j.getString("CreationDate"));
                j.remove("CreationDate");
                LocalDateTime responseDate = normalizeDate(j.getString("ResponseDate"));
                j.remove("ResponseDate");
                Ncr dto = JSONObject.toJavaObject(j, Ncr.class);
                dto.setCreationDate(creationDate);
                dto.setResponseDate(responseDate);
                dto.setFlag(apiData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                ncrList.add(dto);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                LocalDateTime creationDate = normalizeDate(j.getString("CreationDate"));
                j.remove("CreationDate");
                LocalDateTime responseDate = normalizeDate(j.getString("ResponseDate"));
                j.remove("ResponseDate");
                Ncr dto = JSONObject.toJavaObject(j, Ncr.class);
                dto.setCreationDate(creationDate);
                dto.setResponseDate(responseDate);
                dto.setFlag(apiRtData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                ncrList.add(dto);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }

    /**
     * 整理InspCenter
     * @param list
     * @param listRt
     * @param inspCenterList
     * @param removedIds
     */
    private void formatInspCenter(List<ApiData> list, List<ApiRtData> listRt, List<InspCenter> inspCenterList, List<String> removedIds) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                InspCenter dto = JSONObject.toJavaObject(j, InspCenter.class);
                dto.setFlag(apiData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                inspCenterList.add(dto);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                InspCenter dto = JSONObject.toJavaObject(j, InspCenter.class);
                dto.setFlag(apiRtData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                inspCenterList.add(dto);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }

    /**
     * 整理Gage
     * @param list
     * @param listRt
     * @param gageList
     * @param removedIds
     */
    private void formatGage(List<ApiData> list, List<ApiRtData> listRt, List<Gage> gageList, List<String> removedIds) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                Gage dto = JSONObject.toJavaObject(j, Gage.class);
                dto.setFlag(apiData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                gageList.add(dto);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                Gage dto = JSONObject.toJavaObject(j, Gage.class);
                dto.setFlag(apiRtData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                gageList.add(dto);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }

    /**
     * 整理Receiver
     * @param list
     * @param listRt
     * @param receiverList
     * @param removedIds
     */
    private void formatReceiver(List<ApiData> list, List<ApiRtData> listRt, List<Receiver> receiverList, List<String> removedIds) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                Receiver dto = JSONObject.toJavaObject(j, Receiver.class);
                dto.setFlag(apiData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                receiverList.add(dto);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                Receiver dto = JSONObject.toJavaObject(j, Receiver.class);
                dto.setFlag(apiRtData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                receiverList.add(dto);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }
    /**
     * 格式化Company
     * @param list
     * @param listRt
     * @param companyList
     * @param removedIds
     */
    private void formatCompany(List<ApiData> list, List<ApiRtData> listRt, List<Company> companyList, List<String> removedIds) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                Company dto = JSONObject.toJavaObject(j, Company.class);
                dto.setFlag(apiData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                companyList.add(dto);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                Company dto = JSONObject.toJavaObject(j, Company.class);
                dto.setFlag(apiRtData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                companyList.add(dto);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }

    /**
     * 格式化FailedResult
     * @param list
     * @param listRt
     * @param failedResultList
     * @param removedIds
     */
    private void formatFailedResult(List<ApiData> list, List<ApiRtData> listRt, List<Result> failedResultList, List<String> removedIds) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                LocalDateTime date = normalizeDate(j.getString("InspectedDate"));
                j.remove("InspectedDate");
                Result dto = JSONObject.toJavaObject(j, Result.class);
                dto.setFailedFlag(apiData.getFlag());
                dto.setInspectedDate(date);

                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                failedResultList.add(dto);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                LocalDateTime date = normalizeDate(j.getString("InspectedDate"));
                j.remove("InspectedDate");
                Result dto = JSONObject.toJavaObject(j, Result.class);
                dto.setFailedFlag(apiRtData.getFlag());
                dto.setInspectedDate(date);

                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                failedResultList.add(dto);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }

    /**
     * 整理Result
     * @param list
     * @param listRt
     * @param resultList
     * @param removedIds
     */
    private void formatResult(List<ApiData> list, List<ApiRtData> listRt, List<Result> resultList, List<String> removedIds) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                LocalDateTime date = normalizeDate(j.getString("InspectedDate"));
                j.remove("InspectedDate");
                Result dto = JSONObject.toJavaObject(j, Result.class);
                dto.setFlag(apiData.getFlag());
                dto.setInspectedDate(date);

                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                resultList.add(dto);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                LocalDateTime date = normalizeDate(j.getString("InspectedDate"));
                j.remove("InspectedDate");
                Result dto = JSONObject.toJavaObject(j, Result.class);
                dto.setFlag(apiRtData.getFlag());
                dto.setInspectedDate(date);

                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                resultList.add(dto);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }

    /**
     * 整理Dim
     * @param list
     * @param listRt
     * @param dimList
     * @param removedIds
     */
    private void formatDim(List<ApiData> list, List<ApiRtData> listRt, List<Dimension> dimList, List<String> removedIds) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                Dimension dto = JSONObject.toJavaObject(j, Dimension.class);
                dto.setFlag(apiData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                dimList.add(dto);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                Dimension dto = JSONObject.toJavaObject(j, Dimension.class);
                dto.setFlag(apiRtData.getFlag());
                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                dimList.add(dto);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }
    /**
     * 整理Sample
     * @param list
     * @param listRt
     * @param sampleList
     * @param removedIds
     */
    private void formatSample(List<ApiData> list, List<ApiRtData> listRt, List<Sample> sampleList, List<String> removedIds) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                LocalDateTime date = normalizeDate(j.getString("CreationDate"));
                j.remove("CreationDate");
                Sample dto = JSONObject.toJavaObject(j, Sample.class);
                dto.setFlag(apiData.getFlag());
                dto.setCreationDate(date);

                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                sampleList.add(dto);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                LocalDateTime date = normalizeDate(j.getString("CreationDate"));
                j.remove("CreationDate");
                Sample dto = JSONObject.toJavaObject(j, Sample.class);
                dto.setFlag(apiRtData.getFlag());
                dto.setCreationDate(date);

                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                sampleList.add(dto);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }
    /**
     * 格式化Lot
     * @param list
     * @param listRt
     * @param lotList
     * @param removedIds
     */
    private void formatLot(List<ApiData> list, List<ApiRtData> listRt, List<Lot> lotList, List<String> removedIds) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                LocalDateTime date = normalizeDate(j.getString("DueDate"));
                j.remove("DueDate");
                LocalDateTime date1 = normalizeDate(j.getString("StartDate"));
                j.remove("StartDate");
                Lot dto = JSONObject.toJavaObject(j, Lot.class);
                dto.setFlag(apiData.getFlag());
                dto.setDueDate(date);
                dto.setStartDate(date1);

                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                lotList.add(dto);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                LocalDateTime date = normalizeDate(j.getString("DueDate"));
                j.remove("DueDate");
                LocalDateTime date1 = normalizeDate(j.getString("StartDate"));
                j.remove("StartDate");
                Lot dto = JSONObject.toJavaObject(j, Lot.class);
                dto.setFlag(apiRtData.getFlag());
                dto.setDueDate(date);
                dto.setStartDate(date1);

                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                lotList.add(dto);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }

    /**
     * 格式化JOB
     * @param list
     * @param listRt
     * @param jobList
     * @param removedIds
     */
    private void formatJob(List<ApiData> list, List<ApiRtData> listRt, List<Job> jobList, List<String> removedIds) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                LocalDateTime date = normalizeDate(j.getString("ActivationDate"));
                j.remove("ActivationDate");
                LocalDateTime date1 = normalizeDate(j.getString("DeliveryDate"));
                j.remove("DeliveryDate");
                Job dto = JSONObject.toJavaObject(j, Job.class);
                dto.setFlag(apiData.getFlag());
                dto.setActivationDate(date);
                dto.setDeliveryDate(date1);

                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                jobList.add(dto);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                LocalDateTime date = normalizeDate(j.getString("ActivationDate"));
                j.remove("ActivationDate");
                LocalDateTime date1 = normalizeDate(j.getString("DeliveryDate"));
                j.remove("DeliveryDate");
                Job dto = JSONObject.toJavaObject(j, Job.class);
                dto.setFlag(apiRtData.getFlag());
                dto.setActivationDate(date);
                dto.setDeliveryDate(date1);

                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                jobList.add(dto);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }

    /**
     * 格式化Part
     * @param list
     * @param listRt
     * @param partList
     * @param removedIds
     */
    private void formatPart(List<ApiData> list, List<ApiRtData> listRt, List<Part> partList, List<String> removedIds) {
        if(Objects.nonNull(list)) {
            for(ApiData apiData : list) {
                JSONObject j = JSONObject.parseObject(apiData.getContent());
                LocalDateTime date = normalizeDate(j.getString("LastModificationDate"));
                j.remove("LastModificationDate");
                Part dto = JSONObject.toJavaObject(j, Part.class);
                dto.setFlag(apiData.getFlag());
                dto.setLastModificationDate(date);

                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                partList.add(dto);
                removedIds.add(apiData.getId());
            }
        }
        if(Objects.nonNull(listRt)) {
            for(ApiRtData apiRtData : listRt) {
                JSONObject j = JSONObject.parseObject(apiRtData.getContent());
                LocalDateTime date = normalizeDate(j.getString("LastModificationDate"));
                j.remove("LastModificationDate");
                Part dto = JSONObject.toJavaObject(j, Part.class);
                dto.setFlag(apiRtData.getFlag());
                dto.setLastModificationDate(date);

                dto.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                partList.add(dto);
                removedIds.add(apiRtData.getGuid());
            }
        }
    }

    /**
     * 格式化时间
     * @param dateStr
     * @return
     */
    private LocalDateTime normalizeDate(String dateStr) {
        if(StringUtils.isNotBlank(dateStr) && dateStr.indexOf("T") > 0) {
            try{
                return LocalDateTime.parse(dateStr.substring(0, dateStr.indexOf("+")-4), DateTimeFormatter.ofPattern(CommonConstants.DATE_FORMAT));
            } catch(RuntimeException e) {
                log.error(e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 保存记录
     * @param type
     * @param serverUri
     * @return
     */
    private void buildApiRecord(String type, String serverUri) {
        ImApiRecord imApiRecord = new ImApiRecord();
        imApiRecord.setServerId(-1L);
        imApiRecord.setServerUrl(serverUri);
        imApiRecord.setContent(type);
        imApiRecord.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        save(imApiRecord);
    }

}
