package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.CompanyConverter;
import com.agile.qdmp.standalong.model.dto.integrator.CompanyDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.CompanyQueryDTO;
import com.agile.qdmp.standalong.service.integrator.ICompanyService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.Company;
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
 * COMPANY 前端控制器
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:59
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "company", tags = "COMPANY管理")
public class CompanyController extends SuperController {

    private final ICompanyService companyService;

    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * 新增COMPANY
     * @param companyDTO COMPANY
     * @return
     */
    @ApiOperation(value = "新增COMPANY", notes = "新增COMPANY")
    @PostMapping("/company")
    public R<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
        log.debug("REST request to save Company : {}", companyDTO);
        if (companyDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Company newData = CompanyConverter.INSTANCE.from(companyDTO);
        Company data = companyService.lambdaQuery().eq(Company::getGuid, companyDTO.getGuid()).select(Company::getId, Company::getFlag).one();
        boolean result = true;
        if(data == null) {
            result = companyService.save(newData);
        } else {
            if(!data.getFlag().equalsIgnoreCase(companyDTO.getFlag())) {
                newData.setId(data.getId());
                newData.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                result = companyService.updateById(newData);
            }
        }
        if(result) {
            companyDTO.setId(newData.getId());
            return R.ok(companyDTO, "添加成功");
        } else {
            return R.failed(companyDTO, "添加失败");
        }
    }

    /**
     * 修改COMPANY
     * @param companyDTO COMPANY
     * @return
     */
    @ApiOperation(value = "修改COMPANY", notes = "修改COMPANY")
    @PutMapping("/company")
    public R<CompanyDTO> updateCompany(@RequestBody CompanyDTO companyDTO) {
        log.debug("REST request to update Company : {}", companyDTO);
        if (companyDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Company data = CompanyConverter.INSTANCE.from(companyDTO);
        return companyService.updateById(data) ? R.ok(companyDTO, "修改成功") : R.failed(companyDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO COMPANY
     * @return
     */
    @ApiOperation(value = "分页查询COMPANY", notes = "分页查询COMPANY")
    // @JsonFieldFilter(type = CompanyDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = CompanyDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = CompanyDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = CompanyDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/companies")
    public R<List<JSONObject>> getAllCompanies(CompanyQueryDTO queryDTO) {
        log.debug("REST request to get a page of Companies {}", queryDTO);
        List<JSONObject> result = companyService.listByQuery(queryDTO);
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取COMPANY
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询COMPANY", notes = "通过id查询COMPANY")
    // @JsonFieldFilter(type = CompanyDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = CompanyDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = CompanyDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = CompanyDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/company/{id}")
    public R<CompanyDTO> getCompany(@PathVariable Long id) {
        log.debug("REST request to get Company : {}", id);
        Company company = companyService.getById(id);
        return company == null ? R.failed("未查询到数据") : R.ok(CompanyConverter.INSTANCE.to(company), "查询成功");
    }

    /**
     * 通过id删除COMPANY
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除COMPANY", notes = "通过id删除COMPANY")
    @DeleteMapping("/company/{id}")
    public R deleteCompany(@PathVariable Long id) {
        log.debug("REST request to delete Companys : {}", id);
        return companyService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
