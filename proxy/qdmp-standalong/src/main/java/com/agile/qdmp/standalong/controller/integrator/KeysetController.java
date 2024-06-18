package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.KeysetConverter;
import com.agile.qdmp.standalong.model.dto.integrator.KeysetDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.KeysetQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Keyset;
import com.agile.qdmp.standalong.service.integrator.IKeysetService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


/**
 * Keyset 前端控制器
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:59
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "keyset", tags = "Keyset管理")
public class KeysetController extends SuperController {

    private final IKeysetService keysetService;

    public KeysetController(IKeysetService keysetService) {
        this.keysetService = keysetService;
    }

    /**
     * 新增Keyset
     * @param keysetDTO Keyset
     * @return
     */
    @ApiOperation(value = "新增Keyset", notes = "新增Keyset")
    @PostMapping("/keyset")
    public R<KeysetDTO> createKeyset(@RequestBody KeysetDTO keysetDTO) {
        log.debug("REST request to save Keyset : {}", keysetDTO);
        if (keysetDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Keyset newData = KeysetConverter.INSTANCE.from(keysetDTO);
        Keyset data = keysetService.lambdaQuery().eq(Keyset::getGuid, keysetDTO.getGuid()).one();
        boolean result = true;
        if(data == null) {
            result = keysetService.save(newData);
        } else {
//            if(!data.getGuid().equalsIgnoreCase(keysetDTO.getGuid())) {
//                newData.setId(data.getId());
//                newData.setUpdateTime((int) (System.currentTimeMillis() / 1000));
//                result = keysetService.updateById(newData);
//            }
        }
        if(result) {
            keysetDTO.setId(newData.getId());
            return R.ok(keysetDTO, "添加成功");
        } else {
            return R.failed(keysetDTO, "添加失败");
        }
    }

    /**
     * 修改Keyset
     * @param keysetDTO Keyset
     * @return
     */
    @ApiOperation(value = "修改Keyset", notes = "修改Keyset")
    @PutMapping("/keyset")
    public R<KeysetDTO> updateKeyset(@RequestBody KeysetDTO keysetDTO) {
        log.debug("REST request to update Keyset : {}", keysetDTO);
        if (keysetDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Keyset data = KeysetConverter.INSTANCE.from(keysetDTO);
        return keysetService.updateById(data) ? R.ok(keysetDTO, "修改成功") : R.failed(keysetDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO Keyset
     * @return
     */
    @ApiOperation(value = "分页查询Keyset", notes = "分页查询Keyset")
    // @JsonFieldFilter(type = KeysetDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = KeysetDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = KeysetDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = KeysetDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/keysets")
    public R<IPage<KeysetDTO>> getAllKeysets(KeysetQueryDTO queryDTO) {
        log.debug("REST request to get a page of Companies {}", queryDTO);
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<KeysetDTO> result = keysetService.lambdaQuery()
//                .or(StringUtils.isNotEmpty(queryDTO.getKeyWord()), o -> o.like(Keyset::getName, queryDTO.getKeyWord()).or().like(Keyset::getName, queryDTO.getKeyWord()).or())
                .like(StringUtils.isNotEmpty(queryDTO.getKeyWord()), Keyset::getName, queryDTO.getKeyWord())
                .eq(StringUtils.isNotEmpty(queryDTO.getPartGuid()), Keyset::getPartGuid, queryDTO.getPartGuid())
                .eq(Objects.nonNull(queryDTO.getServerId()), Keyset::getServerId, queryDTO.getServerId())
                .page(this.<Keyset>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> KeysetConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取Keyset
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询Keyset", notes = "通过id查询Keyset")
    // @JsonFieldFilter(type = KeysetDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = KeysetDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = KeysetDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = KeysetDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/keyset/{id}")
    public R<KeysetDTO> getKeyset(@PathVariable Long id) {
        log.debug("REST request to get Keyset : {}", id);
        Keyset keyset = keysetService.getById(id);
        return keyset == null ? R.failed("未查询到数据") : R.ok(KeysetConverter.INSTANCE.to(keyset), "查询成功");
    }

    /**
     * 通过id删除Keyset
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除Keyset", notes = "通过id删除Keyset")
    @DeleteMapping("/keyset/{id}")
    public R deleteKeyset(@PathVariable Long id) {
        log.debug("REST request to delete Keysets : {}", id);
        return keysetService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
