package com.agile.qdmp.standalong.service.account;

import com.agile.qdmp.standalong.model.dto.account.LoginInfoDTO;
import com.agile.qdmp.standalong.model.entity.account.LoginInfo;
import com.agile.tem.common.core.util.R;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户登录信息表
 *
 * @author wenbinglei
 * @date 2021-03-23 18:00:31
 */
public interface ILoginInfoService extends IService<LoginInfo> {

    /**
     * 根据用户名获取账户信息附带权限
     * @param username
     * @param systemName
     * @return
     */
    LoginInfoDTO getAccountWithAuthoritiesByUsername(String username, String systemName);

    /**
     * 根据手机号获取账户信息附带权限
     * @param mobile
     * @param systemName
     * @return
     */
    LoginInfoDTO getAccountWithAuthoritiesByMobile(String mobile, String systemName);

    /**
     * 检查登录信息
     * @param loginInfo
     * @return
     */
    R<LoginInfo> checkLoginInfoData(LoginInfo loginInfo);
}
