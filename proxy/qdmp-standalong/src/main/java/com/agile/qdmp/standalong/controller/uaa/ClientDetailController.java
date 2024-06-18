package com.agile.qdmp.standalong.controller.uaa;

import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.convert.uaa.ClientDetailConverter;
import com.agile.qdmp.standalong.model.dto.uaa.ClientDetailDTO;
import com.agile.qdmp.standalong.model.dto.uaa.ClientDetailQueryDTO;
import com.agile.qdmp.standalong.model.entity.uaa.ClientDetail;
import com.agile.qdmp.standalong.service.uaa.IClientDetailService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;


/**
 * 客户端信息 前端控制器
 *
 * @author wenbinglei
 * @date 2021-02-01 15:04:38
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "clientDetail", tags = "客户端信息管理")
public class ClientDetailController extends SuperController {

    private final IClientDetailService clientDetailService;
    private final PasswordEncoder passwordEncoder;

    public ClientDetailController(IClientDetailService clientDetailService, PasswordEncoder passwordEncoder) {
        this.clientDetailService = clientDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 新增客户端信息
     * @param clientDetailDTO 客户端信息
     * @return
     */
    @ApiOperation(value = "新增客户端信息", notes = "新增客户端信息")
    @PostMapping("/clientDetail")
    public R<ClientDetailDTO> createClientDetail(@RequestBody ClientDetailDTO clientDetailDTO) {
        log.debug("REST request to save ClientDetail : {}", clientDetailDTO);
        if (clientDetailDTO.getId() != null) {
            throw new BizException("不应该存在ID");
        }
        ClientDetail data = ClientDetailConverter.INSTANCE.from(clientDetailDTO);
        data.setCreateTime(LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
        data.setClientSecret(passwordEncoder.encode(data.getClientSecretPlain()));
//        data.setArchived(1);
        boolean result = clientDetailService.save(data);
        if(result) {
            clientDetailDTO.setId(data.getId());
            return R.ok(clientDetailDTO, "添加成功");
        } else {
            return R.failed(clientDetailDTO, "添加失败");
        }
    }

    /**
     * 修改客户端信息
     * @param clientDetailDTO 客户端信息
     * @return
     */
    @ApiOperation(value = "修改客户端信息", notes = "修改客户端信息")
    @PutMapping("/clientDetail")
    public R<ClientDetailDTO> updateClientDetail(@RequestBody ClientDetailDTO clientDetailDTO) {
        log.debug("REST request to update ClientDetail : {}", clientDetailDTO);
        if (clientDetailDTO.getId() == null) {
            throw new BizException("ID为空");
        }
        ClientDetail data = ClientDetailConverter.INSTANCE.from(clientDetailDTO);
        if(StringUtils.isNotBlank(data.getClientSecretPlain())) {
            data.setClientSecret(passwordEncoder.encode(data.getClientSecretPlain()));
        }
        boolean result = clientDetailService.updateById(data);
        return result ? R.ok(clientDetailDTO, "修改成功") : R.failed(clientDetailDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO 客户端信息
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/clientDetails")
    public R<IPage<ClientDetailDTO>> getAllClientDetails(ClientDetailQueryDTO queryDTO) {
        log.debug("REST request to get a page of ClientDetails");
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<ClientDetailDTO> result = clientDetailService.lambdaQuery()
                .like(StringUtils.isNotBlank(queryDTO.getClientId()), ClientDetail::getClientId, queryDTO.getClientId())
                .eq(StringUtils.isNotBlank(queryDTO.getScope()), ClientDetail::getScope, queryDTO.getScope())
                .page(this.<ClientDetail>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> ClientDetailConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取客户端信息
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/clientDetail/{id}")
    public R<ClientDetailDTO> getClientDetail(@PathVariable Long id) {
        log.debug("REST request to get ClientDetail : {}", id);
        ClientDetail clientDetail = clientDetailService.getById(id);
        return clientDetail == null ? R.failed("未查询到数据") : R.ok(ClientDetailConverter.INSTANCE.to(clientDetail), "查询成功");
    }

    /**
     * 通过id删除客户端信息
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除客户端信息", notes = "通过id删除客户端信息")
    @DeleteMapping("/clientDetail/{id}")
    public R deleteClientDetail(@PathVariable Long id) {
        log.debug("REST request to delete ClientDetails : {}", id);
        return clientDetailService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
