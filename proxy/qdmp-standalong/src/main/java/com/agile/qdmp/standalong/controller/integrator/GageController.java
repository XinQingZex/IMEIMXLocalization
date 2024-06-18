package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.GageConverter;
import com.agile.qdmp.standalong.model.dto.integrator.GageDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.GageQueryDTO;
import com.agile.qdmp.standalong.service.integrator.IGageService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.Gage;
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
 * GAGE 前端控制器
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:55
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "gage", tags = "GAGE管理")
public class GageController extends SuperController {

    private final IGageService gageService;

    public GageController(IGageService gageService) {
        this.gageService = gageService;
    }

    /**
     * 新增GAGE
     * @param gageDTO GAGE
     * @return
     */
    @ApiOperation(value = "新增GAGE", notes = "新增GAGE")
    @PostMapping("/gage")
    public R<GageDTO> createGage(@RequestBody GageDTO gageDTO) {
        log.debug("REST request to save Gage : {}", gageDTO);
        if (gageDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Gage newData = GageConverter.INSTANCE.from(gageDTO);
        Gage data = gageService.lambdaQuery().eq(Gage::getGuid, gageDTO.getGuid()).select(Gage::getId, Gage::getFlag).one();
        boolean result = true;
        if(data == null) {
            result = gageService.save(newData);
        } else {
            if(!data.getFlag().equalsIgnoreCase(gageDTO.getFlag())) {
                newData.setId(data.getId());
                newData.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                result = gageService.updateById(newData);
            }
        }
        if(result) {
            gageDTO.setId(data.getId());
            return R.ok(gageDTO, "添加成功");
        } else {
            return R.failed(gageDTO, "添加失败");
        }
    }

    /**
     * 修改GAGE
     * @param gageDTO GAGE
     * @return
     */
    @ApiOperation(value = "修改GAGE", notes = "修改GAGE")
    @PutMapping("/gage")
    public R<GageDTO> updateGage(@RequestBody GageDTO gageDTO) {
        log.debug("REST request to update Gage : {}", gageDTO);
        if (gageDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Gage data = GageConverter.INSTANCE.from(gageDTO);
        return gageService.updateById(data) ? R.ok(gageDTO, "修改成功") : R.failed(gageDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO GAGE
     * @return
     */
    @ApiOperation(value = "分页查询GAGE", notes = "分页查询GAGE")
    @GetMapping("/gages")
    public R<List<JSONObject>> getAllGages(GageQueryDTO queryDTO) {
        List<JSONObject> result = gageService.listByQuery(queryDTO, queryDTO.getCursor(), queryDTO.getLimit());
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取GAGE
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询GAGE", notes = "通过id查询GAGE")
    // @JsonFieldFilter(type = GageDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = GageDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = GageDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = GageDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/gage/{id}")
    public R<GageDTO> getGage(@PathVariable Long id) {
        log.debug("REST request to get Gage : {}", id);
        Gage gage = gageService.getById(id);
        return gage == null ? R.failed("未查询到数据") : R.ok(GageConverter.INSTANCE.to(gage), "查询成功");
    }

    /**
     * 通过id删除GAGE
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除GAGE", notes = "通过id删除GAGE")
    @DeleteMapping("/gage/{id}")
    public R deleteGage(@PathVariable Long id) {
        log.debug("REST request to delete Gages : {}", id);
        return gageService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
