package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.OperationConverter;
import com.agile.qdmp.standalong.model.dto.integrator.OperationDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.OperationQueryDTO;
import com.agile.qdmp.standalong.service.integrator.IOperationService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.Operation;
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
 * Operation 前端控制器
 *
 * @author wenbinglei
 * @date 2022-10-21 11:47:53
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "operation", tags = "Operation管理")
public class OperationController extends SuperController {

    private final IOperationService operationService;

    public OperationController(IOperationService operationService) {
        this.operationService = operationService;
    }

    /**
     * 新增Operation
     * @param operationDTO Operation
     * @return
     */
    @ApiOperation(value = "新增Operation", notes = "新增Operation")
    @PostMapping("/operation")
    public R<OperationDTO> createOperation(@RequestBody OperationDTO operationDTO) {
        log.debug("REST request to save Operation : {}", operationDTO);
        if (operationDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Operation newData = OperationConverter.INSTANCE.from(operationDTO);
        Operation data = operationService.lambdaQuery().eq(Operation::getGuid, operationDTO.getGuid()).select(Operation::getId, Operation::getFlag).one();
        boolean result = true;
        if(data == null) {
            result = operationService.save(newData);
        } else {
            if(!data.getFlag().equalsIgnoreCase(operationDTO.getFlag())) {
                newData.setId(data.getId());
                newData.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                result = operationService.updateById(newData);
            }
        }
        if(result) {
            operationDTO.setId(newData.getId());
            return R.ok(operationDTO, "添加成功");
        } else {
            return R.failed(operationDTO, "添加失败");
        }
    }

    /**
     * 修改Operation
     * @param operationDTO Operation
     * @return
     */
    @ApiOperation(value = "修改Operation", notes = "修改Operation")
    @PutMapping("/operation")
    public R<OperationDTO> updateOperation(@RequestBody OperationDTO operationDTO) {
        log.debug("REST request to update Operation : {}", operationDTO);
        if (operationDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Operation data = OperationConverter.INSTANCE.from(operationDTO);
        return operationService.updateById(data) ? R.ok(operationDTO, "修改成功") : R.failed(operationDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO Operation
     * @return
     */
    @ApiOperation(value = "分页查询Operation", notes = "分页查询Operation")
    // @JsonFieldFilter(type = OperationDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = OperationDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = OperationDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = OperationDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/operations")
    public R<List<JSONObject>> getAllOperations(OperationQueryDTO queryDTO) {
        log.debug("REST request to get a page of Operations {}", queryDTO);
        List<JSONObject> result = operationService.listByQuery(queryDTO);
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取Operation
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询Operation", notes = "通过id查询Operation")
    // @JsonFieldFilter(type = OperationDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = OperationDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = OperationDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = OperationDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/operation/{id}")
    public R<OperationDTO> getOperation(@PathVariable Long id) {
        log.debug("REST request to get Operation : {}", id);
        Operation operation = operationService.getById(id);
        return operation == null ? R.failed("未查询到数据") : R.ok(OperationConverter.INSTANCE.to(operation), "查询成功");
    }

    /**
     * 通过查询条件获取Operation
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "通过查询条件获取Operation", notes = "通过查询条件获取Operation")
    @PostMapping("/operation/get")
    public R<OperationDTO> getOperation(@RequestBody OperationQueryDTO queryDTO) {
        log.debug("REST request to get Operation : {}", queryDTO);
        Operation operation = operationService.lambdaQuery().eq(Operation::getGuid, queryDTO.getGuid()).one();
        OperationDTO result = null;
        if (operation != null) {
            result = OperationConverter.INSTANCE.to(operation);
//            if (Objects.nonNull(queryDTO.getWithCompany()) && queryDTO.getWithCompany() && StringUtils.isNotBlank(part.getCustomerGuid())) {
//                Company company = companyService.lambdaQuery().eq(Company::getGuid, part.getCustomerGuid()).one();
//                result.setCompany(Objects.isNull(part) ? null : CompanyConverter.INSTANCE.to(company));
//            }
        }
        return result == null ? R.failed("未查询到数据") : R.ok(result, "查询成功");
    }
    /**
     * 通过id删除Operation
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除Operation", notes = "通过id删除Operation")
    @DeleteMapping("/operation/{id}")
    public R deleteOperation(@PathVariable Long id) {
        log.debug("REST request to delete Operations : {}", id);
        return operationService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
