package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.MachineConverter;
import com.agile.qdmp.standalong.model.dto.integrator.MachineDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.MachineQueryDTO;
import com.agile.qdmp.standalong.service.integrator.IMachineService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.Machine;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * Machine 前端控制器
 *
 * @author wenbinglei
 * @date 2022-10-21 11:47:57
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "machine", tags = "Machine管理")
public class MachineController extends SuperController {

    private final IMachineService machineService;

    public MachineController(IMachineService machineService) {
        this.machineService = machineService;
    }

    /**
     * 新增Machine
     * @param machineDTO Machine
     * @return
     */
    @ApiOperation(value = "新增Machine", notes = "新增Machine")
    @PostMapping("/machine")
    public R<MachineDTO> createMachine(@RequestBody MachineDTO machineDTO) {
        log.debug("REST request to save Machine : {}", machineDTO);
        if (machineDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Machine newData = MachineConverter.INSTANCE.from(machineDTO);
        Machine data = machineService.lambdaQuery().eq(Machine::getGuid, machineDTO.getGuid()).select(Machine::getId, Machine::getFlag).one();
        boolean result = true;
        if(data == null) {
            result = machineService.save(newData);
        } else {
            if(!data.getFlag().equalsIgnoreCase(machineDTO.getFlag())) {
                newData.setId(data.getId());
                newData.setUpdateTime((int) (System.currentTimeMillis() / 1000));
                result = machineService.updateById(newData);
            }
        }
        if(result) {
            machineDTO.setId(newData.getId());
            return R.ok(machineDTO, "添加成功");
        } else {
            return R.failed(machineDTO, "添加失败");
        }
    }

    /**
     * 修改Machine
     * @param machineDTO Machine
     * @return
     */
    @ApiOperation(value = "修改Machine", notes = "修改Machine")
    @PutMapping("/machine")
    public R<MachineDTO> updateMachine(@RequestBody MachineDTO machineDTO) {
        log.debug("REST request to update Machine : {}", machineDTO);
        if (machineDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Machine data = MachineConverter.INSTANCE.from(machineDTO);
        return machineService.updateById(data) ? R.ok(machineDTO, "修改成功") : R.failed(machineDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO Machine
     * @return
     */
    @ApiOperation(value = "分页查询Machine", notes = "分页查询Machine")
    // @JsonFieldFilter(type = MachineDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = MachineDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = MachineDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = MachineDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/machines")
    public R<IPage<MachineDTO>> getAllMachines(MachineQueryDTO queryDTO) {
        log.debug("REST request to get a page of Machines");
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<MachineDTO> result = machineService.lambdaQuery()
                .or(StringUtils.isNotEmpty(queryDTO.getKeyWord()), o -> o.like(Machine::getNotes, queryDTO.getKeyWord()).or().like(Machine::getSerialNo, queryDTO.getKeyWord()).or())
//                .like(StringUtils.isNotEmpty(queryDTO.getName()), Machine::getName, queryDTO.getName())
                .eq(StringUtils.isNotEmpty(queryDTO.getWorkCellGuid()), Machine::getWorkCellGuid, queryDTO.getWorkCellGuid())
                .eq(Objects.nonNull(queryDTO.getStatus()), Machine::getStatus, queryDTO.getStatus())
                .eq(Objects.nonNull(queryDTO.getCompanyId()), Machine::getCompanyId, queryDTO.getCompanyId())
                .eq(Objects.nonNull(queryDTO.getServerId()), Machine::getServerId, queryDTO.getServerId())
                .page(this.<Machine>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> MachineConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取Machine
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询Machine", notes = "通过id查询Machine")
    // @JsonFieldFilter(type = MachineDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = MachineDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = MachineDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = MachineDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/machine/{id}")
    public R<MachineDTO> getMachine(@PathVariable Long id) {
        log.debug("REST request to get Machine : {}", id);
        Machine machine = machineService.getById(id);
        return machine == null ? R.failed("未查询到数据") : R.ok(MachineConverter.INSTANCE.to(machine), "查询成功");
    }

    /**
     * 通过查询条件获取Machine
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "通过查询条件获取Machine", notes = "通过查询条件获取Machine")
    @PostMapping("/machine/get")
    public R<MachineDTO> getMachine(@RequestBody MachineQueryDTO queryDTO) {
        log.debug("REST request to get Machine : {}", queryDTO);
        Machine machine = machineService.lambdaQuery().eq(Machine::getGuid, queryDTO.getGuid()).one();
        MachineDTO result = null;
        if (machine != null) {
            result = MachineConverter.INSTANCE.to(machine);
//            if (Objects.nonNull(queryDTO.getWithCompany()) && queryDTO.getWithCompany() && StringUtils.isNotBlank(part.getCustomerGuid())) {
//                Company company = companyService.lambdaQuery().eq(Company::getGuid, part.getCustomerGuid()).one();
//                result.setCompany(Objects.isNull(part) ? null : CompanyConverter.INSTANCE.to(company));
//            }
        }
        return result == null ? R.failed("未查询到数据") : R.ok(result, "查询成功");
    }
    /**
     * 通过id删除Machine
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除Machine", notes = "通过id删除Machine")
    @DeleteMapping("/machine/{id}")
    public R deleteMachine(@PathVariable Long id) {
        log.debug("REST request to delete Machines : {}", id);
        return machineService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
