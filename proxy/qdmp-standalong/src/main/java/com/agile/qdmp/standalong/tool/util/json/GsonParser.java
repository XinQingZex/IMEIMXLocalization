package com.agile.qdmp.standalong.tool.util.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.Reader;

/**
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 11:29
 */
public class GsonParser {
  private static final JsonParser JSON_PARSER = new JsonParser();

  public static JsonObject parse(String json) {
    return JSON_PARSER.parse(json).getAsJsonObject();
  }

  public static JsonObject parse(Reader json) {
    return JSON_PARSER.parse(json).getAsJsonObject();
  }

  public static JsonObject parse(JsonReader json) {
    return JSON_PARSER.parse(json).getAsJsonObject();
  }
}
