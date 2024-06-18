package com.agile.qdmp.standalong.tool.util.json;

import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;

/**
 * <pre>
 * Gson 日期类型转换器
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 11:32
 */
public class IMDateTypeAdapter extends TypeAdapter<Date> {
    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value.getTime() / 1000);
        }
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        switch (peek) {
            case NULL:
                in.nextNull();
                return null;
            case NUMBER:
                return new Date(in.nextInt() * 1000);
            default:
                throw new JsonParseException("Expected NUMBER but was " + peek);
        }
    }
}
