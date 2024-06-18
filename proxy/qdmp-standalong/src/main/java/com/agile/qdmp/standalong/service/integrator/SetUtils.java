package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.RequestDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.LotQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.*;
import com.agile.tem.common.core.util.R;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * @Author: wenbinglei
 * @Date: 2022/10/26 14:39
 * @Description:
 */
@Component("setUtils")
@Slf4j
@AllArgsConstructor
public class SetUtils {

    private final ISampleService sampleService;
    private final ILotService lotService;
    private final IJobService jobService;
    private final IPartService partService;
    private final INcrService ncrService;
    private final IResultService resultService;
    private final IImApiRecordService imApiRecordService;

    private static final String TIME_SUFFIX = ".0000000";

    /**
     * 发送Job数据到IM
     * @param params
     * @return
     */
    public Job setJob(RequestDTO params) {
        Job job = JSONObject.toJavaObject(params.getData(), Job.class);
        // Lot check
        JSONObject lotJson = null;
        if(params.getData().containsKey("lot")) {
            lotJson = params.getData().getJSONObject("lot");
        }

        JSONObject postData = buildJobPostData(job);
        params.setData(postData);

        log.info("JOB SEND {}", JSONObject.toJSONString(postData, true));
        R<JSONObject> proxyRes = imApiRecordService.handle(params);
        log.info("JOB RES {}", JSONObject.toJSONString(proxyRes, true));
        if(proxyRes.getCode() == 0) {
            JSONObject res = proxyRes.getData().getJSONObject("OutputJob");
            job.setGuid(res.getString("GUID"));

            // Lot start
            // 获取第一个Lot并更新Lot数据
            if(Objects.nonNull(lotJson)) {
                // 查询Lot第一个
                JSONObject firstLot = lotService.getFirstLot(job.getGuid());
                lotService.modifyFirstLot(firstLot.getInteger("ID"), lotJson.getInteger("size"), lotJson.getInteger("qualityStage"));
//                lot.setGuid(firstLot.getString("GlobalID"));
//                lot.setSize(lotJson.getInteger("size"));
//                lot.setQualityStage(lotJson.getInteger("qualityStage"));
//                // end
//                params.setApiUri("/api/lots/set");
//                params.setData(JSONObject.parseObject(JSONObject.toJSONString(lot)));
//                setLot(params);
//                log.info("new lot: {}", JSONObject.toJSONString(lot, true));
            }
            // start

//            if(Objects.nonNull(lotJson)) {
//                params.setApiUri("/api/lots/set");
//                lotJson.put("JobGUID", job.getGuid());
//                params.setData(lotJson);
//                Lot lot = setLot(params);
//            }
            // Lot end
            return job;
        }
        return null;
    }

    public Lot setLot(RequestDTO params) {
        Lot lot = JSONObject.toJavaObject(params.getData(), Lot.class);
        // Sample check
        JSONObject sampleJson = null;
        if(params.getData().containsKey("sample")) {
            sampleJson = params.getData().getJSONObject("sample");
        }
        // Samples check
        JSONObject samplesJson = null;
        if(params.getData().containsKey("samples")) {
            samplesJson = params.getData().getJSONObject("samples");
        }

        JSONObject postData = buildLotPostData(lot);
        params.setData(postData);
        log.info("LOT PARAMS {}", JSONObject.toJSONString(params, true));
        R<JSONObject> proxyRes = imApiRecordService.handle(params);
        log.info("LOT RES {}", JSONObject.toJSONString(proxyRes, true));
        if(proxyRes.getCode() == 0) {
            JSONObject res = proxyRes.getData().getJSONObject("OutputLot");
//            lot.setGuid(res.getString("GUID"));
////            lot.setPartGuid(res.getString("PartGUID"));
//
////            // Sample start
////            if(Objects.nonNull(sampleJson)) {
////                params.setApiUri("/api/samples/set");
////                sampleJson.put("LotGUID", lot.getGuid());
////                params.setData(sampleJson);
////                Sample sample = setSample(params);
////            }
////            // Sample end
////            // Samples start
////            if(Objects.nonNull(samplesJson)) {
////                params.setApiUri("/api/samples/createaqlsamples");
////                samplesJson.put("LotGUID", lot.getGuid());
////                samplesJson.put("StartFrom", 1);
////                samplesJson.put("Prefix", "#");
////                samplesJson.put("DimsToInspect", 0);
////                samplesJson.put("FirstFullSamples", 0);
////                samplesJson.put("LastFullSamples", 0);
////                params.setData(samplesJson);
////                Boolean isSuccess = setSamples(params);
////            }
////            // Samples end
//
//            lot.setGuid(res.getString("GUID"));
//            Lot data = lotService.lambdaQuery().eq(Lot::getGuid, lot.getGuid()).select(Lot::getId).one();
//            if(data == null) {
//                return null;
//            } else {
//                return lot;
//            }
            return lot;
        }
        return null;
    }

    public Sample setSample(RequestDTO params) {
        Sample sample = JSONObject.toJavaObject(params.getData(), Sample.class);

        JSONObject postData = buildSamplePostData(sample);
        params.setData(postData);

        R<JSONObject> proxyRes = imApiRecordService.handle(params);
//        log.info("SAMPLE SET RESULT {}", JSONObject.toJSONString(proxyRes, true));
        if(proxyRes.getCode() == 0) {
            JSONObject res = proxyRes.getData().getJSONObject("OutputSample");
            sample.setGuid(res.getString("GUID"));
//            Sample data = sampleService.lambdaQuery().eq(Sample::getGuid, sample.getGuid()).select(Sample::getId).one();
//            if(data == null) {
//                return null;
//            } else {
//                return sample;
//            }
            return sample;
        }
        return null;
    }

    public Boolean setSamples(RequestDTO params) {
        log.info("SAMPLES PARAMS {}", JSONObject.toJSONString(params, true));
        JSONObject paramData = params.getData();
        JSONObject postData = new JSONObject();
//        postData.put("JobGUID", paramData.getString("JobGUID"));

        postData.put("LotGUID", paramData.getString("LotGUID"));
        postData.put("StartFrom", paramData.getInteger("StartFrom"));
        postData.put("Prefix", paramData.getString("Prefix"));
        postData.put("DimsToInspect", paramData.getInteger("DimsToInspect"));
        postData.put("FirstFullSamples", paramData.getInteger("LastFullSamples"));
        postData.put("LastFullSamples", paramData.getInteger("LastFullSamples"));
//        postData.put("OperationGUIDs", paramData.getString("Operations"));
        postData.put("InspectLastSample", paramData.getBoolean("InspectLastSample"));


        params.setData(postData);

        R<JSONObject> proxyRes = imApiRecordService.handle(params);
        log.info("SAMPLES RES {}", JSONObject.toJSONString(proxyRes, true));
        if(proxyRes.getCode() == 0) {
            Boolean res = proxyRes.getData().getBoolean("IsSuccess");
            return res == null ? false : res;
        }
        return false;
    }

    public List<JSONObject> setResult(RequestDTO params, String sampleGUID, List<JSONObject> results) {
        JSONObject postData = buildResultPostData(results, sampleGUID);
        params.setData(postData);
        log.info("Result POST {}", JSONObject.toJSONString(params, true));
        R<JSONObject> proxyRes = imApiRecordService.handle(params);
        log.info("Result RES {}", JSONObject.toJSONString(proxyRes, true));
        if(proxyRes.getCode() == 0 && proxyRes.getData().getInteger("Count") > 0) {
//            for(Result result : results) {
//                if(Objects.nonNull(result.getId())) {
//                    resultService.updateById(result);
//                } else {
////                resultService.save(result);
//                }
//            }
            return results;
        }
        return null;
    }

    public Ncr setNcr(RequestDTO params) {
        Ncr ncr = JSONObject.toJavaObject(params.getData(), Ncr.class);

        JSONObject postData = buildNcrPostData(ncr);
        params.setData(postData);

        R<JSONObject> proxyRes = imApiRecordService.handle(params);
        if(proxyRes.getCode() == 0) {
            JSONObject res = proxyRes.getData().getJSONObject("OutputNCR");
            ncr.setGuid(res.getString("GUID"));
            return ncr;
//            Ncr data = ncrService.lambdaQuery().eq(Ncr::getGuid, ncr.getGuid()).select(Ncr::getId).one();
//            boolean result;
//            if(data == null) {
//                result = ncrService.save(ncr);
//            } else {
//                ncr.setId(data.getId());
//                result = ncrService.updateById(ncr);
//            }
//            if(result) {
//                return ncr;
//            }
        }
        return null;
    }
    /**
     * 转换Ncr对象
     * @param newData
     * @return
     */
    private JSONObject buildNcrPostData(Ncr newData) {
        JSONObject postData = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("GUID", StringUtils.isBlank(newData.getGuid()) ? "" : newData.getGuid());
        data.put("JobGUID", newData.getJobGuid());
        data.put("LotGUID", newData.getLotGuid());
        data.put("Number", newData.getNumber());
        data.put("Status", Objects.isNull(newData.getStatus()) ? 0 : newData.getStatus());
        data.put("CreationDate", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(newData.getCreationDate()) + TIME_SUFFIX);
        data.put("CreatedByGUID", StringUtils.isBlank(newData.getCreatedByGuid()) ? "" : newData.getGuid());
        data.put("ResponseDate", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(newData.getResponseDate()) + TIME_SUFFIX);
        data.put("AssignedToGUID", StringUtils.isBlank(newData.getAssignedToGuid()) ? "" : newData.getGuid());
        data.put("WorkCellGUID", StringUtils.isBlank(newData.getWorkCellGuid()) ? "" : newData.getGuid());
//        data.put("CavityNumber", Objects.isNull(newData.getCavityNumber()) ? 0 : newData.getCavityNumber());
        data.put("InspCenterGUID", StringUtils.isBlank(newData.getInspCenterGuid()) ? "" : newData.getInspCenterGuid());
//        data.put("FixtureNumber", StringUtils.isBlank(newData.getFixtureNumber()) ? "" : newData.getFixtureNumber());
//        data.put("Status_Text", newData.getStatusText());
//        data.put("CreationDate", newData.getCreationDate());
        data.put("Comments", StringUtils.isBlank(newData.getComments()) ? "" : newData.getComments());
        data.put("ERPID", StringUtils.isBlank(newData.getErpId()) ? "" : newData.getErpId());
        data.put("BarcodeID", StringUtils.isBlank(newData.getBarcodeId()) ? "" : newData.getBarcodeId());
        postData.put("InputNCR", data);
        return postData;
    }

    /**
     * 转换Sample对象
     * @param newData
     * @return
     */
    private JSONObject buildSamplePostData(Sample newData) {
        JSONObject postData = new JSONObject();
        // false不创建Result true创建
        postData.put("AddPlaceholders", false);
        JSONObject data = new JSONObject();
        data.put("GUID", StringUtils.isBlank(newData.getGuid()) ? "" : newData.getGuid());
        data.put("PartGUID", newData.getPartGuid());
        data.put("LotGUID", newData.getLotGuid());
        data.put("JobGUID", newData.getJobGuid());
        data.put("SerialNumber", newData.getSerialNumber());
        data.put("CavityNumber", Objects.isNull(newData.getCavityNumber()) ? 0 : newData.getCavityNumber());
        data.put("MachineNumber", StringUtils.isBlank(newData.getMachineNumber()) ? "" : newData.getMachineNumber());
        data.put("FixtureNumber", StringUtils.isBlank(newData.getFixtureNumber()) ? "" : newData.getFixtureNumber());
        data.put("Status", Objects.isNull(newData.getStatus()) ? 0 : newData.getStatus());
//        data.put("Status_Text", newData.getStatusText());
//        data.put("CreationDate", newData.getCreationDate());
//        data.put("Results", newData.getResults());
        data.put("ERPID", StringUtils.isBlank(newData.getErpId()) ? "" : newData.getErpId());
        data.put("BarcodeID", StringUtils.isBlank(newData.getBarcodeId()) ? "" : newData.getBarcodeId());
        postData.put("InputSample", data);
        return postData;
    }
    /**
     * 转换Lot对象
     * @param newData
     * @return
     */
    private JSONObject buildLotPostData(Lot newData) {
        JSONObject postData = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("GUID", StringUtils.isBlank(newData.getGuid()) ? "" : newData.getGuid());
        data.put("JobGUID", newData.getJobGuid());
        data.put("PartGUID", newData.getPartGuid());
        data.put("Number", newData.getNumber());
        data.put("Status", newData.getStatus());
//        data.put("StatusText", newData.getStatusText());
        data.put("StartDate", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(newData.getStartDate()) + TIME_SUFFIX);
        data.put("DueDate", newData.getDueDate() == null ? DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(newData.getStartDate()) + TIME_SUFFIX : DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(newData.getDueDate()) + TIME_SUFFIX);
        data.put("Size", newData.getSize());
//        data.put("TotalSamples", newData.getTotalSamples());
//        data.put("TotalFailedSamples", newData.getTotalFailedSamples());
        data.put("ERPID", StringUtils.isBlank(newData.getErpId()) ? "" : newData.getErpId());
        data.put("BarcodeID", StringUtils.isBlank(newData.getBarcodeId()) ? "" : newData.getBarcodeId());
        data.put("SamplesPerHour", newData.getSamplesPerHour());
        data.put("HoursPerShift", newData.getHoursPerShift());
        data.put("QualityStage", newData.getQualityStage());
//        data.put("QualityStageText", newData.getQualityStageText());
        data.put("QualityLevel", Objects.nonNull(newData.getQualityLevel()) ? newData.getQualityLevel() : 0.65);
        data.put("AQLTableGUID", StringUtils.isBlank(newData.getAqlTableGuid()) ? "" : newData.getAqlTableGuid());
        data.put("InspectionLevel", Objects.nonNull(newData.getInspectionLevel()) ? newData.getInspectionLevel() : 0);
//        data.put("InspectionLevelText", newData.getInspectionLevelText());
        data.put("CodeLetter", newData.getCodeLetter());
//        data.put("SampleSize", newData.getSampleSize());
        postData.put("InputLot", data);
        return postData;
    }
    /**
     * 转换Job对象
     * @param newData
     * @return
     */
    private JSONObject buildJobPostData(Job newData) {
        JSONObject postData = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("GUID", StringUtils.isBlank(newData.getGuid()) ? "" : newData.getGuid());
        data.put("PartGUID", newData.getPartGuid());
        data.put("Number", newData.getNumber());
        data.put("Title", newData.getTitle());
        data.put("Revision", StringUtils.isBlank(newData.getRevision()) ? "" : newData.getRevision());
        data.put("Quantity", newData.getQuantity());
        data.put("Status", newData.getStatus());
//        data.put("StatusText", newData.getStatusText());
        // "2022-10-31T00:00:00.0000000"
        data.put("ActivationDate", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(newData.getActivationDate()) + TIME_SUFFIX);
        data.put("DeliveryDate", newData.getDeliveryDate() == null ? "" : DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(newData.getDeliveryDate()) + TIME_SUFFIX);
//        data.put("TotalLots", newData.getTotalLots());
//        data.put("TotalFailedLots", newData.getTotalFailedLots());
//        data.put("TotalSamples", newData.getTotalSamples());
//        data.put("TotalFailedSamples", newData.getTotalFailedSamples());
        data.put("ERPID", StringUtils.isBlank(newData.getErpId()) ? "" : newData.getErpId());
        data.put("BarcodeID", StringUtils.isBlank(newData.getBarcodeId()) ? "" : newData.getBarcodeId());
        data.put("AQLMode", Objects.nonNull(newData.getAqlMode()) ? newData.getAqlMode() : 0);
//        data.put("AQLModeText", newData.getAqlModeText());
        data.put("AQLTableGUID", StringUtils.isBlank(newData.getAqlTableGuid()) ? "" : newData.getAqlTableGuid());
        data.put("InspectionLevel", Objects.nonNull(newData.getInspectionLevel()) ? newData.getInspectionLevel() : 0);
//        data.put("InspectionLevelText", newData.getInspectionLevelText());
        postData.put("InputJob", data);
        return postData;
    }

    /**
     * 转换Result对象
     * @param resultList
     * @param sampleGuid
     * @return
     */
    private JSONObject buildResultPostData(List<JSONObject> resultList, String sampleGuid) {
        JSONObject postData = new JSONObject();
        postData.put("SampleGUID", sampleGuid);
        postData.put("IgnoreInvalidLines", false);
        JSONArray results = new JSONArray();
        for(JSONObject newData : resultList) {
            JSONObject data = new JSONObject();
//        data.put("GUID", StringUtils.isBlank(newData.getGuid()) ? "" : newData.getGuid());
//        data.put("PartGUID", newData.getPartGuid());

            data.put("DimGUID", newData.getString("DimGUID"));
            data.put("GageGUID", newData.getString("CustomDimToolGUID"));
            data.put("ResNo", newData.getString("ActualCode"));
            data.put("Data", newData.getString("CustomActualData"));
            data.put("UpperTol", newData.getString("ActualUpperTol"));
            data.put("LowerTol", newData.getString("ActualLowerTol"));
//            data.put("Deviation", newData.getDeviation());
            data.put("Deviation", "");
            data.put("OutTol", "");
            data.put("Bonus", 0);
            data.put("Axis", "");
            data.put("Feature1", "");
            data.put("Feature2", "");
            data.put("MeasuredByGUID", "");
            data.put("Comment", StringUtils.isBlank(newData.getString("CustomComment")) ? "" : newData.getString("CustomComment"));
            data.put("Result", newData.getString("CustomActualResult"));
            data.put("NeedsCalculation", false);

            results.add(data);
        }
        postData.put("InputResults", results);
        return postData;
    }
}
