package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.GageCategoryConverter;
import com.agile.qdmp.standalong.model.dto.integrator.GageCategoryDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.GageCategoryQueryDTO;
import com.agile.qdmp.standalong.service.integrator.IGageCategoryService;
import com.agile.qdmp.standalong.service.integrator.IGageService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.Gage;
import com.agile.qdmp.standalong.model.entity.integrator.GageCategory;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


/**
 * GAGE_CATEGORY 前端控制器
 *
 * @author wenbinglei
 * @date 2022-10-21 14:06:08
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "gageCategory", tags = "GAGE_CATEGORY管理")
public class GageCategoryController extends SuperController {

    private final IGageCategoryService gageCategoryService;
    private final IGageService gageService;

    public GageCategoryController(IGageCategoryService gageCategoryService, IGageService gageService) {
        this.gageCategoryService = gageCategoryService;
        this.gageService = gageService;
    }

    /**
     * 新增GAGE_CATEGORY
     * @param gageCategoryDTO GAGE_CATEGORY
     * @return
     */
    @ApiOperation(value = "新增GAGE_CATEGORY", notes = "新增GAGE_CATEGORY")
    @PostMapping("/gageCategory")
    public R<GageCategoryDTO> createGageCategory(@RequestBody GageCategoryDTO gageCategoryDTO) {
        log.debug("REST request to save GageCategory : {}", gageCategoryDTO);
        if (gageCategoryDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        GageCategory data = GageCategoryConverter.INSTANCE.from(gageCategoryDTO);
        boolean result = gageCategoryService.save(data);
        if(result) {
            gageCategoryDTO.setId(data.getId());
            return R.ok(gageCategoryDTO, "添加成功");
        } else {
            return R.failed(gageCategoryDTO, "添加失败");
        }
    }


    /**
     * 修改GAGE_CATEGORY
     * @param gageCategoryDTO GAGE_CATEGORY
     * @return
     */
    @ApiOperation(value = "修改GAGE_CATEGORY", notes = "修改GAGE_CATEGORY")
    @PutMapping("/gageCategory")
    public R<GageCategoryDTO> updateGageCategory(@RequestBody GageCategoryDTO gageCategoryDTO) {
        log.debug("REST request to update GageCategory : {}", gageCategoryDTO);
        if (gageCategoryDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        GageCategory data = GageCategoryConverter.INSTANCE.from(gageCategoryDTO);
        return gageCategoryService.updateById(data) ? R.ok(gageCategoryDTO, "修改成功") : R.failed(gageCategoryDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO GAGE_CATEGORY
     * @return
     */
    @ApiOperation(value = "分页查询GAGE_CATEGORY", notes = "分页查询GAGE_CATEGORY")
    @GetMapping("/gageCategories")
    public R<List<JSONObject>> getAllGageCategories(GageCategoryQueryDTO queryDTO) {
        List<JSONObject> result = gageCategoryService.listByQuery(queryDTO);
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取GAGE_CATEGORY
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询GAGE_CATEGORY", notes = "通过id查询GAGE_CATEGORY")
    // @JsonFieldFilter(type = GageCategoryDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = GageCategoryDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = GageCategoryDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = GageCategoryDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/gageCategory/{id}")
    public R<GageCategoryDTO> getGageCategory(@PathVariable Long id) {
        log.debug("REST request to get GageCategory : {}", id);
        GageCategory gageCategory = gageCategoryService.getById(id);
        return gageCategory == null ? R.failed("未查询到数据") : R.ok(GageCategoryConverter.INSTANCE.to(gageCategory), "查询成功");
    }

    /**
     * 通过id删除GAGE_CATEGORY
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除GAGE_CATEGORY", notes = "通过id删除GAGE_CATEGORY")
    @DeleteMapping("/gageCategory/{id}")
    public R deleteGageCategory(@PathVariable Long id) {
        log.debug("REST request to delete GageCategorys : {}", id);
        return gageCategoryService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
