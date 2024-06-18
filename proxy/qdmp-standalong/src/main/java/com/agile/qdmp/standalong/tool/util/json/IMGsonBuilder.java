package com.agile.qdmp.standalong.tool.util.json;

import com.agile.qdmp.standalong.tool.bean.result.IMMediaUploadResult;
import com.agile.qdmp.standalong.tool.error.IMError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

/**
 * @Author: wenbinglei
 * @Date: 2022/9/6 16:23
 * @Description:
 */
public class IMGsonBuilder {
    private static final GsonBuilder INSTANCE = new GsonBuilder();
    private static volatile Gson GSON_INSTANCE;

    static {
        INSTANCE.disableHtmlEscaping();
//        INSTANCE.registerTypeAdapter(IMAccessToken.class, new IMAccessTokenAdapter());
        INSTANCE.registerTypeAdapter(IMError.class, new IMErrorAdapter());
//        INSTANCE.registerTypeAdapter(WxMenu.class, new WxMenuGsonAdapter());
        INSTANCE.registerTypeAdapter(IMMediaUploadResult.class, new IMMediaUploadResultAdapter());

    }

    public static Gson create() {
        if (Objects.isNull(GSON_INSTANCE)) {
            synchronized (INSTANCE) {
                if (Objects.isNull(GSON_INSTANCE)) {
                    GSON_INSTANCE = INSTANCE.create();
                }
            }
        }
        return GSON_INSTANCE;
    }

}
