package com.agile.qdmp.standalong.controller.uaa;

import com.agile.qdmp.standalong.model.dto.account.LoginInfoDTO;
import com.agile.qdmp.standalong.model.dto.rbac.AccountRoleDTO;
import com.agile.qdmp.standalong.service.account.ILoginInfoService;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.tem.common.filter.annotation.JsonFieldFilter;
import com.agile.qdmp.standalong.model.dto.uaa.PasswordChangeDTO;
import com.agile.qdmp.standalong.model.dto.uaa.RegistDTO;
import com.agile.qdmp.standalong.service.uaa.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户基础信息表 前端控制器
 * </p>
 *
 * @author Caratacus
 * @since 2019-01-06
 */
@RestController
@RequestMapping("/api")
@Slf4j
@Api(value = "账户注册登录和重置密码", tags = "账户注册登录和重置密码")
public class UserRestController extends SuperController {

    private final IUserService userService;

    public UserRestController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 使用用户名和密码注册新用户
     * @param infoDTO
     * @return
     */
    @ApiOperation(value = "value:使用用户名和密码注册新用户", notes = "notes:用户名和密码注册")
    @JsonFieldFilter(type = LoginInfoDTO.class, exclude = {"password"})
//    @JsonFieldFilter(type = LoginInfoDTO.class, include = {"id", "username", "mobile"})
//    @JsonFieldFilter(type = Page.class, include = {"pageSize", "pageNo", "count", "list"})
//    @JsonFieldFilters( value = {
//            @JsonFieldFilter(type = LoginInfoDTO.class, include = {"id", "username", "mobile"}),
//            @JsonFieldFilter(type = LoginInfoDTO.class, include = {"id", "username", "mobile"})
//    })
    @PostMapping("/open/register")
    public R<LoginInfoDTO> registerAccount(@RequestBody RegistDTO infoDTO) {
        if (StringUtils.isBlank(infoDTO.getPassword())) {
            throw new BizException("缺少参数：password");
        }
        if(StringUtils.isBlank(infoDTO.getSource())) {
            throw new BizException("缺少参数：用户来源");
        }
        return userService.register(infoDTO, infoDTO.getPassword());
    }

    /**
     * 使用手机号注册新用户
     * @param infoDTO
     * @return
     */
    @ApiOperation(value = "value:使用手机号注册新用户", notes = "notes:使用手机号注册新用户")
    @JsonFieldFilter(type = LoginInfoDTO.class, exclude = {"password"})
//    @JsonFieldFilter(type = LoginInfoDTO.class, include = {"id", "username", "mobile"})
//    @JsonFieldFilter(type = Page.class, include = {"pageSize", "pageNo", "count", "list"})
//    @JsonFieldFilters( value = {
//            @JsonFieldFilter(type = LoginInfoDTO.class, include = {"id", "username", "mobile"}),
//            @JsonFieldFilter(type = LoginInfoDTO.class, include = {"id", "username", "mobile"})
//    })
    @PostMapping("/open/register/mobile")
    public R<LoginInfoDTO> registerAccountByMobile(@RequestBody RegistDTO infoDTO) {
        if (StringUtils.isBlank(infoDTO.getPassword())) {
            throw new BizException("缺少参数：password");
        }
        if(StringUtils.isBlank(infoDTO.getSource())) {
            throw new BizException("缺少参数：用户来源");
        }
        if(StringUtils.isBlank(infoDTO.getMobile())) {
            throw new BizException("缺少参数：手机号码");
        }
        return userService.register(infoDTO, infoDTO.getPassword());
    }

    /**
     * 检查用户是否授权
     * @param request
     * @return
     */
    @ApiOperation(value = "检查用户是否授权")
    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    /**
     * 获取当前用户
     * @return
     */
    @ApiOperation(value = "获取当前登录用户信息, 客户端登录前需要获取base64编码，内容为security_client:security_str。并且需要传入所属系统，以获取默认权限。")
    @GetMapping("/account")
    @JsonFieldFilter(type = LoginInfoDTO.class, include = {"id", "username", "nickname", "avatar", "mobile", "roles"})
    @JsonFieldFilter(type = AccountRoleDTO.class, include = {"id", "roleId", "roleName", "roleCode", "systemName", "supplierId", "supplierName"})
//    @Desensitization
    public R<LoginInfoDTO> getAccount(@RequestParam(value="systemName", required=false) String systemName) {
        return userService.getUserWithAuthorities(systemName)
                .map(account -> R.ok(account))
                .orElseThrow(() -> new BizException("当前用户不存在"));
    }

    /**
     * 修改登录密码
     * @param passwordChangeDto
     */
    @PostMapping(path = "/changePassword")
    @ApiOperation(value = "修改登录密码")
    public R<Boolean> changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
        if (StringUtils.isEmpty(passwordChangeDto.getNewPassword())) {
            throw new BizException("修改登录密码错误");
        }
        Boolean result = userService.changePassword(passwordChangeDto.getId(), passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
        if(result) {
            return R.ok(result, "更新成功");
        } else {
            return R.failed(result, "更新失败");
        }
    }

    /**
     * 重设登录密码
     * @param passwordChangeDto
     */
    @PostMapping(path = "/resetPassword")
    @ApiOperation(value = "重设登录密码")
    public R<Boolean> resetPassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
        if (StringUtils.isEmpty(passwordChangeDto.getNewPassword())) {
            throw new BizException("重置密码错误");
        }
        boolean result = userService.resetPassword(passwordChangeDto.getId(), passwordChangeDto.getNewPassword());
        return result ? R.ok( result, "重置密码成功") : R.failed(result, "重置密码失败");
    }

}

