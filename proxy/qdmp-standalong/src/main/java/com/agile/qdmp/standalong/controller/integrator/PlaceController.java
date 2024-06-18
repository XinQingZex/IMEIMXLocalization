package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.PlaceConverter;
import com.agile.qdmp.standalong.model.dto.integrator.PlaceDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.PlaceQueryDTO;
import com.agile.qdmp.standalong.service.integrator.IPlaceService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.Place;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * PLACE 前端控制器
 *
 * @author wenbinglei
 * @date 2022-10-21 11:47:49
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "place", tags = "PLACE管理")
public class PlaceController extends SuperController {

    private final IPlaceService placeService;

    public PlaceController(IPlaceService placeService) {
        this.placeService = placeService;
    }

    /**
     * 新增PLACE
     * @param placeDTO PLACE
     * @return
     */
    @ApiOperation(value = "新增PLACE", notes = "新增PLACE")
    @PostMapping("/place")
    public R<PlaceDTO> createPlace(@RequestBody PlaceDTO placeDTO) {
        log.debug("REST request to save Place : {}", placeDTO);
        if (placeDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Place newData = PlaceConverter.INSTANCE.from(placeDTO);
        Place data = placeService.lambdaQuery().eq(Place::getGuid, placeDTO.getGuid()).select(Place::getId, Place::getFlag).one();
        boolean result = true;
        if(data == null) {
            result = placeService.save(newData);
        } else {
            if(!data.getFlag().equalsIgnoreCase(placeDTO.getFlag())) {
                newData.setId(data.getId());
                newData.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                result = placeService.updateById(newData);
            }
        }
        if(result) {
            placeDTO.setId(newData.getId());
            return R.ok(placeDTO, "添加成功");
        } else {
            return R.failed(placeDTO, "添加失败");
        }
    }

    /**
     * 修改PLACE
     * @param placeDTO PLACE
     * @return
     */
    @ApiOperation(value = "修改PLACE", notes = "修改PLACE")
    @PutMapping("/place")
    public R<PlaceDTO> updatePlace(@RequestBody PlaceDTO placeDTO) {
        log.debug("REST request to update Place : {}", placeDTO);
        if (placeDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Place data = PlaceConverter.INSTANCE.from(placeDTO);
        return placeService.updateById(data) ? R.ok(placeDTO, "修改成功") : R.failed(placeDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO PLACE
     * @return
     */
    @ApiOperation(value = "分页查询PLACE", notes = "分页查询PLACE")
    // @JsonFieldFilter(type = PlaceDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = PlaceDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = PlaceDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = PlaceDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/places")
    public R<IPage<PlaceDTO>> getAllPlaces(PlaceQueryDTO queryDTO) {
        log.debug("REST request to get a page of Places {}", queryDTO);
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<PlaceDTO> result = placeService.lambdaQuery()
                .or(StringUtils.isNotEmpty(queryDTO.getKeyWord()), o -> o.like(Place::getName, queryDTO.getKeyWord()).or().like(Place::getDescription, queryDTO.getKeyWord()).or())
                .eq(StringUtils.isNotEmpty(queryDTO.getLocationGuid()), Place::getLocationGuid, queryDTO.getLocationGuid())
                .eq(Objects.nonNull(queryDTO.getServerId()), Place::getServerId, queryDTO.getServerId())
                .eq(Objects.nonNull(queryDTO.getCompanyId()), Place::getCompanyId, queryDTO.getCompanyId())
                .page(this.<Place>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> PlaceConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取PLACE
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询PLACE", notes = "通过id查询PLACE")
    // @JsonFieldFilter(type = PlaceDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = PlaceDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = PlaceDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = PlaceDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/place/{id}")
    public R<PlaceDTO> getPlace(@PathVariable Long id) {
        log.debug("REST request to get Place : {}", id);
        Place place = placeService.getById(id);
        return place == null ? R.failed("未查询到数据") : R.ok(PlaceConverter.INSTANCE.to(place), "查询成功");
    }

    /**
     * 通过查询条件获取Place
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "通过查询条件获取Place", notes = "通过查询条件获取Place")
    @PostMapping("/place/get")
    public R<PlaceDTO> getPlace(@RequestBody PlaceQueryDTO queryDTO) {
        log.debug("REST request to get Place : {}", queryDTO);
        Place place = placeService.lambdaQuery().eq(Place::getGuid, queryDTO.getGuid()).one();
        PlaceDTO result = null;
        if (place != null) {
            result = PlaceConverter.INSTANCE.to(place);
//            if (Objects.nonNull(queryDTO.getWithCompany()) && queryDTO.getWithCompany() && StringUtils.isNotBlank(part.getCustomerGuid())) {
//                Company company = companyService.lambdaQuery().eq(Company::getGuid, part.getCustomerGuid()).one();
//                result.setCompany(Objects.isNull(part) ? null : CompanyConverter.INSTANCE.to(company));
//            }
        }
        return result == null ? R.failed("未查询到数据") : R.ok(result, "查询成功");
    }

    /**
     * 通过id删除PLACE
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除PLACE", notes = "通过id删除PLACE")
    @DeleteMapping("/place/{id}")
    public R deletePlace(@PathVariable Long id) {
        log.debug("REST request to delete Places : {}", id);
        return placeService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
