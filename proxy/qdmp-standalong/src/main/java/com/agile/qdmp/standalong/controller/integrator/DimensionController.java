package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.DimensionConverter;
import com.agile.qdmp.standalong.model.dto.integrator.DimensionDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.DimensionQueryDTO;
import com.agile.qdmp.standalong.service.integrator.IDimensionService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.Dimension;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * DIMENSION 前端控制器
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:56
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "dimension", tags = "DIMENSION管理")
public class DimensionController extends SuperController {

    private final IDimensionService dimensionService;

    public DimensionController(IDimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    /**
     * 新增DIMENSION
     * @param dimensionDTO DIMENSION
     * @return
     */
    @ApiOperation(value = "新增DIMENSION", notes = "新增DIMENSION")
    @PostMapping("/dimension")
    public R<DimensionDTO> createDimension(@RequestBody DimensionDTO dimensionDTO) {
        log.debug("REST request to save Dimension : {}", dimensionDTO);
        if (dimensionDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Dimension newData = DimensionConverter.INSTANCE.from(dimensionDTO);
        Dimension data = dimensionService.lambdaQuery().eq(Dimension::getGuid, dimensionDTO.getGuid()).select(Dimension::getId).one();
        boolean result;
        if(data == null) {
            result = dimensionService.save(newData);
        } else {
            newData.setId(data.getId());
            result = dimensionService.updateById(newData);
        }
        if(result) {
            dimensionDTO.setId(newData.getId());
            return R.ok(dimensionDTO, "添加成功");
        } else {
            return R.failed(dimensionDTO, "添加失败");
        }
    }

    /**
     * 修改DIMENSION
     * @param dimensionDTO DIMENSION
     * @return
     */
    @ApiOperation(value = "修改DIMENSION", notes = "修改DIMENSION")
    @PutMapping("/dimension")
    public R<DimensionDTO> updateDimension(@RequestBody DimensionDTO dimensionDTO) {
        log.debug("REST request to update Dimension : {}", dimensionDTO);
        if (dimensionDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Dimension data = DimensionConverter.INSTANCE.from(dimensionDTO);
        return dimensionService.updateById(data) ? R.ok(dimensionDTO, "修改成功") : R.failed(dimensionDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO DIMENSION
     * @return
     */
    @ApiOperation(value = "分页查询DIMENSION", notes = "分页查询DIMENSION")
    // @JsonFieldFilter(type = DimensionDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = DimensionDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = DimensionDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = DimensionDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/dimensions")
    public R<IPage<DimensionDTO>> getAllDimensions(DimensionQueryDTO queryDTO) {
        log.debug("REST request to get a page of Dimensions");
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<DimensionDTO> result = dimensionService.lambdaQuery()
//                .like(StringUtils.isNotEmpty(queryDTO.getName()), Dimension::getName, queryDTO.getName())
                .eq(StringUtils.isNotEmpty(queryDTO.getOperationId()), Dimension::getOperationGuid, queryDTO.getOperationId())
                .eq(StringUtils.isNotEmpty(queryDTO.getDrawingGuid()), Dimension::getDrawingGuid, queryDTO.getDrawingGuid())
                .eq(Objects.nonNull(queryDTO.getCompanyId()), Dimension::getCompanyId, queryDTO.getCompanyId())
                .eq(Objects.nonNull(queryDTO.getServerId()), Dimension::getServerId, queryDTO.getServerId())
//                .eq(Objects.nonNull(queryDTO.getPartGuid()), Dimension::getPartGuid, queryDTO.getPartGuid())
                .page(this.<Dimension>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> DimensionConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 查询DIMENSION
     * @param queryDTO DIMENSION
     * @return
     */
    @ApiOperation(value = "查询DIMENSION", notes = "查询DIMENSION")
    @GetMapping("/dimension/list")
    public R<List<JSONObject>> listDimensions(DimensionQueryDTO queryDTO) {
        log.debug("REST request to get a list of Dimensions {}", queryDTO);
        List<JSONObject> list = dimensionService.listBySampleAndOperation(queryDTO.getSampleId(), queryDTO.getOperationId(), queryDTO.getProcedureId());
        return Objects.isNull(list) ? R.failed("暂无数据") : R.ok(list, "查询列表成功");
    }

    /**
     * 查询DIMENSION多个Sample重复
     * @param queryDTO DIMENSION
     * @return
     */
    @ApiOperation(value = "查询DIMENSION多个Sample重复", notes = "查询DIMENSION多个Sample重复")
    @PostMapping("/dimension/list/multi")
    public R<List<DimensionDTO>> listDimensionsBySample(@RequestBody DimensionQueryDTO queryDTO) {
        log.debug("REST request to get a list of Dimensions {}", queryDTO);
        List<Dimension> list = dimensionService.listBySample(queryDTO.getOperationId(), queryDTO.getSampleIds());
        return Objects.isNull(list) ? R.failed("暂无数据") : R.ok(DimensionConverter.INSTANCE.to(list), "查询列表成功");
    }

    /**
     * 根据ID获取DIMENSION
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询DIMENSION", notes = "通过id查询DIMENSION")
    // @JsonFieldFilter(type = DimensionDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = DimensionDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = DimensionDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = DimensionDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/dimension/{id}")
    public R<DimensionDTO> getDimension(@PathVariable Long id) {
        log.debug("REST request to get Dimension : {}", id);
        Dimension dimension = dimensionService.getById(id);
        return dimension == null ? R.failed("未查询到数据") : R.ok(DimensionConverter.INSTANCE.to(dimension), "查询成功");
    }

    /**
     * 根据GUID获取dimension
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "根据GUID获取dimension", notes = "根据GUID获取dimension")
    @PostMapping("/dimension/get")
    public R<DimensionDTO> getSample(@RequestBody DimensionQueryDTO queryDTO) {
        log.debug("REST request to get dimension : {}", queryDTO);
        Dimension dimension = dimensionService.lambdaQuery().eq(Dimension::getGuid, queryDTO.getGuid()).one();
        DimensionDTO result = null;
        if(dimension != null) {
            result = DimensionConverter.INSTANCE.to(dimension);
//            if(Objects.nonNull(queryDTO.getWithLot()) && queryDTO.getWithLot() && StringUtils.isNotBlank(sample.getLotGuid())) {
//                Lot lot = lotService.lambdaQuery().eq(Lot::getGuid, sample.getLotGuid()).one();
//                result.setLot(Objects.isNull(lot) ? null : LotConverter.INSTANCE.to(lot));
//            }
//            if(Objects.nonNull(queryDTO.getWithJob()) && queryDTO.getWithJob() && StringUtils.isNotBlank(sample.getJobGuid())) {
//                Job job = jobService.lambdaQuery().eq(Job::getGuid, sample.getJobGuid()).one();
//                result.setJob(Objects.isNull(job) ? null : JobConverter.INSTANCE.to(job));
//            }
//            if(Objects.nonNull(queryDTO.getWithPart()) && queryDTO.getWithPart() && StringUtils.isNotBlank(sample.getPartGuid())) {
//                Part part = partService.lambdaQuery().eq(Part::getGuid, sample.getPartGuid()).one();
//                result.setPart(Objects.isNull(part) ? null : PartConverter.INSTANCE.to(part));
//            }
        }
        return result == null ? R.failed("未查询到数据") : R.ok(result, "查询成功");
    }

    /**
     * 通过id删除DIMENSION
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除DIMENSION", notes = "通过id删除DIMENSION")
    @DeleteMapping("/dimension/{id}")
    public R deleteDimension(@PathVariable Long id) {
        log.debug("REST request to delete Dimensions : {}", id);
        return dimensionService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
