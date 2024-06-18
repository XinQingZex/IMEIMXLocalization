package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.CompanyConverter;
import com.agile.qdmp.standalong.model.convert.integrator.DrawingConverter;
import com.agile.qdmp.standalong.model.convert.integrator.PartConverter;
import com.agile.qdmp.standalong.model.dto.integrator.FullLotDTO;
import com.agile.qdmp.standalong.model.dto.integrator.PartDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.PartQueryDTO;
import com.agile.qdmp.standalong.service.integrator.ICompanyService;
import com.agile.qdmp.standalong.service.integrator.IDrawingService;
import com.agile.qdmp.standalong.service.integrator.IPartService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.Company;
import com.agile.qdmp.standalong.model.entity.integrator.Drawing;
import com.agile.qdmp.standalong.model.entity.integrator.Part;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * PART 前端控制器
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:11
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "part", tags = "PART管理")
public class PartController extends SuperController {

    private final IPartService partService;
    private final ICompanyService companyService;
    private final IDrawingService drawingService;

    public PartController(IPartService partService, ICompanyService companyService, IDrawingService drawingService) {
        this.partService = partService;
        this.companyService = companyService;
        this.drawingService = drawingService;
    }

    /**
     * 新增PART
     * @param partDTO PART
     * @return
     */
    @ApiOperation(value = "新增PART", notes = "新增PART")
    @PostMapping("/part")
    public R<PartDTO> createPart(@RequestBody PartDTO partDTO) {
        log.debug("REST request to save Part : {}", partDTO);
        if (partDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Part newData = PartConverter.INSTANCE.from(partDTO);
        Part data = partService.lambdaQuery().eq(Part::getGuid, partDTO.getGuid()).select(Part::getId, Part::getFlag).one();
        boolean result = true;
        if(data == null) {
            result = partService.save(newData);
        } else {
            if(!data.getFlag().equalsIgnoreCase(partDTO.getFlag())) {
                newData.setId(data.getId());
                newData.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                result = partService.updateById(newData);
            }
        }
        if(result) {
            partDTO.setId(newData.getId());
            return R.ok(partDTO, "添加成功");
        } else {
            return R.failed(partDTO, "添加失败");
        }
    }

    /**
     * 修改PART
     * @param partDTO PART
     * @return
     */
    @ApiOperation(value = "修改PART", notes = "修改PART")
    @PutMapping("/part")
    public R<PartDTO> updatePart(@RequestBody PartDTO partDTO) {
        log.debug("REST request to update Part : {}", partDTO);
        if (partDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Part data = PartConverter.INSTANCE.from(partDTO);
        return partService.updateById(data) ? R.ok(partDTO, "修改成功") : R.failed(partDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO PART
     * @return
     */
    @ApiOperation(value = "分页查询PART", notes = "分页查询PART")
    @GetMapping("/parts/list")
    public R<List<JSONObject>> getPartsList(PartQueryDTO queryDTO) {
        log.debug("REST request to get list of Parts {}", queryDTO);
        List<JSONObject> parts = partService.listByQuery(queryDTO);
        return R.ok(parts, "查询列表成功");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO PART
     * @return
     */
    @ApiOperation(value = "分页查询PART", notes = "分页查询PART")
    @GetMapping("/parts/search/drawing")
    public R<List<JSONObject>> searchPartsListWithDrawing(PartQueryDTO queryDTO) {
        log.debug("REST request to get list of Parts {}", queryDTO);
//        Page page = this.getPage(Integer.parseInt(queryDTO.getCursor().toString()), Integer.parseInt(queryDTO.getLimit().toString()), true, null, null);
        List<JSONObject> parts = partService.searchWithDrawingByQuery(queryDTO, queryDTO.getCursor(), queryDTO.getLimit());
        return R.ok(parts, "查询列表成功");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO PART
     * @return
     */
    @ApiOperation(value = "分页查询PART", notes = "分页查询PART")
    // @JsonFieldFilter(type = PartDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = PartDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = PartDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = PartDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/parts")
    public R<IPage<PartDTO>> getAllParts(PartQueryDTO queryDTO) {
        log.debug("REST request to get a page of Parts {}", queryDTO);
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<PartDTO> result = partService.lambdaQuery()
                .or(StringUtils.isNotEmpty(queryDTO.getKeyWord()), o -> o.like(Part::getPartName, queryDTO.getKeyWord()).or().like(Part::getPartNumber, queryDTO.getKeyWord()).or())
//                .like(StringUtils.isNotEmpty(queryDTO.getKeyWord()), Part::getPartName, queryDTO.getKeyWord())
                .eq(StringUtils.isNotEmpty(queryDTO.getGuid()), Part::getGuid, queryDTO.getGuid())
//                .eq(StringUtils.isNotEmpty(queryDTO.getCustomerGuid()), Part::getCustomerGuid, queryDTO.getCustomerGuid())
                .eq(Objects.nonNull(queryDTO.getCompanyId()), Part::getCompanyId, queryDTO.getCompanyId())
                .eq(Objects.nonNull(queryDTO.getServerId()), Part::getServerId, queryDTO.getServerId())
                .eq(Part::getIsDeleted, 0)
                .page(this.<Part>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> {
                    PartDTO res = PartConverter.INSTANCE.to(dto);
                    if (Objects.nonNull(queryDTO.getWithCompany()) && queryDTO.getWithCompany() && StringUtils.isNotBlank(dto.getCustomerGuid())) {
                        Company company = companyService.lambdaQuery().eq(Company::getGuid, dto.getCustomerGuid()).one();
                        res.setCompany(Objects.isNull(company) ? null : CompanyConverter.INSTANCE.to(company));
                    }
                    if (Objects.nonNull(queryDTO.getWithDrawing()) && queryDTO.getWithDrawing()) {
                        List<Drawing> ds = drawingService.lambdaQuery().eq(Drawing::getPartGuid, dto.getGuid()).list();
                        res.setDrawings(DrawingConverter.INSTANCE.to(ds));
                    }
                    return res;
                });
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据InspCenter查询条件获取分页数据
     * @param queryDTO PART
     * @return
     */
    @ApiOperation(value = "根据InspCenter查询条件获取分页数据", notes = "根据InspCenter查询条件获取分页数据")
    @GetMapping("/parts/byInspCenter")
    public R<Collection<FullLotDTO>> getPartsByInspCenter(PartQueryDTO queryDTO) {
        log.debug("REST request to get list of Parts {}", queryDTO);
        List<FullLotDTO> result = partService.listByInspCenterGuidQuery(queryDTO);
        // TODO 根据LOT_GUID去重处理
        if(result != null && result.size() > 0) {
            Map<String, FullLotDTO> finalRes = new HashMap<>();
            for(FullLotDTO dto : result) {
                finalRes.put(dto.getLotGuid(), dto);
            }

            return R.ok(finalRes.values(), "查询列表成功");
        }

        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取PART
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询PART", notes = "通过id查询PART")
    @GetMapping("/part/{id}")
    public R<JSONObject> getPart(@PathVariable String id) {
        log.debug("REST request to get Part : {}", id);
        JSONObject part = partService.getByPartId(id);
        return part == null ? R.failed("未查询到数据") : R.ok(part, "查询成功");
    }
    /**
     * 通过查询条件获取Part
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "通过查询条件获取Part", notes = "通过查询条件获取Part")
    @PostMapping("/part/get")
    public R<PartDTO> getPart(@RequestBody PartQueryDTO queryDTO) {
        log.debug("REST request to get Part : {}", queryDTO);
        Part part = partService.lambdaQuery().eq(Part::getGuid, queryDTO.getGuid()).one();
        PartDTO result = null;
        if (part != null) {
            result = PartConverter.INSTANCE.to(part);
            if (Objects.nonNull(queryDTO.getWithCompany()) && queryDTO.getWithCompany() && StringUtils.isNotBlank(part.getCustomerGuid())) {
                Company company = companyService.lambdaQuery().eq(Company::getGuid, part.getCustomerGuid()).one();
                result.setCompany(Objects.isNull(company) ? null : CompanyConverter.INSTANCE.to(company));
            }
            if (Objects.nonNull(queryDTO.getWithDrawing()) && queryDTO.getWithDrawing()) {
                List<Drawing> ds = drawingService.lambdaQuery().eq(Drawing::getPartGuid, part.getGuid()).list();
                result.setDrawings(DrawingConverter.INSTANCE.to(ds));
            }
        }
        return result == null ? R.failed("未查询到数据") : R.ok(result, "查询成功");
    }

    /**
     * 根据ID获取PART
     * @param guid guid
     * @return
     */
    @ApiOperation(value = "根据ID获取PART", notes = "根据ID获取PART")
    // @JsonFieldFilter(type = PartDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = PartDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = PartDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = PartDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/part/guid/{guid}")
    public R<PartDTO> getPartByGuid(@PathVariable String guid) {
        log.debug("REST request to get Part : {}", guid);
        Part part = partService.lambdaQuery().eq(Part::getGuid, guid).one();
        return part == null ? R.failed("未查询到数据") : R.ok(PartConverter.INSTANCE.to(part), "查询成功");
    }

    /**
     * 通过id删除PART
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除PART", notes = "通过id删除PART")
    @DeleteMapping("/part/{id}")
    public R deletePart(@PathVariable Long id) {
        log.debug("REST request to delete Parts : {}", id);
        return partService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
