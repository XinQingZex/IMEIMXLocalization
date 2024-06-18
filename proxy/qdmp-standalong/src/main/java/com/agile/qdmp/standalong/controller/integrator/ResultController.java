package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.GageConverter;
import com.agile.qdmp.standalong.model.convert.integrator.LotConverter;
import com.agile.qdmp.standalong.model.convert.integrator.ResultConverter;
import com.agile.qdmp.standalong.model.convert.integrator.SampleConverter;
import com.agile.qdmp.standalong.model.dto.integrator.RequestDTO;
import com.agile.qdmp.standalong.model.dto.integrator.ResultCount;
import com.agile.qdmp.standalong.model.dto.integrator.ResultDTO;
import com.agile.qdmp.standalong.model.dto.integrator.SampleDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.ResultQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.*;
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

import java.util.*;


/**
 * RESULT 前端控制器
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:06
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "result", tags = "RESULT管理")
public class ResultController extends SuperController {

    private final IResultService resultService;
    private final IGageService gageService;
    private final ISampleService sampleService;
    private final ILotService lotService;
    private final INcrService ncrService;
    private final SetUtils setUtils;

    public ResultController(IResultService resultService, IGageService gageService, ISampleService sampleService, ILotService lotService, INcrService ncrService, SetUtils setUtils) {
        this.resultService = resultService;
        this.gageService = gageService;
        this.sampleService = sampleService;
        this.lotService = lotService;
        this.ncrService = ncrService;
        this.setUtils = setUtils;
    }

    /**
     * 新增或修改Result
     * @param params
     * @return
     */
    @ApiOperation(value = "新增或修改Result", notes = "新增或修改Result")
    @PostMapping("/result/set")
    public R<Map<String, List<JSONObject>>> setResult(@RequestBody RequestDTO params) {
        log.debug("REST request to set Result : {}", params);
        if (StringUtils.isBlank(params.getServerUri())) {
            throw new BizException("缺少Server Uri");
        }
        if (StringUtils.isBlank(params.getApiUri())) {
            throw new BizException("缺少Api Uri");
        }
        if (StringUtils.isBlank(params.getApiVersion())) {
            throw new BizException("缺少Api Version");
        }
        JSONArray results = params.getData().getJSONArray("InputResults");

        Map<String, List<JSONObject>> postData = new HashMap<>(10);
        for(int i =0 ; i < results.size(); i++) {
            JSONObject actualAndDimJson = results.getJSONObject(i);
            String sampleGuid = actualAndDimJson.getString("CustomSampleGUID");
            if(postData.containsKey(sampleGuid)) {
                postData.get(sampleGuid).add(actualAndDimJson);
            } else {
                List<JSONObject> resultList = new ArrayList<>();
                resultList.add(actualAndDimJson);
                postData.put(sampleGuid, resultList);
            }
        }
        for(Map.Entry<String, List<JSONObject>> entry : postData.entrySet()) {
            setUtils.setResult(params, entry.getKey(), entry.getValue());
        }
        return Objects.isNull(postData) ? R.failed("添加失败") : R.ok(postData, "添加成功");
    }

    /**
     * 新增RESULT
     * @param resultDTO RESULT
     * @return
     */
    @ApiOperation(value = "新增RESULT", notes = "新增RESULT")
    @PostMapping("/result")
    public R<ResultDTO> createResult(@RequestBody ResultDTO resultDTO) {
        log.debug("REST request to save Result : {}", resultDTO);
        if (resultDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Result newData = ResultConverter.INSTANCE.from(resultDTO);
        Result data = resultService.lambdaQuery().eq(Result::getGuid, resultDTO.getGuid()).select(Result::getId).one();
        boolean result;
        if(data == null) {
            result = resultService.save(newData);
        } else {
            newData.setId(data.getId());
            newData.setData(null);
            newData.setStatus(null);
            newData.setStatusText(null);
            result = resultService.updateById(newData);
        }

        if(result) {
            resultDTO.setId(newData.getId());
            return R.ok(resultDTO, "添加成功");
        } else {
            return R.failed(resultDTO, "添加失败");
        }
    }

    /**
     * 修改RESULT
     * @param resultDTO RESULT
     * @return
     */
    @ApiOperation(value = "修改RESULT", notes = "修改RESULT")
    @PutMapping("/result")
    public R<ResultDTO> updateResult(@RequestBody ResultDTO resultDTO) {
        log.debug("REST request to update Result : {}", resultDTO);
        if (resultDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Result data = ResultConverter.INSTANCE.from(resultDTO);
        return resultService.updateById(data) ? R.ok(resultDTO, "修改成功") : R.failed(resultDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO RESULT
     * @return
     */
    @ApiOperation(value = "分页查询RESULT", notes = "分页查询RESULT")
    // @JsonFieldFilter(type = ResultDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = ResultDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = ResultDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = ResultDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/results")
    public R<IPage<ResultDTO>> getAllResults(ResultQueryDTO queryDTO) {
        log.debug("REST request to get a page of Results");
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<ResultDTO> result = resultService.lambdaQuery()
                .like(StringUtils.isNotEmpty(queryDTO.getKeyWord()), Result::getResNo, queryDTO.getKeyWord())
                .eq(StringUtils.isNotEmpty(queryDTO.getDimGuid()), Result::getDimGuid, queryDTO.getDimGuid())
//                .eq(StringUtils.isNotEmpty(queryDTO.getSampleGuid()), Result::getSampleGuid, queryDTO.getSampleGuid())
                .eq(StringUtils.isNotEmpty(queryDTO.getJobId()), Result::getJobGuid, queryDTO.getJobId())
//                .eq(StringUtils.isNotEmpty(queryDTO.getPartGuid()), Result::getPartGuid, queryDTO.getPartGuid())
                .eq(Objects.nonNull(queryDTO.getServerId()), Result::getServerId, queryDTO.getServerId())
                .page(this.<Result>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> {
                    ResultDTO res = ResultConverter.INSTANCE.to(dto);
                    if(StringUtils.isNotBlank(res.getGageGuid())) {
                        Gage gage = gageService.lambdaQuery().eq(Gage::getGuid, res.getGageGuid()).one();
                        res.setGage(GageConverter.INSTANCE.to(gage));
                    }
                    if(Objects.nonNull(queryDTO.getWithSample()) && queryDTO.getWithSample() && StringUtils.isNotBlank(res.getSampleGuid())) {
                        Sample sample = sampleService.lambdaQuery().eq(Sample::getGuid, res.getSampleGuid()).one();
                        SampleDTO sampleDTO = SampleConverter.INSTANCE.to(sample);
                        if(Objects.nonNull(queryDTO.getWithLot()) && queryDTO.getWithLot()) {
                            Lot lot = lotService.lambdaQuery().eq(Lot::getGuid, sampleDTO.getLotGuid()).one();
                            sampleDTO.setLot(LotConverter.INSTANCE.to(lot));
                        }
                        res.setSample(sampleDTO);
                    }
                    return res;
                });
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取RESULT
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询RESULT", notes = "通过id查询RESULT")
    // @JsonFieldFilter(type = ResultDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = ResultDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = ResultDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = ResultDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/result/{id}")
    public R<ResultDTO> getResult(@PathVariable Long id) {
        log.debug("REST request to get Result : {}", id);
        Result result = resultService.getById(id);
        return result == null ? R.failed("未查询到数据") : R.ok(ResultConverter.INSTANCE.to(result), "查询成功");
    }

    @ApiOperation(value = "通过id查询RESULT", notes = "通过id查询RESULT")
    @PostMapping("/result/get")
    public R<ResultDTO> getResult(@RequestBody ResultQueryDTO queryDTO) {
        log.debug("REST request to get Result : {}", queryDTO);
        Result res = resultService.lambdaQuery().eq(Result::getGuid, queryDTO.getGuid()).one();
        ResultDTO result = null;
        if (result != null) {
            result = ResultConverter.INSTANCE.to(res);
//            if (Objects.nonNull(queryDTO.getWithPart()) && queryDTO.getWithPart() && StringUtils.isNotBlank(result.getPartGuid())) {
//                Part part = partService.lambdaQuery().eq(Part::getGuid, result.getPartGuid()).one();
//                result.setPart(Objects.isNull(part) ? null : PartConverter.INSTANCE.to(part));
//            }
        }
        return result == null ? R.failed("未查询到数据") : R.ok(result, "查询成功");
    }

    /**
     * 通过id删除RESULT
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除RESULT", notes = "通过id删除RESULT")
    @DeleteMapping("/result/{id}")
    public R deleteResult(@PathVariable Long id) {
        log.debug("REST request to delete Results : {}", id);
        return resultService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }

    /**
     * 统计Sample和Result结果
     * @param queryDTO SAMPLE
     * @return
     */
    @ApiOperation(value = "统计Sample和Result结果", notes = "统计Sample和Result结果")
    @GetMapping("/result/count")
    public R<Map<String, JSONObject>> countResults(ResultQueryDTO queryDTO) {
        List<JSONObject> data = resultService.customCount(queryDTO);

        Map<String, JSONObject> res = new HashMap<>(10);
        if(Objects.nonNull(data) && data.size() > 0) {
            // Result status 1 - 'PASS', 2 - 'FAIL', 3 - 'N/A', 4 - 'ACCEPT'
            for (JSONObject s : data) {
                String sampleId = s.getString("ID");
                String jk = s.getString("status");
                Integer jv = s.getInteger("total");
                JSONObject state;
                if (res.containsKey(sampleId)) {
                    state = res.get(sampleId);
                    if (state.containsKey(jk)) {
                        state.put(jk, jv + state.getInteger(jk));
                    } else {
                        state.put(jk, jv);
                    }
                } else {
                    state = new JSONObject();
                    state.put(jk, jv);
                    res.put(sampleId, state);
                }
            }
        }
        return R.ok(res, "查询列表成功");
    }

    /**
     * 根据Job统计Sample和Result结果(带Lot)
     * @param queryDTO SAMPLE
     * @return
     */
    @ApiOperation(value = "根据Job统计Sample和Result结果(带Lot)", notes = "根据Job统计Sample和Result结果(带Lot)")
    @GetMapping("/result/count/byJob")
    public R<List<JSONObject>> countResultsByJob(ResultQueryDTO queryDTO) {
        List<JSONObject> data = resultService.customCountByJob(queryDTO);
        return R.ok(data, "查询列表成功");
    }

//    /**
//     * 根据查询条件查询数目
//     * @param queryDTO SAMPLE
//     * @return
//     */
//    @ApiOperation(value = "根据查询条件查询数目", notes = "根据查询条件查询数目")
//    @GetMapping("/result/count/lotGuid")
//    public R<List<ResultCount>> countByLotGuid(ResultQueryDTO queryDTO) {
//        List<ResultCount> data = resultService.customCountByLotGuid(queryDTO.getLotGuid());
//        return R.ok(data, "查询列表成功");
//    }
}
