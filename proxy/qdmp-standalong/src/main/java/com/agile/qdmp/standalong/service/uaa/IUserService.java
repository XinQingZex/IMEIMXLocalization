package com.agile.qdmp.standalong.service.uaa;

import com.agile.qdmp.standalong.model.dto.account.LoginInfoDTO;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.dto.uaa.RegistDTO;

import java.util.Optional;

/**
 * <p>
 * 用户基础信息表 服务类
 * </p>
 *
 * @author Caratacus
 * @since 2019-01-06
 */
public interface IUserService {

    /**
     * 用户注册
     * @param infoDTO
     * @param password
     * @return
     */
    R<LoginInfoDTO> register(RegistDTO infoDTO, String password);

    /**
     * 获取当前用户
     * @param systemName
     * @return
     */
    Optional<LoginInfoDTO> getUserWithAuthorities(String systemName);

    /**
     * 修改用户密码
     * @param userId
     * @param currentClearTextPassword
     * @param newPassword
     * @return
     */
    Boolean changePassword(Long userId, String currentClearTextPassword, String newPassword);

    /**
     * 修改手机号
     * @param userId
     * @param mobile
     * @return
     */
    R<Boolean> changeMobile(Long userId, String mobile);

    /**
     * 重设用户密码
     * @param userId
     * @param newPassword
     * @return
     */
    Boolean resetPassword(Long userId, String newPassword);
}
