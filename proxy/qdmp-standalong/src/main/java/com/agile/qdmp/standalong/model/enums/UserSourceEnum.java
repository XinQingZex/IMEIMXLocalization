package com.agile.qdmp.standalong.model.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 用户来源
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserSourceEnum implements IEnum<String> {
    MA_BAG_LIFE("ma-bag-life", "微信小程序保安官生活版"),
    MA_BLY("ma-bly", "微信小程序保乐业"),
    WEB_ADMIN_SCM("web-admin-scm", "供应商管理系统"),
    WEB_ADMIN_TALENT("web-admin-talent", "智慧人才管理系统"),
    MOBILE("web-admin", "手机号注册");

    private String value;
    private String desc;

    UserSourceEnum(final String value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @JsonValue
    public String getDesc(){
        return this.desc;
    }
}
