package com.agile.qdmp.standalong.model.convert.account;

import com.agile.qdmp.standalong.model.dto.account.LoginInfoDTO;
import com.agile.qdmp.standalong.model.entity.account.LoginInfo;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户登录信息表 数据转换
 *
 * @author wenbinglei
 * @date 2021-03-23 18:00:31
 */
@Mapper
public interface LoginInfoConverter extends Convert<LoginInfo, LoginInfoDTO> {
    LoginInfoConverter INSTANCE = Mappers.getMapper(LoginInfoConverter.class);
}
