package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.ImServerConverter;
import com.agile.qdmp.standalong.model.dto.integrator.ImServerDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.ImServerQueryDTO;
import com.agile.qdmp.standalong.service.integrator.IImServerService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.ImServer;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * 企业质量管理大师信息表 前端控制器
 *
 * @author hyzh code generator
 * @date 2022-09-19 09:56:51
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "imServer", tags = "企业质量管理大师信息表管理")
public class ImServerController extends SuperController {

    private final IImServerService imServerService;

    public ImServerController(IImServerService imServerService) {
        this.imServerService = imServerService;
    }

    /**
     * 新增企业质量管理大师信息表
     * @param imServerDTO 企业质量管理大师信息表
     * @return
     */
    @ApiOperation(value = "新增企业质量管理大师信息表", notes = "新增企业质量管理大师信息表")
    @PostMapping("/imServer")
    public R<ImServerDTO> createImServer(@RequestBody ImServerDTO imServerDTO) {
        log.debug("REST request to save ImServer : {}", imServerDTO);
        if (imServerDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        ImServer data = ImServerConverter.INSTANCE.from(imServerDTO);
        data.setCreateTime((int) (System.currentTimeMillis()/1000));
        data.setState("正常");
        boolean result = imServerService.save(data);
        if(result) {
            imServerDTO.setId(data.getId());
            return R.ok(imServerDTO, "添加成功");
        } else {
            return R.failed(imServerDTO, "添加失败");
        }
    }

    /**
     * 修改企业质量管理大师信息表
     * @param imServerDTO 企业质量管理大师信息表
     * @return
     */
    @ApiOperation(value = "修改企业质量管理大师信息表", notes = "修改企业质量管理大师信息表")
    @PutMapping("/imServer")
    public R<ImServerDTO> updateImServer(@RequestBody ImServerDTO imServerDTO) {
        log.debug("REST request to update ImServer : {}", imServerDTO);
        if (imServerDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        ImServer data = ImServerConverter.INSTANCE.from(imServerDTO);
        return imServerService.updateById(data) ? R.ok(imServerDTO, "修改成功") : R.failed(imServerDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO 企业质量管理大师信息表
     * @return
     */
    @ApiOperation(value = "分页查询企业质量管理大师信息表", notes = "分页查询企业质量管理大师信息表")
    // @JsonFieldFilter(type = ImServerDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = ImServerDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = ImServerDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = ImServerDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/imServers")
    public R<IPage<ImServerDTO>> getAllImServers(ImServerQueryDTO queryDTO) {
        log.debug("REST request to get a page of ImServers {}", queryDTO);
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<ImServerDTO> result = imServerService.lambdaQuery()
                .like(StringUtils.isNotEmpty(queryDTO.getName()), ImServer::getName, queryDTO.getName())
//                .eq(StringUtils.isNotEmpty(queryDTO.getClassName()), ImServer::getClassName, queryDTO.getClassName())
                .eq(Objects.nonNull(queryDTO.getCompanyId()), ImServer::getCompanyId, queryDTO.getCompanyId())
                .eq(Objects.nonNull(queryDTO.getCreatorId()), ImServer::getCreatorId, queryDTO.getCreatorId())
                .page(this.<ImServer>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> ImServerConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取企业质量管理大师信息表
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询企业质量管理大师信息表", notes = "通过id查询企业质量管理大师信息表")
    // @JsonFieldFilter(type = ImServerDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = ImServerDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = ImServerDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = ImServerDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/imServer/{id}")
    public R<ImServerDTO> getImServer(@PathVariable Long id) {
        log.debug("REST request to get ImServer : {}", id);
        ImServer imServer = imServerService.getById(id);
        return imServer == null ? R.failed("未查询到数据") : R.ok(ImServerConverter.INSTANCE.to(imServer), "查询成功");
    }

    /**
     * 通过id删除企业质量管理大师信息表
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除企业质量管理大师信息表", notes = "通过id删除企业质量管理大师信息表")
    @DeleteMapping("/imServer/{id}")
    public R deleteImServer(@PathVariable Long id) {
        log.debug("REST request to delete ImServers : {}", id);
        return imServerService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
