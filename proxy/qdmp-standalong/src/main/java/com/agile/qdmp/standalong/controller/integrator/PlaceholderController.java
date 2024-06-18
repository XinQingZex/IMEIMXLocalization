package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.PlaceholderConverter;
import com.agile.qdmp.standalong.model.dto.integrator.PlaceholderDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.PlaceholderQueryDTO;
import com.agile.qdmp.standalong.service.integrator.IPlaceholderService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.Placeholder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * Placeholder 前端控制器
 *
 * @author wenbinglei
 * @date 2022-10-21 11:48:14
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "placeholder", tags = "Placeholder管理")
public class PlaceholderController extends SuperController {

    private final IPlaceholderService placeholderService;

    public PlaceholderController(IPlaceholderService placeholderService) {
        this.placeholderService = placeholderService;
    }

    /**
     * 新增Placeholder
     * @param placeholderDTO Placeholder
     * @return
     */
    @ApiOperation(value = "新增Placeholder", notes = "新增Placeholder")
    @PostMapping("/placeholder")
    public R<PlaceholderDTO> createPlaceholder(@RequestBody PlaceholderDTO placeholderDTO) {
        log.debug("REST request to save Placeholder : {}", placeholderDTO);
        if (placeholderDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Placeholder newData = PlaceholderConverter.INSTANCE.from(placeholderDTO);
        Placeholder data = placeholderService.lambdaQuery().eq(Placeholder::getGuid, placeholderDTO.getGuid()).select(Placeholder::getId, Placeholder::getFlag).one();
        boolean result = true;
        if(data == null) {
            result = placeholderService.save(newData);
        } else {
            if(!data.getFlag().equalsIgnoreCase(placeholderDTO.getFlag())) {
                newData.setId(data.getId());
                newData.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                result = placeholderService.updateById(newData);
            }
        }
        if(result) {
            placeholderDTO.setId(newData.getId());
            return R.ok(placeholderDTO, "添加成功");
        } else {
            return R.failed(placeholderDTO, "添加失败");
        }
    }

    /**
     * 修改Placeholder
     * @param placeholderDTO Placeholder
     * @return
     */
    @ApiOperation(value = "修改Placeholder", notes = "修改Placeholder")
    @PutMapping("/placeholder")
    public R<PlaceholderDTO> updatePlaceholder(@RequestBody PlaceholderDTO placeholderDTO) {
        log.debug("REST request to update Placeholder : {}", placeholderDTO);
        if (placeholderDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Placeholder data = PlaceholderConverter.INSTANCE.from(placeholderDTO);
        return placeholderService.updateById(data) ? R.ok(placeholderDTO, "修改成功") : R.failed(placeholderDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO Placeholder
     * @return
     */
    @ApiOperation(value = "分页查询Placeholder", notes = "分页查询Placeholder")
    // @JsonFieldFilter(type = PlaceholderDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = PlaceholderDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = PlaceholderDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = PlaceholderDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/placeholders")
    public R<IPage<PlaceholderDTO>> getAllPlaceholders(PlaceholderQueryDTO queryDTO) {
        log.debug("REST request to get a page of Placeholders");
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<PlaceholderDTO> result = placeholderService.lambdaQuery()
//                .or(StringUtils.isNotEmpty(queryDTO.getKeyWord()), o -> o.like(Placeholder::getResNo, queryDTO.getKeyWord()).or().like(Placeholder::get, queryDTO.getKeyWord()).or())
                .like(StringUtils.isNotEmpty(queryDTO.getKeyWord()), Placeholder::getResNo, queryDTO.getKeyWord())
                .eq(StringUtils.isNotEmpty(queryDTO.getDimGuid()), Placeholder::getDimGuid, queryDTO.getDimGuid())
                .eq(StringUtils.isNotEmpty(queryDTO.getSampleGuid()), Placeholder::getSampleGuid, queryDTO.getSampleGuid())
                .eq(Objects.nonNull(queryDTO.getStatus()), Placeholder::getStatus, queryDTO.getStatus())
                .eq(Objects.nonNull(queryDTO.getCompanyId()), Placeholder::getCompanyId, queryDTO.getCompanyId())
                .eq(Objects.nonNull(queryDTO.getServerId()), Placeholder::getServerId, queryDTO.getServerId())
                .page(this.<Placeholder>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> PlaceholderConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取Placeholder
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询Placeholder", notes = "通过id查询Placeholder")
    // @JsonFieldFilter(type = PlaceholderDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = PlaceholderDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = PlaceholderDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = PlaceholderDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/placeholder/{id}")
    public R<PlaceholderDTO> getPlaceholder(@PathVariable Long id) {
        log.debug("REST request to get Placeholder : {}", id);
        Placeholder placeholder = placeholderService.getById(id);
        return placeholder == null ? R.failed("未查询到数据") : R.ok(PlaceholderConverter.INSTANCE.to(placeholder), "查询成功");
    }

    /**
     * 通过查询条件获取Placeholder
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "通过查询条件获取Placeholder", notes = "通过查询条件获取Placeholder")
    @PostMapping("/placeholder/get")
    public R<PlaceholderDTO> getPlaceholder(@RequestBody PlaceholderQueryDTO queryDTO) {
        log.debug("REST request to get Placeholder : {}", queryDTO);
        Placeholder placeholder = placeholderService.lambdaQuery().eq(Placeholder::getGuid, queryDTO.getGuid()).one();
        PlaceholderDTO result = null;
        if (placeholder != null) {
            result = PlaceholderConverter.INSTANCE.to(placeholder);
//            if (Objects.nonNull(queryDTO.getWithCompany()) && queryDTO.getWithCompany() && StringUtils.isNotBlank(part.getCustomerGuid())) {
//                Company company = companyService.lambdaQuery().eq(Company::getGuid, part.getCustomerGuid()).one();
//                result.setCompany(Objects.isNull(part) ? null : CompanyConverter.INSTANCE.to(company));
//            }
        }
        return result == null ? R.failed("未查询到数据") : R.ok(result, "查询成功");
    }

    /**
     * 通过id删除Placeholder
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除Placeholder", notes = "通过id删除Placeholder")
    @DeleteMapping("/placeholder/{id}")
    public R deletePlaceholder(@PathVariable Long id) {
        log.debug("REST request to delete Placeholders : {}", id);
        return placeholderService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
