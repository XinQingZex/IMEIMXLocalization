package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.CharacterDesignatorConverter;
import com.agile.qdmp.standalong.model.dto.integrator.CharacterDesignatorDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.CharacterDesignatorQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.CharacterDesignator;
import com.agile.qdmp.standalong.service.integrator.ICharacterDesignatorService;
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
 * CharacterDesignator 前端控制器
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:59
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "characterDesignator", tags = "CharacterDesignator管理")
public class CharacterDesignatorController extends SuperController {

    private final ICharacterDesignatorService characterDesignatorService;

    public CharacterDesignatorController(ICharacterDesignatorService characterDesignatorService) {
        this.characterDesignatorService = characterDesignatorService;
    }

    /**
     * 新增CharacterDesignator
     * @param characterDesignatorDTO CharacterDesignator
     * @return
     */
    @ApiOperation(value = "新增CharacterDesignator", notes = "新增CharacterDesignator")
    @PostMapping("/characterDesignator")
    public R<CharacterDesignatorDTO> createCharacterDesignator(@RequestBody CharacterDesignatorDTO characterDesignatorDTO) {
        log.debug("REST request to save CharacterDesignator : {}", characterDesignatorDTO);
        if (characterDesignatorDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        CharacterDesignator newData = CharacterDesignatorConverter.INSTANCE.from(characterDesignatorDTO);
        CharacterDesignator data = characterDesignatorService.lambdaQuery().eq(CharacterDesignator::getGuid, characterDesignatorDTO.getGuid()).one();
        boolean result = true;
        if(data == null) {
            result = characterDesignatorService.save(newData);
        } else {
//            if(!data.getGuid().equalsIgnoreCase(characterDesignatorDTO.getGuid())) {
//                newData.setId(data.getId());
//                newData.setUpdateTime((int) (System.currentTimeMillis() / 1000));
//                result = characterDesignatorService.updateById(newData);
//            }
        }
        if(result) {
            characterDesignatorDTO.setId(newData.getId());
            return R.ok(characterDesignatorDTO, "添加成功");
        } else {
            return R.failed(characterDesignatorDTO, "添加失败");
        }
    }

    /**
     * 修改CharacterDesignator
     * @param characterDesignatorDTO CharacterDesignator
     * @return
     */
    @ApiOperation(value = "修改CharacterDesignator", notes = "修改CharacterDesignator")
    @PutMapping("/characterDesignator")
    public R<CharacterDesignatorDTO> updateCharacterDesignator(@RequestBody CharacterDesignatorDTO characterDesignatorDTO) {
        log.debug("REST request to update CharacterDesignator : {}", characterDesignatorDTO);
        if (characterDesignatorDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        CharacterDesignator data = CharacterDesignatorConverter.INSTANCE.from(characterDesignatorDTO);
        return characterDesignatorService.updateById(data) ? R.ok(characterDesignatorDTO, "修改成功") : R.failed(characterDesignatorDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO CharacterDesignator
     * @return
     */
    @ApiOperation(value = "分页查询CharacterDesignator", notes = "分页查询CharacterDesignator")
    // @JsonFieldFilter(type = CharacterDesignatorDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = CharacterDesignatorDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = CharacterDesignatorDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = CharacterDesignatorDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/characterDesignators")
    public R<IPage<CharacterDesignatorDTO>> getAllCharacterDesignators(CharacterDesignatorQueryDTO queryDTO) {
        log.debug("REST request to get a page of Companies {}", queryDTO);
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<CharacterDesignatorDTO> result = characterDesignatorService.lambdaQuery()
//                .or(StringUtils.isNotEmpty(queryDTO.getKeyWord()), o -> o.like(CharacterDesignator::getName, queryDTO.getKeyWord()).or().like(CharacterDesignator::getName, queryDTO.getKeyWord()).or())
                .like(StringUtils.isNotEmpty(queryDTO.getKeyWord()), CharacterDesignator::getName, queryDTO.getKeyWord())
                .eq(StringUtils.isNotEmpty(queryDTO.getPartGuid()), CharacterDesignator::getPartGuid, queryDTO.getPartGuid())
                .eq(Objects.nonNull(queryDTO.getServerId()), CharacterDesignator::getServerId, queryDTO.getServerId())
                .page(this.<CharacterDesignator>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> CharacterDesignatorConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取CharacterDesignator
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询CharacterDesignator", notes = "通过id查询CharacterDesignator")
    // @JsonFieldFilter(type = CharacterDesignatorDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = CharacterDesignatorDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = CharacterDesignatorDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = CharacterDesignatorDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/characterDesignator/{id}")
    public R<CharacterDesignatorDTO> getCharacterDesignator(@PathVariable Long id) {
        log.debug("REST request to get CharacterDesignator : {}", id);
        CharacterDesignator characterDesignator = characterDesignatorService.getById(id);
        return characterDesignator == null ? R.failed("未查询到数据") : R.ok(CharacterDesignatorConverter.INSTANCE.to(characterDesignator), "查询成功");
    }

    /**
     * 通过id删除CharacterDesignator
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除CharacterDesignator", notes = "通过id删除CharacterDesignator")
    @DeleteMapping("/characterDesignator/{id}")
    public R deleteCharacterDesignator(@PathVariable Long id) {
        log.debug("REST request to delete CharacterDesignators : {}", id);
        return characterDesignatorService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
