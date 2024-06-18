package com.agile.qdmp.standalong.mapper.integrator;

import com.agile.qdmp.standalong.model.entity.integrator.Drawing;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * DRAWING
 *
 * @author wenbinglei
 * @date 2022-10-22 15:03:30
 */
public interface DrawingMapper extends BaseMapper<Drawing> {

    /**
     * 获取part文件
     * @param id
     * @return
     */
    @Select({
            "<script>",
            "SELECT" +
            " Parts.ID AS FilesObjectID," +
            " Files.ID," +
            " Files.GlobalID," +
            " Files.Name," +
            " Files.OrigExt," +
            " Files.Description," +
            " Files.FullPath" +
            " FROM" +
            " Objects_Parts AS Parts" +
            " INNER JOIN Core_Files AS Files ON Files.FolderID = Parts.PartPhoto " +
            " WHERE Parts.ID = #{id} " +
            "    AND Parts.PartFiles > 0 " +
            "</script>"
    })
    List<JSONObject> fetchPartFiles(String id);

    /**
     * 获取图纸完整信息
     * @param id
     * @return
     */
    JSONObject fetchDimFileList(Long id);

    /**
     * 查询DIM所有图纸信息
     * @param id
     * @return
     */
    @Select({
    "<script>",
    "    select dw.* from Objects_Drawings dw where dw.DrawingPartID=#{id}" +
    "</script>"
    })
    List<JSONObject> fetchDimFiles(String id);
}
