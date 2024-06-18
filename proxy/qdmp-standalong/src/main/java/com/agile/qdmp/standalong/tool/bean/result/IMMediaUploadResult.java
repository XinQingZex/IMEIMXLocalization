package com.agile.qdmp.standalong.tool.bean.result;

import com.agile.qdmp.standalong.tool.util.json.IMGsonBuilder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 11:35
 */
@Data
public class IMMediaUploadResult implements Serializable {
    private static final long serialVersionUID = 330834334738622341L;

    private String url;
    private String type;
    private String mediaId;
    private String thumbMediaId;
    private long createdAt;

    public static IMMediaUploadResult fromJson(String json) {
        return IMGsonBuilder.create().fromJson(json, IMMediaUploadResult.class);
    }

    @Override
    public String toString() {
        return IMGsonBuilder.create().toJson(this);
    }

}
