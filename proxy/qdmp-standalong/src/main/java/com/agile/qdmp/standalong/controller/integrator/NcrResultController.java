package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.NcrResultConverter;
import com.agile.qdmp.standalong.model.dto.integrator.NcrResultDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.NcrResultQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.NcrResult;
import com.agile.qdmp.standalong.service.integrator.INcrResultService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * NCR_RESULT 前端控制器
 *
 * @author hyzh code generator
 * @date 2022-12-18 14:33:57
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "ncrResult", tags = "NCR_RESULT管理")
public class NcrResultController extends SuperController {

    private final INcrResultService ncrResultService;

    public NcrResultController(INcrResultService ncrResultService) {
        this.ncrResultService = ncrResultService;
    }

    /**
     * 新增NCR_RESULT
     * @param ncrResultDTO NCR_RESULT
     * @return
     */
    @ApiOperation(value = "新增NCR_RESULT", notes = "新增NCR_RESULT")
    @PostMapping("/ncrResult")
    public R<NcrResultDTO> createNcrResult(@RequestBody NcrResultDTO ncrResultDTO) {
        log.debug("REST request to save NcrResult : {}", ncrResultDTO);
        if (ncrResultDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        NcrResult data = NcrResultConverter.INSTANCE.from(ncrResultDTO);
        boolean result = ncrResultService.save(data);
        if(result) {
            ncrResultDTO.setId(data.getId());
            return R.ok(ncrResultDTO, "添加成功");
        } else {
            return R.failed(ncrResultDTO, "添加失败");
        }
    }

    /**
     * 修改NCR_RESULT
     * @param ncrResultDTO NCR_RESULT
     * @return
     */
    @ApiOperation(value = "修改NCR_RESULT", notes = "修改NCR_RESULT")
    @PutMapping("/ncrResult")
    public R<NcrResultDTO> updateNcrResult(@RequestBody NcrResultDTO ncrResultDTO) {
        log.debug("REST request to update NcrResult : {}", ncrResultDTO);
        if (ncrResultDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        NcrResult data = NcrResultConverter.INSTANCE.from(ncrResultDTO);
        return ncrResultService.updateById(data) ? R.ok(ncrResultDTO, "修改成功") : R.failed(ncrResultDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO NCR_RESULT
     * @return
     */
    @ApiOperation(value = "分页查询NCR_RESULT", notes = "分页查询NCR_RESULT")
    // @JsonFieldFilter(type = NcrResultDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = NcrResultDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = NcrResultDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = NcrResultDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/ncrResults")
    public R<IPage<NcrResultDTO>> getAllNcrResults(NcrResultQueryDTO queryDTO) {
        log.debug("REST request to get a page of NcrResults");
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<NcrResultDTO> result = ncrResultService.lambdaQuery()
//                .like(StringUtils.isNotEmpty(queryDTO.getName()), NcrResult::getName, queryDTO.getName())
                .eq(StringUtils.isNotEmpty(queryDTO.getNcrGuid()), NcrResult::getNcrGuid, queryDTO.getNcrGuid())
//                .eq(Objects.nonNull(queryDTO.getAccountId()), NcrResult::getAccountId, queryDTO.getAccountId())
                .page(this.<NcrResult>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> NcrResultConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取NCR_RESULT
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询NCR_RESULT", notes = "通过id查询NCR_RESULT")
    // @JsonFieldFilter(type = NcrResultDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = NcrResultDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = NcrResultDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = NcrResultDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/ncrResult/{id}")
    public R<NcrResultDTO> getNcrResult(@PathVariable Long id) {
        log.debug("REST request to get NcrResult : {}", id);
        NcrResult ncrResult = ncrResultService.getById(id);
        return ncrResult == null ? R.failed("未查询到数据") : R.ok(NcrResultConverter.INSTANCE.to(ncrResult), "查询成功");
    }

    /**
     * 通过id删除NCR_RESULT
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除NCR_RESULT", notes = "通过id删除NCR_RESULT")
    @DeleteMapping("/ncrResult/{id}")
    public R deleteNcrResult(@PathVariable Long id) {
        log.debug("REST request to delete NcrResults : {}", id);
        return ncrResultService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
