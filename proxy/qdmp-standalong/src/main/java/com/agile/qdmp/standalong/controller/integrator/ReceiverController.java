package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.ReceiverConverter;
import com.agile.qdmp.standalong.model.dto.integrator.ReceiverDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.ReceiverQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Receiver;
import com.agile.qdmp.standalong.service.integrator.IReceiverService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Receivers 前端控制器
 *
 * @author hyzh code generator
 * @date 2022-11-22 18:41:33
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "receiver", tags = "Receivers管理")
public class ReceiverController extends SuperController {

    private final IReceiverService receiverService;

    public ReceiverController(IReceiverService receiverService) {
        this.receiverService = receiverService;
    }

    /**
     * 新增Receivers
     * @param receiverDTO Receivers
     * @return
     */
    @ApiOperation(value = "新增Receivers", notes = "新增Receivers")
    @PostMapping("/receiver")
    public R<ReceiverDTO> createReceiver(@RequestBody ReceiverDTO receiverDTO) {
        log.debug("REST request to save Receiver : {}", receiverDTO);
        if (receiverDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Receiver data = ReceiverConverter.INSTANCE.from(receiverDTO);
        boolean result = receiverService.save(data);
        if(result) {
            receiverDTO.setId(data.getId());
            return R.ok(receiverDTO, "添加成功");
        } else {
            return R.failed(receiverDTO, "添加失败");
        }
    }

    /**
     * 修改Receivers
     * @param receiverDTO Receivers
     * @return
     */
    @ApiOperation(value = "修改Receivers", notes = "修改Receivers")
    @PutMapping("/receiver")
    public R<ReceiverDTO> updateReceiver(@RequestBody ReceiverDTO receiverDTO) {
        log.debug("REST request to update Receiver : {}", receiverDTO);
        if (receiverDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Receiver data = ReceiverConverter.INSTANCE.from(receiverDTO);
        return receiverService.updateById(data) ? R.ok(receiverDTO, "修改成功") : R.failed(receiverDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO Receivers
     * @return
     */
    @ApiOperation(value = "分页查询Receivers", notes = "分页查询Receivers")
    // @JsonFieldFilter(type = ReceiverDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = ReceiverDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = ReceiverDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = ReceiverDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/receivers")
    public R<IPage<ReceiverDTO>> getAllReceivers(ReceiverQueryDTO queryDTO) {
        log.debug("REST request to get a page of Receivers");
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<ReceiverDTO> result = receiverService.lambdaQuery()
//                .like(StringUtils.isNotEmpty(queryDTO.getName()), Receiver::getName, queryDTO.getName())
//                .eq(StringUtils.isNotEmpty(queryDTO.getClassName()), Receiver::getClassName, queryDTO.getClassName())
//                .eq(Objects.nonNull(queryDTO.getAccountId()), Receiver::getAccountId, queryDTO.getAccountId())
                .page(this.<Receiver>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> ReceiverConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取Receivers
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询Receivers", notes = "通过id查询Receivers")
    // @JsonFieldFilter(type = ReceiverDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = ReceiverDTO.class, exclude = {"id", ""})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = ReceiverDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = ReceiverDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/receiver/{id}")
    public R<ReceiverDTO> getReceiver(@PathVariable Long id) {
        log.debug("REST request to get Receiver : {}", id);
        Receiver receiver = receiverService.getById(id);
        return receiver == null ? R.failed("未查询到数据") : R.ok(ReceiverConverter.INSTANCE.to(receiver), "查询成功");
    }

    /**
     * 通过id删除Receivers
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除Receivers", notes = "通过id删除Receivers")
    @DeleteMapping("/receiver/{id}")
    public R deleteReceiver(@PathVariable Long id) {
        log.debug("REST request to delete Receivers : {}", id);
        return receiverService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
