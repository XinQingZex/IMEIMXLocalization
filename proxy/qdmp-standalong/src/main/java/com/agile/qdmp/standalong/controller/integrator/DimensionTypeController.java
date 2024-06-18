package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.DimensionTypeConverter;
import com.agile.qdmp.standalong.model.dto.integrator.DimensionTypeDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.DimensionTypeQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.DimensionType;
import com.agile.qdmp.standalong.service.integrator.IDimensionTypeService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * DimensionType 前端控制器
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:59
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "dimensionType", tags = "DimensionType管理")
public class DimensionTypeController extends SuperController {

    private final IDimensionTypeService dimensionTypeService;

    public DimensionTypeController(IDimensionTypeService dimensionTypeService) {
        this.dimensionTypeService = dimensionTypeService;
    }

    /**
     * 新增DimensionType
     * @param dimensionTypeDTO DimensionType
     * @return
     */
    @ApiOperation(value = "新增DimensionType", notes = "新增DimensionType")
    @PostMapping("/dimensionType")
    public R<DimensionTypeDTO> createDimensionType(@RequestBody DimensionTypeDTO dimensionTypeDTO) {
        log.debug("REST request to save DimensionType : {}", dimensionTypeDTO);
        if (dimensionTypeDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        DimensionType newData = DimensionTypeConverter.INSTANCE.from(dimensionTypeDTO);
        DimensionType data = dimensionTypeService.lambdaQuery().eq(DimensionType::getGuid, dimensionTypeDTO.getGuid()).one();
        boolean result = true;
        if(data == null) {
            result = dimensionTypeService.save(newData);
        } else {
//            if(!data.getGuid().equalsIgnoreCase(dimensionTypeDTO.getGuid())) {
//                newData.setId(data.getId());
//                newData.setUpdateTime((int) (System.currentTimeMillis() / 1000));
//                result = dimensionTypeService.updateById(newData);
//            }
        }
        if(result) {
            dimensionTypeDTO.setId(newData.getId());
            return R.ok(dimensionTypeDTO, "添加成功");
        } else {
            return R.failed(dimensionTypeDTO, "添加失败");
        }
    }

    /**
     * 修改DimensionType
     * @param dimensionTypeDTO DimensionType
     * @return
     */
    @ApiOperation(value = "修改DimensionType", notes = "修改DimensionType")
    @PutMapping("/dimensionType")
    public R<DimensionTypeDTO> updateDimensionType(@RequestBody DimensionTypeDTO dimensionTypeDTO) {
        log.debug("REST request to update DimensionType : {}", dimensionTypeDTO);
        if (dimensionTypeDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        DimensionType data = DimensionTypeConverter.INSTANCE.from(dimensionTypeDTO);
        return dimensionTypeService.updateById(data) ? R.ok(dimensionTypeDTO, "修改成功") : R.failed(dimensionTypeDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO DimensionType
     * @return
     */
    @ApiOperation(value = "分页查询DimensionType", notes = "分页查询DimensionType")
    // @JsonFieldFilter(type = DimensionTypeDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = DimensionTypeDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = DimensionTypeDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = DimensionTypeDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/dimensionTypes")
    public R<IPage<DimensionTypeDTO>> getAllDimensionTypes(DimensionTypeQueryDTO queryDTO) {
        log.debug("REST request to get a page of Companies {}", queryDTO);
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<DimensionTypeDTO> result = dimensionTypeService.lambdaQuery()
//                .or(StringUtils.isNotEmpty(queryDTO.getKeyWord()), o -> o.like(DimensionType::getName, queryDTO.getKeyWord()).or().like(DimensionType::getName, queryDTO.getKeyWord()).or())
                .like(StringUtils.isNotEmpty(queryDTO.getKeyWord()), DimensionType::getName, queryDTO.getKeyWord())
                .eq(StringUtils.isNotEmpty(queryDTO.getPartGuid()), DimensionType::getPartGuid, queryDTO.getPartGuid())
                .eq(Objects.nonNull(queryDTO.getServerId()), DimensionType::getServerId, queryDTO.getServerId())
                .page(this.<DimensionType>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> DimensionTypeConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取DimensionType
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询DimensionType", notes = "通过id查询DimensionType")
    // @JsonFieldFilter(type = DimensionTypeDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = DimensionTypeDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = DimensionTypeDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = DimensionTypeDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/dimensionType/{id}")
    public R<DimensionTypeDTO> getDimensionType(@PathVariable Long id) {
        log.debug("REST request to get DimensionType : {}", id);
        DimensionType dimensionType = dimensionTypeService.getById(id);
        return dimensionType == null ? R.failed("未查询到数据") : R.ok(DimensionTypeConverter.INSTANCE.to(dimensionType), "查询成功");
    }

    /**
     * 通过id删除DimensionType
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除DimensionType", notes = "通过id删除DimensionType")
    @DeleteMapping("/dimensionType/{id}")
    public R deleteDimensionType(@PathVariable Long id) {
        log.debug("REST request to delete DimensionTypes : {}", id);
        return dimensionTypeService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
