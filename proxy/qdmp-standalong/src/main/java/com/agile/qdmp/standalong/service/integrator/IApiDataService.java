package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.entity.integrator.ApiData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 接口数据
 *
 * @author hyzh code generator
 * @date 2022-12-09 09:37:49
 */
public interface IApiDataService extends IService<ApiData> {

    /**
     * 根据Type删除
     * @param type
     * @return
     */
    int removeByType(String type);

    /**
     * 与IM同步后，清理不存在的数据
     * @param apiUri
     */
    int cleanData(String apiUri);

    /**
     * 根据ID 物理删除
     * @param removedIds
     * @return
     */
    int delByIds(List<String> removedIds);
}
