package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.NcrConverter;
import com.agile.qdmp.standalong.model.convert.integrator.NcrResultConverter;
import com.agile.qdmp.standalong.model.dto.integrator.NcrDTO;
import com.agile.qdmp.standalong.model.dto.integrator.RequestDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.NcrQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Ncr;
import com.agile.qdmp.standalong.model.entity.integrator.NcrResult;
import com.agile.qdmp.standalong.service.integrator.INcrResultService;
import com.agile.qdmp.standalong.service.integrator.INcrService;
import com.agile.qdmp.standalong.service.integrator.SetUtils;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;


/**
 * NCR 前端控制器
 *
 * @author hyzh code generator
 * @date 2022-11-28 14:18:46
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "ncr", tags = "NCR管理")
public class NcrController extends SuperController {

    private final INcrService ncrService;
    private final INcrResultService ncrResultService;
    private final SetUtils setUtils;

    public NcrController(INcrService ncrService, INcrResultService ncrResultService, SetUtils setUtils) {
        this.ncrService = ncrService;
        this.ncrResultService = ncrResultService;
        this.setUtils = setUtils;
    }

    /**
     * 新增或修改Ncr
     * @param params
     * @return
     */
    @ApiOperation(value = "新增或修改Ncr", notes = "新增或修改Ncr")
    @PostMapping("/ncr/set")
    public R<NcrDTO> setNcr(@RequestBody RequestDTO params) {
        log.debug("REST request to set Ncr : {}", params);
        if (StringUtils.isBlank(params.getServerUri())) {
            throw new BizException("缺少Server Uri");
        }
        if (StringUtils.isBlank(params.getApiUri())) {
            throw new BizException("缺少Api Uri");
        }
        if (StringUtils.isBlank(params.getApiVersion())) {
            throw new BizException("缺少Api Version");
        }
        Ncr newData = setUtils.setNcr(params);
        return Objects.isNull(newData) ? R.failed("添加失败") : R.ok(NcrConverter.INSTANCE.to(newData), "添加成功");
    }

    /**
     * 新增NCR
     * @param ncrDTO NCR
     * @return
     */
    @ApiOperation(value = "新增NCR", notes = "新增NCR")
    @PostMapping("/ncr")
    public R<NcrDTO> createNcr(@RequestBody NcrDTO ncrDTO) {
        log.debug("REST request to save Ncr : {}", ncrDTO);
        if (ncrDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Ncr data = NcrConverter.INSTANCE.from(ncrDTO);
        boolean result = ncrService.save(data);
        if(result) {
            ncrDTO.setId(data.getId());
            return R.ok(ncrDTO, "添加成功");
        } else {
            return R.failed(ncrDTO, "添加失败");
        }
    }

    /**
     * 修改NCR
     * @param ncrDTO NCR
     * @return
     */
    @ApiOperation(value = "修改NCR", notes = "修改NCR")
    @PutMapping("/ncr")
    public R<NcrDTO> updateNcr(@RequestBody NcrDTO ncrDTO) {
        log.debug("REST request to update Ncr : {}", ncrDTO);
        if (ncrDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Ncr data = NcrConverter.INSTANCE.from(ncrDTO);
        return ncrService.updateById(data) ? R.ok(ncrDTO, "修改成功") : R.failed(ncrDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO NCR
     * @return
     */
    @ApiOperation(value = "分页查询NCR", notes = "分页查询NCR")
    // @JsonFieldFilter(type = NcrDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = NcrDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = NcrDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = NcrDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/ncrs")
    public R<List<JSONObject>> getAllNcrs(NcrQueryDTO queryDTO) {
        log.debug("REST request to get a page of Ncrs {}", queryDTO);
        List<JSONObject> result = ncrService.listByQuery(queryDTO);
        for(JSONObject res : result) {
            List<JSONObject> records = ncrResultService.listById(res.getString("ID"));
            res.put("records", records);
        }
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取NCR
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询NCR", notes = "通过id查询NCR")
    // @JsonFieldFilter(type = NcrDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = NcrDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = NcrDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = NcrDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/ncr/{id}")
    public R<NcrDTO> getNcr(@PathVariable Long id) {
        log.debug("REST request to get Ncr : {}", id);
        Ncr ncr = ncrService.getById(id);
        return ncr == null ? R.failed("未查询到数据") : R.ok(NcrConverter.INSTANCE.to(ncr), "查询成功");
    }

    /**
     * 通过id删除NCR
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除NCR", notes = "通过id删除NCR")
    @DeleteMapping("/ncr/{id}")
    public R deleteNcr(@PathVariable Long id) {
        log.debug("REST request to delete Ncrs : {}", id);
        return ncrService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
