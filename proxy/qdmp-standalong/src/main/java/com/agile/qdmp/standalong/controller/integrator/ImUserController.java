package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.ImUserConverter;
import com.agile.qdmp.standalong.model.dto.integrator.ImUserDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.ImUserQueryDTO;
import com.agile.qdmp.standalong.service.integrator.IImUserService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.ImUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * 质量管理大师用户信息表 前端控制器
 *
 * @author hyzh code generator
 * @date 2022-09-19 09:56:46
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "imUser", tags = "质量管理大师用户信息表管理")
public class ImUserController extends SuperController {

    private final IImUserService imUserService;

    public ImUserController(IImUserService imUserService) {
        this.imUserService = imUserService;
    }

    /**
     * 新增质量管理大师用户信息表
     * @param imUserDTO 质量管理大师用户信息表
     * @return
     */
    @ApiOperation(value = "新增质量管理大师用户信息表", notes = "新增质量管理大师用户信息表")
    @PostMapping("/imUser")
    public R<ImUserDTO> createImUser(@RequestBody ImUserDTO imUserDTO) {
        log.debug("REST request to save ImUser : {}", imUserDTO);
        if (imUserDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        ImUser data = ImUserConverter.INSTANCE.from(imUserDTO);
        data.setState("正常");
        data.setCreateTime((int) (System.currentTimeMillis()/1000));
        data.setUpdateTime(data.getCreateTime());
        boolean result = imUserService.save(data);
        if(result) {
            imUserDTO.setId(data.getId());
            return R.ok(imUserDTO, "添加成功");
        } else {
            return R.failed(imUserDTO, "添加失败");
        }
    }

    /**
     * 修改质量管理大师用户信息表
     * @param imUserDTO 质量管理大师用户信息表
     * @return
     */
    @ApiOperation(value = "修改质量管理大师用户信息表", notes = "修改质量管理大师用户信息表")
    @PutMapping("/imUser")
    public R<ImUserDTO> updateImUser(@RequestBody ImUserDTO imUserDTO) {
        log.debug("REST request to update ImUser : {}", imUserDTO);
        if (imUserDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        ImUser data = ImUserConverter.INSTANCE.from(imUserDTO);
        return imUserService.updateById(data) ? R.ok(imUserDTO, "修改成功") : R.failed(imUserDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO 质量管理大师用户信息表
     * @return
     */
    @ApiOperation(value = "分页查询质量管理大师用户信息表", notes = "分页查询质量管理大师用户信息表")
    // @JsonFieldFilter(type = ImUserDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = ImUserDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = ImUserDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = ImUserDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/imUsers")
    public R<IPage<ImUserDTO>> getAllImUsers(ImUserQueryDTO queryDTO) {
        log.debug("REST request to get a page of ImUsers");
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<ImUserDTO> result = imUserService.lambdaQuery()
//                .like(StringUtils.isNotEmpty(queryDTO.getName()), ImUser::getName, queryDTO.getName())
                .eq(StringUtils.isNotEmpty(queryDTO.getState()), ImUser::getState, queryDTO.getState())
                .eq(Objects.nonNull(queryDTO.getAccountId()), ImUser::getAccountId, queryDTO.getAccountId())
                .eq(Objects.nonNull(queryDTO.getCompanyId()), ImUser::getCompanyId, queryDTO.getCompanyId())
                .eq(Objects.nonNull(queryDTO.getServerId()), ImUser::getServerId, queryDTO.getServerId())
                .eq(Objects.nonNull(queryDTO.getStaffId()), ImUser::getStaffId, queryDTO.getStaffId())
                .page(this.<ImUser>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> ImUserConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取质量管理大师用户信息表
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询质量管理大师用户信息表", notes = "通过id查询质量管理大师用户信息表")
    // @JsonFieldFilter(type = ImUserDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = ImUserDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = ImUserDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = ImUserDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/imUser/{id}")
    public R<ImUserDTO> getImUser(@PathVariable Long id) {
        log.debug("REST request to get ImUser : {}", id);
        ImUser imUser = imUserService.getById(id);
        return imUser == null ? R.failed("未查询到数据") : R.ok(ImUserConverter.INSTANCE.to(imUser), "查询成功");
    }

    /**
     * 通过id删除质量管理大师用户信息表
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除质量管理大师用户信息表", notes = "通过id删除质量管理大师用户信息表")
    @DeleteMapping("/imUser/{id}")
    public R deleteImUser(@PathVariable Long id) {
        log.debug("REST request to delete ImUsers : {}", id);
        return imUserService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
