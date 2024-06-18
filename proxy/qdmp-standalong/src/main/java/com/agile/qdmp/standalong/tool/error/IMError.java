package com.agile.qdmp.standalong.tool.error;

import com.agile.qdmp.standalong.tool.util.json.IMGsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 质量管理大师错误码.
 * @Author: wenbinglei
 * @Date: 2022/9/3 15:45
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IMError implements Serializable {
    private static final long serialVersionUID = 7869786563361406291L;

    /**
     * 错误代码.
     */
    private int errorCode;

    /**
     * 错误信息.
     * （如果可以翻译为中文，就为中文）
     */
    private String errorMsg;

    /**
     * 接口返回的错误原始信息（英文）.
     */
    private String errorMsgEn;

    private String json;

    public IMError(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public static IMError fromJson(String json) {
        final IMError wxError = IMGsonBuilder.create().fromJson(json, IMError.class);
        if (wxError.getErrorCode() == 0) {
            return wxError;
        }

        if (StringUtils.isNotEmpty(wxError.getErrorMsg())) {
            wxError.setErrorMsgEn(wxError.getErrorMsg());
        }

        final String msg = IMErrorMsgEnum.findMsgByCode(wxError.getErrorCode());
        if (msg != null) {
            wxError.setErrorMsg(msg);
        }

        return wxError;
    }

    @Override
    public String toString() {
        if (this.json == null) {
            return "错误代码：" + this.errorCode + ", 错误信息：" + this.errorMsg;
        }

        return "错误代码：" + this.errorCode + ", 错误信息：" + this.errorMsg + "，微信原始报文：" + this.json;
    }

}
