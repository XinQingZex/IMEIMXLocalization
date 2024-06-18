package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.LocationConverter;
import com.agile.qdmp.standalong.model.dto.integrator.LocationDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.LocationQueryDTO;
import com.agile.qdmp.standalong.service.integrator.ILocationService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.Location;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * LOCATION 前端控制器
 *
 * @author wenbinglei
 * @date 2022-10-21 11:48:01
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "location", tags = "LOCATION管理")
public class LocationController extends SuperController {

    private final ILocationService locationService;

    public LocationController(ILocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * 新增LOCATION
     * @param locationDTO LOCATION
     * @return
     */
    @ApiOperation(value = "新增LOCATION", notes = "新增LOCATION")
    @PostMapping("/location")
    public R<LocationDTO> createLocation(@RequestBody LocationDTO locationDTO) {
        log.debug("REST request to save Location : {}", locationDTO);
        if (locationDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Location newData = LocationConverter.INSTANCE.from(locationDTO);
        Location data = locationService.lambdaQuery().eq(Location::getGuid, locationDTO.getGuid()).select(Location::getId, Location::getFlag).one();
        boolean result = true;
        if(data == null) {
            result = locationService.save(newData);
        } else {
            if(!data.getFlag().equalsIgnoreCase(locationDTO.getFlag())) {
                newData.setId(data.getId());
                newData.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                result = locationService.updateById(newData);
            }
        }
        if(result) {
            locationDTO.setId(newData.getId());
            return R.ok(locationDTO, "添加成功");
        } else {
            return R.failed(locationDTO, "添加失败");
        }
    }

    /**
     * 修改LOCATION
     * @param locationDTO LOCATION
     * @return
     */
    @ApiOperation(value = "修改LOCATION", notes = "修改LOCATION")
    @PutMapping("/location")
    public R<LocationDTO> updateLocation(@RequestBody LocationDTO locationDTO) {
        log.debug("REST request to update Location : {}", locationDTO);
        if (locationDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Location data = LocationConverter.INSTANCE.from(locationDTO);
        return locationService.updateById(data) ? R.ok(locationDTO, "修改成功") : R.failed(locationDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO LOCATION
     * @return
     */
    @ApiOperation(value = "分页查询LOCATION", notes = "分页查询LOCATION")
    // @JsonFieldFilter(type = LocationDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = LocationDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = LocationDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = LocationDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/locations")
    public R<IPage<LocationDTO>> getAllLocations(LocationQueryDTO queryDTO) {
        log.debug("REST request to get a page of Locations {}", queryDTO);
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<LocationDTO> result = locationService.lambdaQuery()
                .or(StringUtils.isNotEmpty(queryDTO.getKeyWord()), o -> o.like(Location::getName, queryDTO.getKeyWord()).or().like(Location::getEmail, queryDTO.getKeyWord()).or())
//                .like(StringUtils.isNotEmpty(queryDTO.getName()), Location::getName, queryDTO.getName())
                .eq(StringUtils.isNotEmpty(queryDTO.getCompanyGuid()), Location::getCompanyGuid, queryDTO.getCompanyGuid())
                .eq(Objects.nonNull(queryDTO.getType()), Location::getType, queryDTO.getType())
                .eq(Objects.nonNull(queryDTO.getServerId()), Location::getServerId, queryDTO.getServerId())
                .eq(Objects.nonNull(queryDTO.getCompanyId()), Location::getCompanyId, queryDTO.getCompanyId())
                .page(this.<Location>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> LocationConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取LOCATION
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询LOCATION", notes = "通过id查询LOCATION")
    // @JsonFieldFilter(type = LocationDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = LocationDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = LocationDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = LocationDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/location/{id}")
    public R<LocationDTO> getLocation(@PathVariable Long id) {
        log.debug("REST request to get Location : {}", id);
        Location location = locationService.getById(id);
        return location == null ? R.failed("未查询到数据") : R.ok(LocationConverter.INSTANCE.to(location), "查询成功");
    }

    /**
     * 通过查询条件获取LOCATION
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "通过查询条件获取LOCATION", notes = "通过查询条件获取LOCATION")
    @PostMapping("/location/get")
    public R<LocationDTO> getLocation(@RequestBody LocationQueryDTO queryDTO) {
        log.debug("REST request to get Location : {}", queryDTO);
        Location location = locationService.lambdaQuery().eq(Location::getGuid, queryDTO.getGuid()).one();
        LocationDTO result = null;
        if (location != null) {
            result = LocationConverter.INSTANCE.to(location);
//            if (Objects.nonNull(queryDTO.getWithCompany()) && queryDTO.getWithCompany() && StringUtils.isNotBlank(part.getCustomerGuid())) {
//                Company company = companyService.lambdaQuery().eq(Company::getGuid, part.getCustomerGuid()).one();
//                result.setCompany(Objects.isNull(part) ? null : CompanyConverter.INSTANCE.to(company));
//            }
        }
        return result == null ? R.failed("未查询到数据") : R.ok(result, "查询成功");
    }

    /**
     * 通过id删除LOCATION
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除LOCATION", notes = "通过id删除LOCATION")
    @DeleteMapping("/location/{id}")
    public R deleteLocation(@PathVariable Long id) {
        log.debug("REST request to delete Locations : {}", id);
        return locationService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
