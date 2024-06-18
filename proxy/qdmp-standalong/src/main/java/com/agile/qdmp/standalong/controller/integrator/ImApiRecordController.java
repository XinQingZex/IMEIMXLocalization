package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.ImApiRecordConverter;
import com.agile.qdmp.standalong.model.dto.integrator.ImApiRecordDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.ImApiRecordQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.ImApiRecord;
import com.agile.qdmp.standalong.service.integrator.IImApiRecordService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.util.R;
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
@Api(value = "imApiRecord", tags = "企业质量管理大师信息表管理")
public class ImApiRecordController extends SuperController {

    private final IImApiRecordService imApiRecordService;

    public ImApiRecordController(IImApiRecordService imApiRecordService) {
        this.imApiRecordService = imApiRecordService;
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO 企业质量管理大师信息表
     * @return
     */
    @ApiOperation(value = "分页查询企业质量管理大师信息表", notes = "分页查询企业质量管理大师信息表")
    // @JsonFieldFilter(type = ImApiRecordDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = ImApiRecordDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = ImApiRecordDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = ImApiRecordDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/imApiRecords")
    public R<IPage<ImApiRecordDTO>> getAllImApiRecords(ImApiRecordQueryDTO queryDTO) {
        log.debug("REST request to get a page of ImApiRecords {}", queryDTO);
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<ImApiRecordDTO> result = imApiRecordService.lambdaQuery()
//                .like(StringUtils.isNotEmpty(queryDTO.getName()), ImApiRecord::getName, queryDTO.getName())
//                .eq(StringUtils.isNotEmpty(queryDTO.getClassName()), ImApiRecord::getClassName, queryDTO.getClassName())
                .eq(Objects.nonNull(queryDTO.getCompanyId()), ImApiRecord::getCompanyId, queryDTO.getCompanyId())
                .eq(Objects.nonNull(queryDTO.getServerId()), ImApiRecord::getServerId, queryDTO.getServerId())
                .page(this.<ImApiRecord>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> ImApiRecordConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取企业质量管理大师信息表
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询企业质量管理大师信息表", notes = "通过id查询企业质量管理大师信息表")
    // @JsonFieldFilter(type = ImApiRecordDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = ImApiRecordDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = ImApiRecordDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = ImApiRecordDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/imApiRecord/{id}")
    public R<ImApiRecordDTO> getImApiRecord(@PathVariable Long id) {
        log.debug("REST request to get ImApiRecord : {}", id);
        ImApiRecord imApiRecord = imApiRecordService.getById(id);
        return imApiRecord == null ? R.failed("未查询到数据") : R.ok(ImApiRecordConverter.INSTANCE.to(imApiRecord), "查询成功");
    }

    /**
     * 通过id删除企业质量管理大师信息表
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除企业质量管理大师信息表", notes = "通过id删除企业质量管理大师信息表")
    @DeleteMapping("/imApiRecord/{id}")
    public R deleteImApiRecord(@PathVariable Long id) {
        log.debug("REST request to delete ImApiRecords : {}", id);
        return imApiRecordService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
