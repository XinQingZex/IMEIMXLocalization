package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.InspCenterConverter;
import com.agile.qdmp.standalong.model.dto.integrator.InspCenterDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.InspCenterQueryDTO;
import com.agile.qdmp.standalong.service.integrator.IInspCenterService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.InspCenter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * InspCenters 前端控制器
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:50
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "inspCenter", tags = "InspCenters管理")
public class InspCenterController extends SuperController {

    private final IInspCenterService inspCenterService;

    public InspCenterController(IInspCenterService inspCenterService) {
        this.inspCenterService = inspCenterService;
    }

    /**
     * 新增InspCenters
     * @param inspCenterDTO InspCenters
     * @return
     */
    @ApiOperation(value = "新增InspCenters", notes = "新增InspCenters")
    @PostMapping("/inspCenter")
    public R<InspCenterDTO> createInspCenter(@RequestBody InspCenterDTO inspCenterDTO) {
        log.debug("REST request to save InspCenter : {}", inspCenterDTO);
        if (inspCenterDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        InspCenter newData = InspCenterConverter.INSTANCE.from(inspCenterDTO);
        InspCenter data = inspCenterService.lambdaQuery().eq(InspCenter::getInspCenterId, inspCenterDTO.getInspCenterId()).select(InspCenter::getId, InspCenter::getFlag).one();
        boolean result = true;
        if(data == null) {
            result = inspCenterService.save(newData);
        } else {
            if(!data.getFlag().equalsIgnoreCase(inspCenterDTO.getFlag())) {
                newData.setId(data.getId());
                newData.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                result = inspCenterService.updateById(newData);
            }
        }
        if(result) {
            inspCenterDTO.setId(newData.getId());
            return R.ok(inspCenterDTO, "添加成功");
        } else {
            return R.failed(inspCenterDTO, "添加失败");
        }
    }

    /**
     * 修改InspCenters
     * @param inspCenterDTO InspCenters
     * @return
     */
    @ApiOperation(value = "修改InspCenters", notes = "修改InspCenters")
    @PutMapping("/inspCenter")
    public R<InspCenterDTO> updateInspCenter(@RequestBody InspCenterDTO inspCenterDTO) {
        log.debug("REST request to update InspCenter : {}", inspCenterDTO);
        if (inspCenterDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        InspCenter data = InspCenterConverter.INSTANCE.from(inspCenterDTO);
        return inspCenterService.updateById(data) ? R.ok(inspCenterDTO, "修改成功") : R.failed(inspCenterDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO InspCenters
     * @return
     */
    @ApiOperation(value = "分页查询InspCenters", notes = "分页查询InspCenters")
    // @JsonFieldFilter(type = InspCenterDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = InspCenterDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = InspCenterDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = InspCenterDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/inspCenters")
    public R<IPage<InspCenterDTO>> getAllInspCenters(InspCenterQueryDTO queryDTO) {
        log.debug("REST request to get a page of InspCenters");
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<InspCenterDTO> result = inspCenterService.lambdaQuery()
                .like(StringUtils.isNotEmpty(queryDTO.getKeyWord()), InspCenter::getName, queryDTO.getKeyWord())
//                .eq(StringUtils.isNotEmpty(queryDTO.getClassName()), InspCenter::getClassName, queryDTO.getClassName())
                .eq(Objects.nonNull(queryDTO.getCompanyId()), InspCenter::getCompanyId, queryDTO.getCompanyId())
                .eq(Objects.nonNull(queryDTO.getServerId()), InspCenter::getServerId, queryDTO.getServerId())
                .page(this.<InspCenter>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> InspCenterConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取InspCenters
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询InspCenters", notes = "通过id查询InspCenters")
    // @JsonFieldFilter(type = InspCenterDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = InspCenterDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = InspCenterDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = InspCenterDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/inspCenter/{id}")
    public R<InspCenterDTO> getInspCenter(@PathVariable Long id) {
        log.debug("REST request to get InspCenter : {}", id);
        InspCenter inspCenter = inspCenterService.getById(id);
        return inspCenter == null ? R.failed("未查询到数据") : R.ok(InspCenterConverter.INSTANCE.to(inspCenter), "查询成功");
    }

    /**
     * 通过id删除InspCenters
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除InspCenters", notes = "通过id删除InspCenters")
    @DeleteMapping("/inspCenter/{id}")
    public R deleteInspCenter(@PathVariable Long id) {
        log.debug("REST request to delete InspCenters : {}", id);
        return inspCenterService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
