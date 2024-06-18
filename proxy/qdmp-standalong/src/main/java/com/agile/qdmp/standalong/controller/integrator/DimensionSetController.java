package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.DimensionSetConverter;
import com.agile.qdmp.standalong.model.dto.integrator.DimensionSetDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.DimensionSetQueryDTO;
import com.agile.qdmp.standalong.service.integrator.IDimensionSetService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.DimensionSet;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * DIMENSION_SET 前端控制器
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:51
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "dimensionSet", tags = "DIMENSION_SET管理")
public class DimensionSetController extends SuperController {

    private final IDimensionSetService dimensionSetService;

    public DimensionSetController(IDimensionSetService dimensionSetService) {
        this.dimensionSetService = dimensionSetService;
    }

    /**
     * 新增DIMENSION_SET
     * @param dimensionSetDTO DIMENSION_SET
     * @return
     */
    @ApiOperation(value = "新增DIMENSION_SET", notes = "新增DIMENSION_SET")
    @PostMapping("/dimensionSet")
    public R<DimensionSetDTO> createDimensionSet(@RequestBody DimensionSetDTO dimensionSetDTO) {
        log.debug("REST request to save DimensionSet : {}", dimensionSetDTO);
        if (dimensionSetDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        DimensionSet data = DimensionSetConverter.INSTANCE.from(dimensionSetDTO);
        boolean result = dimensionSetService.save(data);
        if(result) {
            dimensionSetDTO.setId(data.getId());
            return R.ok(dimensionSetDTO, "添加成功");
        } else {
            return R.failed(dimensionSetDTO, "添加失败");
        }
    }

    /**
     * 修改DIMENSION_SET
     * @param dimensionSetDTO DIMENSION_SET
     * @return
     */
    @ApiOperation(value = "修改DIMENSION_SET", notes = "修改DIMENSION_SET")
    @PutMapping("/dimensionSet")
    public R<DimensionSetDTO> updateDimensionSet(@RequestBody DimensionSetDTO dimensionSetDTO) {
        log.debug("REST request to update DimensionSet : {}", dimensionSetDTO);
        if (dimensionSetDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        DimensionSet data = DimensionSetConverter.INSTANCE.from(dimensionSetDTO);
        return dimensionSetService.updateById(data) ? R.ok(dimensionSetDTO, "修改成功") : R.failed(dimensionSetDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO DIMENSION_SET
     * @return
     */
    @ApiOperation(value = "分页查询DIMENSION_SET", notes = "分页查询DIMENSION_SET")
    // @JsonFieldFilter(type = DimensionSetDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = DimensionSetDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = DimensionSetDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = DimensionSetDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/dimensionSets")
    public R<IPage<DimensionSetDTO>> getAllDimensionSets(DimensionSetQueryDTO queryDTO) {
        log.debug("REST request to get a page of DimensionSets");
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<DimensionSetDTO> result = dimensionSetService.lambdaQuery()
//                .like(StringUtils.isNotEmpty(queryDTO.getName()), DimensionSet::getName, queryDTO.getName())
//                .eq(StringUtils.isNotEmpty(queryDTO.getClassName()), DimensionSet::getClassName, queryDTO.getClassName())
//                .eq(Objects.nonNull(queryDTO.getAccountId()), DimensionSet::getAccountId, queryDTO.getAccountId())
                .page(this.<DimensionSet>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> DimensionSetConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取DIMENSION_SET
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询DIMENSION_SET", notes = "通过id查询DIMENSION_SET")
    // @JsonFieldFilter(type = DimensionSetDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = DimensionSetDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = DimensionSetDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = DimensionSetDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/dimensionSet/{id}")
    public R<DimensionSetDTO> getDimensionSet(@PathVariable Long id) {
        log.debug("REST request to get DimensionSet : {}", id);
        DimensionSet dimensionSet = dimensionSetService.getById(id);
        return dimensionSet == null ? R.failed("未查询到数据") : R.ok(DimensionSetConverter.INSTANCE.to(dimensionSet), "查询成功");
    }

    /**
     * 通过id删除DIMENSION_SET
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除DIMENSION_SET", notes = "通过id删除DIMENSION_SET")
    @DeleteMapping("/dimensionSet/{id}")
    public R deleteDimensionSet(@PathVariable Long id) {
        log.debug("REST request to delete DimensionSets : {}", id);
        return dimensionSetService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
