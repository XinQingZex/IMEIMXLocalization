package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.*;
import com.agile.qdmp.standalong.model.dto.integrator.*;
import com.agile.qdmp.standalong.model.dto.integrator.query.SampleQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.*;
import com.agile.qdmp.standalong.model.enums.CommonConstants;
import com.agile.qdmp.standalong.service.integrator.*;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;


/**
 * SAMPLE 前端控制器
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:00
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "sample", tags = "SAMPLE管理")
public class SampleController extends SuperController {

    private final ISampleService sampleService;
    private final ILotService lotService;
    private final IJobService jobService;
    private final IPartService partService;
    private final IResultService resultService;
    private final IDimensionService dimensionService;
    private final IImApiRecordService imApiRecordService;
    private final SetUtils setUtils;

    public SampleController(ISampleService sampleService, ILotService lotService, IJobService jobService, IPartService partService, IResultService resultService, IDimensionService dimensionService, IImApiRecordService imApiRecordService, SetUtils setUtils) {
        this.sampleService = sampleService;
        this.lotService = lotService;
        this.jobService = jobService;
        this.partService = partService;
        this.resultService = resultService;
        this.dimensionService = dimensionService;
        this.imApiRecordService = imApiRecordService;
        this.setUtils = setUtils;
    }

    /**
     * 新增或修改SAMPLE(AQL)
     * @param params
     * @return
     */
    @ApiOperation(value = "新增或修改SAMPLE(AQL)", notes = "新增或修改SAMPLE(AQL)")
    @PostMapping("/sample/set/aql")
    public R<Boolean> setSampleAQL(@RequestBody RequestDTO params) {
        log.debug("REST request to set Sample(AQL) : {}", params);
        if (StringUtils.isBlank(params.getServerUri())) {
            throw new BizException("缺少Server Uri");
        }
        if (StringUtils.isBlank(params.getApiUri())) {
            throw new BizException("缺少Api Uri");
        }
        if (StringUtils.isBlank(params.getApiVersion())) {
            throw new BizException("缺少Api Version");
        }

        Boolean newData = setUtils.setSamples(params);
        return newData ? R.ok(Boolean.TRUE, "添加成功") : R.failed("添加失败");
    }

    /**
     * 批量新增或修改SAMPLE
     * @param params
     * @return
     */
    @ApiOperation(value = "批量新增或修改SAMPLE", notes = "批量新增或修改SAMPLE")
    @PostMapping("/sample/sets")
    public R<Boolean> setSamples(@RequestBody RequestDTO params) {
        log.debug("REST request to set Samples : {}", params);
        if (Objects.isNull(params.getData().getJSONArray("samples"))) {
            throw new BizException("缺少样品数据");
        }
        if (StringUtils.isBlank(params.getApiUri())) {
            throw new BizException("缺少Api Uri");
        }
        if (StringUtils.isBlank(params.getApiVersion())) {
            throw new BizException("缺少Api Version");
        }
        String sampleApiUri = params.getApiUri();
        JSONArray samples = params.getData().getJSONArray("samples");
        JSONArray operations = samples.getJSONObject(0).getJSONArray("operations");
        for(int i = 0; i < samples.size(); i++) {
            params.setApiUri(sampleApiUri);
            params.setData(samples.getJSONObject(i));
            Sample newData = setUtils.setSample(params);
            if(Objects.nonNull(newData) && StringUtils.isNotBlank(newData.getGuid())) {
                buildNewResults(operations, params, newData);
            }
        }
        return R.ok(Boolean.TRUE, "添加成功");
    }

    /**
     * 新增或修改SAMPLE
     * @param params
     * @return
     */
    @ApiOperation(value = "新增或修改SAMPLE", notes = "新增或修改SAMPLE")
    @PostMapping("/sample/set")
    public R<SampleDTO> setSample(@RequestBody RequestDTO params) {
        log.debug("REST request to set Sample : {}", params);
        if (StringUtils.isBlank(params.getServerUri())) {
            throw new BizException("缺少Server Uri");
        }
        if (StringUtils.isBlank(params.getApiUri())) {
            throw new BizException("缺少Api Uri");
        }
        if (StringUtils.isBlank(params.getApiVersion())) {
            throw new BizException("缺少Api Version");
        }
        JSONObject paramData = params.getData();
        JSONArray operations = paramData.getJSONArray("operations");
        // 更新状态使用
        if(paramData.containsKey("id")) {
            Sample currentSample = sampleService.getById(paramData.getLong("id"));
            currentSample.setStatus(paramData.getInteger("status"));
            params.setData(JSONObject.parseObject(JSONObject.toJSONString(currentSample)));
        }

        // 先添加样品，再添加Result
        Sample newData = setUtils.setSample(params);
        if(Objects.nonNull(operations) && operations.size() > 0 && Objects.nonNull(newData) && StringUtils.isNotBlank(newData.getGuid())) {
            if(buildNewResults(operations, params, newData)) {
                return R.ok(SampleConverter.INSTANCE.to(newData));
            } else {
                return R.failed();
            }
        }
        return R.ok();
    }

    /**
     * 创建新的Result
     * @param operations
     * @param params
     * @param newData
     */
    private Boolean buildNewResults(JSONArray operations, RequestDTO params, Sample newData) {
        List<Dimension> dimensions = dimensionService.lambdaQuery().in(Dimension::getOperationGuid, operations).list();
        List<Result> results = new ArrayList<>();
        for(Dimension dimension : dimensions) {
            Result result = new Result();
            result.setSampleGuid(newData.getGuid());
            result.setDimGuid(dimension.getGuid());
            result.setGageGuid("");
            result.setResNo(dimension.getDimNo());
            result.setData(BigDecimal.ZERO);
            result.setUpperTol(dimension.getUpperTol());
            result.setLowerTol(dimension.getLowerTol());
            result.setDeviation(BigDecimal.ZERO);
            //            result.setOutTol("0");
            //            result.setBonus("0");
            //            result.setAxis("0");
            //            result.setMeasuredByGUID("0");
            result.setStatus(0);
            results.add(result);
        }
//            log.info("results {}", JSONObject.toJSONString(results, true));
        if(results.size() > 0) {
            params.setApiUri("/api/results/bulkload");
//            if(setUtils.setResult(params, newData.getGuid(), results).size() > 0) {
////                JSONObject resultParams = new JSONObject();
////                resultParams.put("SampleGUID", newData.getGuid());
////                resultParams.put("Status", 0);
////                imApiRecordService.synchronizeServerData(params.getServerId(), params.getServerUri(), params.getToken(),CommonConstants.RESULT_STR, resultParams, true, newData.getGuid());
//                JSONObject lotParams = new JSONObject();
//                lotParams.put("LotGUIDs", newData.getLotGuid());
//                imApiRecordService.synchronizeServerData(params.getServerId(), params.getServerUri(), params.getToken(), CommonConstants.LOT_STR, lotParams, true, newData.getGuid());
//
//                JSONObject jobParams = new JSONObject();
//                lotParams.put("JobGUIDs", newData.getJobGuid());
//                imApiRecordService.synchronizeServerData(params.getServerId(), params.getServerUri(), params.getToken(), CommonConstants.JOB_STR, jobParams, true, newData.getGuid());
//            }
            return true;
        }
        return false;
    }

    /**
     * 新增SAMPLE
     * @param sampleDTO SAMPLE
     * @return
     */
    @ApiOperation(value = "新增SAMPLE", notes = "新增SAMPLE")
    @PostMapping("/sample")
    public R<SampleDTO> createSample(@RequestBody SampleDTO sampleDTO) {
        log.debug("REST request to save Sample : {}", sampleDTO);
        if (sampleDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Sample newData = SampleConverter.INSTANCE.from(sampleDTO);
        Sample data = sampleService.lambdaQuery().eq(Sample::getGuid, sampleDTO.getGuid()).select(Sample::getId).one();
        boolean result;
        if(data == null) {
            result = sampleService.save(newData);
        } else {
            newData.setId(data.getId());
            result = sampleService.updateById(newData);
        }
        if(result) {
            sampleDTO.setId(newData.getId());
            return R.ok(sampleDTO, "添加成功");
        } else {
            return R.failed(sampleDTO, "添加失败");
        }
    }

    /**
     * 修改SAMPLE
     * @param sampleDTO SAMPLE
     * @return
     */
    @ApiOperation(value = "修改SAMPLE", notes = "修改SAMPLE")
    @PutMapping("/sample")
    public R<SampleDTO> updateSample(@RequestBody SampleDTO sampleDTO) {
        log.debug("REST request to update Sample : {}", sampleDTO);
        if (sampleDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Sample data = SampleConverter.INSTANCE.from(sampleDTO);
        return sampleService.updateById(data) ? R.ok(sampleDTO, "修改成功") : R.failed(sampleDTO, "修改失败");
    }

//    /**
//     * 根据查询条件获取分页数据
//     * @param queryDTO SAMPLE
//     * @return
//     */
//    @ApiOperation(value = "分页查询SAMPLE", notes = "分页查询SAMPLE")
//    @GetMapping("/samples")
//    public R<IPage<SampleDTO>> getAllSamples(SampleQueryDTO queryDTO) {
//        log.debug("REST request to get a page of Samples");
//        String[] ascParameters = null;
//        String[] descParameters = null;
//        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
//            ascParameters = new String[] {queryDTO.getAscParameter()};
//        }
//        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
//            descParameters = new String[] {queryDTO.getDescParameter()};
//        }
//
////        List<Operation> operations = new ArrayList<>();
////        if(Objects.nonNull(queryDTO.getWithDimCount()) && queryDTO.getWithDimCount() && StringUtils.isNotBlank(queryDTO.getPartGuid())) {
//////            operations = operationService.lambdaQuery().eq(Operation::getPartGuid, queryDTO.getPartGuid()).select(Operation::getGuid, Operation::getDimCount).list();
////            operations = operationService.lambdaQuery().eq(Operation::getPartGuid, queryDTO.getPartGuid()).list();
////        }
////        final List<OperationDTO> operList = OperationConverter.INSTANCE.to(operations);
//
//        IPage<SampleDTO> result = sampleService.lambdaQuery()
//                .like(StringUtils.isNotEmpty(queryDTO.getKeyWord()), Sample::getSerialNumber, queryDTO.getKeyWord())
//                .eq(StringUtils.isNotEmpty(queryDTO.getLotGuid()), Sample::getLotGuid, queryDTO.getLotGuid())
//                .eq(StringUtils.isNotEmpty(queryDTO.getJobGuid()), Sample::getJobGuid, queryDTO.getJobGuid())
//                .eq(StringUtils.isNotEmpty(queryDTO.getPartGuid()), Sample::getPartGuid, queryDTO.getPartGuid())
//                .eq(Objects.nonNull(queryDTO.getStatus()), Sample::getStatus, queryDTO.getStatus())
//                .eq(Objects.nonNull(queryDTO.getServerId()), Sample::getServerId, queryDTO.getServerId())
//                .ge(Objects.nonNull(queryDTO.getStartDate()), Sample::getCreationDate, queryDTO.getStartDate())
//                .le(Objects.nonNull(queryDTO.getEndDate()), Sample::getCreationDate, queryDTO.getEndDate())
//                .page(this.<Sample>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
//                .convert(data -> {
//                    SampleDTO dto =  SampleConverter.INSTANCE.to(data);
//
//                    List<ResultCount> resultCount = resultService.customCountBySampleGuid(dto.getGuid());
//                    dto.setResultCount(resultCount);
////                    dto.setDimCountList(operList);
//                    return dto;
//                });
//        return R.ok(result, "查询列表成功");
//    }

    /**
     * 根据ID获取SAMPLE
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询SAMPLE", notes = "通过id查询SAMPLE")
    @GetMapping("/sample/{id}")
    public R<SampleDTO> getSample(@PathVariable Long id) {
        log.debug("REST request to get Sample : {}", id);
        Sample sample = sampleService.getById(id);
        return sample == null ? R.failed("未查询到数据") : R.ok(SampleConverter.INSTANCE.to(sample), "查询成功");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO SAMPLE
     * @return
     */
    @ApiOperation(value = "分页查询SAMPLE", notes = "分页查询SAMPLE")
    @GetMapping("/samples")
    public R<List<JSONObject>> getAllSamples(SampleQueryDTO queryDTO) {
        log.debug("REST request to get a page of Samples {}", queryDTO);
        List<JSONObject> result = sampleService.listByQuery(queryDTO);
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取SAMPLE
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "通过id查询SAMPLE", notes = "通过id查询SAMPLE")
    @PostMapping("/sample/get")
    public R<JSONObject> getSample(@RequestBody SampleQueryDTO queryDTO) {
        log.debug("REST request to get Sample : {}", queryDTO);
        JSONObject sample = sampleService.getBySampleId(queryDTO.getSampleId());
        if(sample != null) {
            if(Objects.nonNull(queryDTO.getWithLot()) && queryDTO.getWithLot()) {
                JSONObject lot = lotService.getByLotId(sample.getString("PartInstanceLotID"));
                sample.put("lot", Objects.isNull(lot) ? null : lot);
            }
            if(Objects.nonNull(queryDTO.getWithJob()) && queryDTO.getWithJob()) {
                JSONObject job = jobService.getByJobId(sample.getString("PartInstanceJobID"));
                sample.put("job", Objects.isNull(job) ? null : job);
            }
            if(Objects.nonNull(queryDTO.getWithPart()) && queryDTO.getWithPart()) {
                JSONObject part = partService.getByPartId(sample.getString("PartInstancePartID"));
                sample.put("part", Objects.isNull(part) ? null : part);
            }
        }
        return sample == null ? R.failed("未查询到数据") : R.ok(sample, "查询成功");
    }

    /**
     * 根据GUID获取SAMPLES
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "通过id查询SAMPLE", notes = "通过id查询SAMPLE")
    @PostMapping("/sample/get/multi")
    public R<List<SampleDTO>> getSamples(@RequestBody SampleQueryDTO queryDTO) {
        log.debug("REST request to get Samples : {}", queryDTO);
        List<Sample> samples = sampleService.lambdaQuery().in(Sample::getGuid, queryDTO.getGuids()).list();
        List<SampleDTO> res = new ArrayList<>();
        for(Sample sample : samples) {
            SampleDTO result = SampleConverter.INSTANCE.to(sample);
            if(Objects.nonNull(queryDTO.getWithLot()) && queryDTO.getWithLot() && StringUtils.isNotBlank(sample.getLotGuid())) {
                Lot lot = lotService.lambdaQuery().eq(Lot::getGuid, sample.getLotGuid()).one();
                result.setLot(Objects.isNull(lot) ? null : LotConverter.INSTANCE.to(lot));
            }
            if(Objects.nonNull(queryDTO.getWithJob()) && queryDTO.getWithJob() && StringUtils.isNotBlank(sample.getJobGuid())) {
                Job job = jobService.lambdaQuery().eq(Job::getGuid, sample.getJobGuid()).one();
                result.setJob(Objects.isNull(job) ? null : JobConverter.INSTANCE.to(job));
            }
            if(Objects.nonNull(queryDTO.getWithPart()) && queryDTO.getWithPart() && StringUtils.isNotBlank(sample.getPartGuid())) {
                Part part = partService.lambdaQuery().eq(Part::getGuid, sample.getPartGuid()).one();
                result.setPart(Objects.isNull(part) ? null : PartConverter.INSTANCE.to(part));
            }
            res.add(result);
        }
        return samples == null || samples.size() == 0 ? R.failed("未查询到数据") : R.ok(res, "查询成功");
    }

    /**
     * 根据查询条件查询数目
     * @param queryDTO SAMPLE
     * @return
     */
    @ApiOperation(value = "根据查询条件查询数目", notes = "根据查询条件查询数目")
    @GetMapping("/sample/count/jobGuid")
    public R<List<SampleCount>> countByJobGuid(SampleQueryDTO queryDTO) {
        List<SampleCount> res = sampleService.customCountByJobGuid(queryDTO.getJobGuid());
        return R.ok(res, "查询列表成功");
    }

    /**
     * 根据Part查询
     * @param queryDTO SAMPLE
     * @return
     */
    @ApiOperation(value = "根据Part查询", notes = "根据Part查询")
    @GetMapping("/sample/count/byPart")
    public R<List<JSONObject>> countResults(SampleQueryDTO queryDTO) {
        List<JSONObject> data = sampleService.customCountByPart(queryDTO);
        return R.ok(data, "查询列表成功");
    }

    /**
     * 通过id删除SAMPLE
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除SAMPLE", notes = "通过id删除SAMPLE")
    @DeleteMapping("/sample/{id}")
    public R deleteSample(@PathVariable Long id) {
        log.debug("REST request to delete Samples : {}", id);
        return sampleService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
