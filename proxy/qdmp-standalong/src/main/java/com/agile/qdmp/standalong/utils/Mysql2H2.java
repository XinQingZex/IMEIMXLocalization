package com.agile.qdmp.standalong.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: wenbinglei
 * @Date: 2022/11/9 08:44
 * @Description:
 */
public class Mysql2H2 {
    public static void main(String[] args) {
        try {
            System.out.println(convert("C:\\Users\\Administrator\\Downloads\\db_qdmp.sql"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static String convert(String filePath) throws IOException {
        String[] rawSQL = new String(Files.readAllBytes(Paths.get(filePath))).split("\\n");
        StringBuilder builder = new StringBuilder();

        for(String line : rawSQL) {
            if(line.contains("CHARACTER SET utf8 COLLATE utf8_general_ci")) {
                line = line.replaceAll("CHARACTER SET utf8 COLLATE utf8_general_ci", "");
            } else if(line.contains("INDEX")) {
                continue;
            } else if(line.contains("IF NOT EXISTS")) {
                line = line.replaceAll("IF NOT EXISTS", "");
            } else if(line.contains("--")) {
                continue;
            } else if(line.contains("ENGINE")) {
                line = line.replaceAll("\\).*ENGINE.*(?=)", ");");
            }else if (line.contains("USING BTREE")){
                line = line.replaceAll("USING BTREE*","");
            }

            line = line.replace("`", "");
            builder.append(line).append("\n");
        }
        return builder.toString();
    }
}
