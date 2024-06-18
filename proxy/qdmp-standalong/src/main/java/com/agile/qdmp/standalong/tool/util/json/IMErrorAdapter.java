package com.agile.qdmp.standalong.tool.util.json;

import com.agile.qdmp.standalong.tool.error.IMError;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 11:33
 */
public class IMErrorAdapter implements JsonDeserializer<IMError> {

    @Override
    public IMError deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        IMError.IMErrorBuilder errorBuilder = IMError.builder();
        JsonObject wxErrorJsonObject = json.getAsJsonObject();

        if (wxErrorJsonObject.get("errcode") != null && !wxErrorJsonObject.get("errcode").isJsonNull()) {
            errorBuilder.errorCode(GsonHelper.getAsPrimitiveInt(wxErrorJsonObject.get("errcode")));
        }
        if (wxErrorJsonObject.get("errmsg") != null && !wxErrorJsonObject.get("errmsg").isJsonNull()) {
            errorBuilder.errorMsg(GsonHelper.getAsString(wxErrorJsonObject.get("errmsg")));
        }

        errorBuilder.json(json.toString());

        return errorBuilder.build();
    }

}
