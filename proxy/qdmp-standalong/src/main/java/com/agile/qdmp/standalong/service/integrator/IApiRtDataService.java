package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.entity.integrator.ApiRtData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 接口数据
 *
 * @author hyzh code generator
 * @date 2022-12-09 09:37:49
 */
public interface IApiRtDataService extends IService<ApiRtData> {

    /**
     * 根据Type删除
     * @param type
     * @return
     */
    int removeByType(String type, String rtKey);

    /**
     * 与IM同步后，清理不存在的数据
     * @param apiUri
     */
    int cleanData(String apiUri);

    /**
     * 与IM同步后，清理未发生改变的数据
     * @param apiUri
     */
    int cleanUcData(String apiUri);

    /**
     * 物理删除
     * @param removedIds
     * @return
     */
    int delByIds(List<String> removedIds);
}
