package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.WorkCellConverter;
import com.agile.qdmp.standalong.model.dto.integrator.WorkCellDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.WorkCellQueryDTO;
import com.agile.qdmp.standalong.service.integrator.IWorkCellService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.WorkCell;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * WorkCell 前端控制器
 *
 * @author wenbinglei
 * @date 2022-10-21 11:48:07
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "workCell", tags = "WorkCell管理")
public class WorkCellController extends SuperController {

    private final IWorkCellService workCellService;

    public WorkCellController(IWorkCellService workCellService) {
        this.workCellService = workCellService;
    }

    /**
     * 新增WorkCell
     * @param workCellDTO WorkCell
     * @return
     */
    @ApiOperation(value = "新增WorkCell", notes = "新增WorkCell")
    @PostMapping("/workCell")
    public R<WorkCellDTO> createWorkCell(@RequestBody WorkCellDTO workCellDTO) {
        log.debug("REST request to save WorkCell : {}", workCellDTO);
        if (workCellDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        WorkCell newData = WorkCellConverter.INSTANCE.from(workCellDTO);
        WorkCell data = workCellService.lambdaQuery().eq(WorkCell::getGuid, workCellDTO.getGuid()).select(WorkCell::getId, WorkCell::getFlag).one();
        boolean result = true;
        if(data == null) {
            result = workCellService.save(newData);
        } else {
            if(!data.getFlag().equalsIgnoreCase(workCellDTO.getFlag())) {
                newData.setId(data.getId());
                newData.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                result = workCellService.updateById(newData);
            }
        }
        if(result) {
            workCellDTO.setId(newData.getId());
            return R.ok(workCellDTO, "添加成功");
        } else {
            return R.failed(workCellDTO, "添加失败");
        }
    }

    /**
     * 修改WorkCell
     * @param workCellDTO WorkCell
     * @return
     */
    @ApiOperation(value = "修改WorkCell", notes = "修改WorkCell")
    @PutMapping("/workCell")
    public R<WorkCellDTO> updateWorkCell(@RequestBody WorkCellDTO workCellDTO) {
        log.debug("REST request to update WorkCell : {}", workCellDTO);
        if (workCellDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        WorkCell data = WorkCellConverter.INSTANCE.from(workCellDTO);
        return workCellService.updateById(data) ? R.ok(workCellDTO, "修改成功") : R.failed(workCellDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO WorkCell
     * @return
     */
    @ApiOperation(value = "分页查询WorkCell", notes = "分页查询WorkCell")
    // @JsonFieldFilter(type = WorkCellDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = WorkCellDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = WorkCellDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = WorkCellDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/workCells")
    public R<IPage<WorkCellDTO>> getAllWorkCells(WorkCellQueryDTO queryDTO) {
        log.debug("REST request to get a page of WorkCells");
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<WorkCellDTO> result = workCellService.lambdaQuery()
                .like(StringUtils.isNotEmpty(queryDTO.getKeyWord()), WorkCell::getDescription, queryDTO.getKeyWord())
                .eq(StringUtils.isNotEmpty(queryDTO.getLocationGuid()), WorkCell::getLocationGuid, queryDTO.getLocationGuid())
                .eq(StringUtils.isNotEmpty(queryDTO.getPlaceGuid()), WorkCell::getPlaceGuid, queryDTO.getPlaceGuid())
                .eq(StringUtils.isNotEmpty(queryDTO.getCompanyGuid()), WorkCell::getCompanyGuid, queryDTO.getCompanyGuid())
                .eq(Objects.nonNull(queryDTO.getServerId()), WorkCell::getServerId, queryDTO.getServerId())
                .eq(Objects.nonNull(queryDTO.getCompanyId()), WorkCell::getCompanyId, queryDTO.getCompanyId())
                .page(this.<WorkCell>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> WorkCellConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取WorkCell
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询WorkCell", notes = "通过id查询WorkCell")
    // @JsonFieldFilter(type = WorkCellDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = WorkCellDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = WorkCellDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = WorkCellDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/workCell/{id}")
    public R<WorkCellDTO> getWorkCell(@PathVariable Long id) {
        log.debug("REST request to get WorkCell : {}", id);
        WorkCell workCell = workCellService.getById(id);
        return workCell == null ? R.failed("未查询到数据") : R.ok(WorkCellConverter.INSTANCE.to(workCell), "查询成功");
    }

    /**
     * 通过查询条件获取WorkCell
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "通过查询条件获取WorkCell", notes = "通过查询条件获取WorkCell")
    @PostMapping("/workCell/get")
    public R<WorkCellDTO> getWorkCell(@RequestBody WorkCellQueryDTO queryDTO) {
        log.debug("REST request to get WorkCell : {}", queryDTO);
        WorkCell workCell = workCellService.lambdaQuery().eq(WorkCell::getGuid, queryDTO.getGuid()).one();
        WorkCellDTO result = null;
        if (workCell != null) {
            result = WorkCellConverter.INSTANCE.to(workCell);
//            if (Objects.nonNull(queryDTO.getWithCompany()) && queryDTO.getWithCompany() && StringUtils.isNotBlank(part.getCustomerGuid())) {
//                Company company = companyService.lambdaQuery().eq(Company::getGuid, part.getCustomerGuid()).one();
//                result.setCompany(Objects.isNull(part) ? null : CompanyConverter.INSTANCE.to(company));
//            }
        }
        return result == null ? R.failed("未查询到数据") : R.ok(result, "查询成功");
    }

    /**
     * 通过id删除WorkCell
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除WorkCell", notes = "通过id删除WorkCell")
    @DeleteMapping("/workCell/{id}")
    public R deleteWorkCell(@PathVariable Long id) {
        log.debug("REST request to delete WorkCells : {}", id);
        return workCellService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
