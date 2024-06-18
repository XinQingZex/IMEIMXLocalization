package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.RequestDTO;
import com.agile.qdmp.standalong.model.entity.integrator.ImApiRecord;
import com.agile.tem.common.core.util.R;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * IM_API_RECORD
 *
 * @author hyzh code generator
 * @date 2022-09-19 09:56:55
 */
public interface IImApiRecordService extends IService<ImApiRecord> {

//    /**
//     * 异步同步Server端数据
//     * @param serverId
//     * @param serverUri
//     * @param accessToken
//     * @param type
//     * @param params
//     * @return
//     */
//    Boolean synchronizeServerData(Long serverId, String serverUri, String accessToken, String type, JSONObject params, Boolean force);

    /**
     * 实时同步Server端数据
     * @param serverId
     * @param serverUri
     * @param accessToken
     * @param type
     * @param params
     * @param realtime
     * @param rtKey
     * @return
     */
    Boolean synchronizeServerData(Long serverId, String serverUri, String accessToken, String type, JSONObject params, Boolean realtime, String rtKey);

    /**
     * request
     * @param params
     * @return
     */
    R<JSONObject> handle(RequestDTO params);

    /**
     * 整理Dim数据，IME登录使用
     * @param serverUri
     * @param apiUri
     * @param accessToken
     * @return
     */
    Boolean normalizeDimData(String serverUri, String apiUri, String accessToken, String partGuid);

}
