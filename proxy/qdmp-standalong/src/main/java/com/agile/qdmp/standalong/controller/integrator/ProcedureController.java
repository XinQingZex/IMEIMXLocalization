package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.ProcedureConverter;
import com.agile.qdmp.standalong.model.dto.integrator.ProcedureDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.ProcedureQueryDTO;
import com.agile.qdmp.standalong.service.integrator.IProcedureService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.Procedure;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * Procedure 前端控制器
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:46
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "procedure", tags = "Procedure管理")
public class ProcedureController extends SuperController {

    private final IProcedureService procedureService;

    public ProcedureController(IProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    /**
     * 新增Procedure
     * @param procedureDTO Procedure
     * @return
     */
    @ApiOperation(value = "新增Procedure", notes = "新增Procedure")
    @PostMapping("/procedure")
    public R<ProcedureDTO> createProcedure(@RequestBody ProcedureDTO procedureDTO) {
        log.debug("REST request to save Procedure : {}", procedureDTO);
        if (procedureDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Procedure newData = ProcedureConverter.INSTANCE.from(procedureDTO);
        Procedure data = procedureService.lambdaQuery().eq(Procedure::getGuid, procedureDTO.getGuid()).select(Procedure::getId, Procedure::getFlag).one();
        boolean result = true;
        if(data == null) {
            result = procedureService.save(newData);
        } else {
            if(!data.getFlag().equalsIgnoreCase(procedureDTO.getFlag())) {
                newData.setId(data.getId());
                newData.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                result = procedureService.updateById(newData);
            }
        }
        if(result) {
            procedureDTO.setId(newData.getId());
            return R.ok(procedureDTO, "添加成功");
        } else {
            return R.failed(procedureDTO, "添加失败");
        }
    }

    /**
     * 修改Procedure
     * @param procedureDTO Procedure
     * @return
     */
    @ApiOperation(value = "修改Procedure", notes = "修改Procedure")
    @PutMapping("/procedure")
    public R<ProcedureDTO> updateProcedure(@RequestBody ProcedureDTO procedureDTO) {
        log.debug("REST request to update Procedure : {}", procedureDTO);
        if (procedureDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Procedure data = ProcedureConverter.INSTANCE.from(procedureDTO);
        return procedureService.updateById(data) ? R.ok(procedureDTO, "修改成功") : R.failed(procedureDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO Procedure
     * @return
     */
    @ApiOperation(value = "分页查询Procedure", notes = "分页查询Procedure")
    // @JsonFieldFilter(type = ProcedureDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = ProcedureDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = ProcedureDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = ProcedureDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/procedures")
    public R<IPage<ProcedureDTO>> getAllProcedures(ProcedureQueryDTO queryDTO) {
        log.debug("REST request to get a page of Procedures");
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<ProcedureDTO> result = procedureService.lambdaQuery()
//                .or(StringUtils.isNotEmpty(queryDTO.getKeyWord()), o -> o.like(Procedure::getPartName, queryDTO.getKeyWord()).or().like(Procedure::getPartNumber, queryDTO.getKeyWord()).or())
//                .like(StringUtils.isNotEmpty(queryDTO.getName()), Procedure::getName, queryDTO.getName())
                .eq(StringUtils.isNotEmpty(queryDTO.getPartGuid()), Procedure::getPartGuid, queryDTO.getPartGuid())
                .eq(StringUtils.isNotEmpty(queryDTO.getInspCenterGuid()), Procedure::getInspCenterGuid, queryDTO.getInspCenterGuid())
                .eq(Objects.nonNull(queryDTO.getStatus()), Procedure::getStatus, queryDTO.getStatus())
                .eq(Objects.nonNull(queryDTO.getCompanyId()), Procedure::getCompanyId, queryDTO.getCompanyId())
                .eq(Objects.nonNull(queryDTO.getServerId()), Procedure::getServerId, queryDTO.getServerId())
                .page(this.<Procedure>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> ProcedureConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取Procedure
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询Procedure", notes = "通过id查询Procedure")
    // @JsonFieldFilter(type = ProcedureDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = ProcedureDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = ProcedureDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = ProcedureDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/procedure/{id}")
    public R<ProcedureDTO> getProcedure(@PathVariable Long id) {
        log.debug("REST request to get Procedure : {}", id);
        Procedure procedure = procedureService.getById(id);
        return procedure == null ? R.failed("未查询到数据") : R.ok(ProcedureConverter.INSTANCE.to(procedure), "查询成功");
    }

    /**
     * 通过id删除Procedure
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除Procedure", notes = "通过id删除Procedure")
    @DeleteMapping("/procedure/{id}")
    public R deleteProcedure(@PathVariable Long id) {
        log.debug("REST request to delete Procedures : {}", id);
        return procedureService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
