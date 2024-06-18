package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.config.SysConfig;
import com.agile.qdmp.standalong.model.convert.integrator.DrawingConverter;
import com.agile.qdmp.standalong.model.dto.integrator.DrawingDTO;
import com.agile.qdmp.standalong.model.dto.integrator.RequestDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Drawing;
import com.agile.qdmp.standalong.service.integrator.IDrawingService;
import com.agile.qdmp.standalong.service.integrator.IImApiRecordService;
import com.agile.qdmp.standalong.tool.api.IMService;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Description:
 * @Author: wenbinglei
 * @Date: 2021/7/22 11:15
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/proxy")
public class ProxyController {
    private final IMService imService;
    private final IImApiRecordService imApiRecordService;
    private final IDrawingService drawingService;
//    private final IntegratorFeignClient integratorFeignClient;
//    private final static String dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'0000'XXX";
    private final static String ACCESS_TOKEN_KEY = "AccessToken";

    @Autowired
    private SysConfig sysConfig;

    /**
     * 测试Server
     * @param params 参数
     * @return
     */
    @ApiOperation(value = "测试Server", notes = "测试Server")
    @PostMapping("/ping")
    public R<JSONObject> ping(@RequestBody RequestDTO params) {
        params.setServerUri(sysConfig.getServerUri());
//        if (StringUtils.isBlank(params.getServerUri())) {
//            throw new BizException("缺少Server Uri");
//        }
        if (StringUtils.isBlank(params.getApiUri())) {
            throw new BizException("缺少Api Uri");
        }
        try {
            String result = imService.get(params.getServerUri() + params.getApiUri(), "");
            return R.ok(JSONObject.parseObject(result));
        } catch (IMErrorException e) {
            log.error(e.getMessage());
            return R.failed(e.getMessage());
        }
    }

    /**
     * 登录Server
     * @param params 参数
     * @return
     */
    @ApiOperation(value = "登录Server", notes = "登录Server")
    @PostMapping("/auth")
    public R<JSONObject> auth(@RequestBody RequestDTO params) {
//        if (StringUtils.isBlank(params.getServerUri())) {
//            throw new BizException("缺少Server Uri");
//        }
        params.setServerUri(sysConfig.getServerUri());
        if (Objects.isNull(params.getServerId())) {
            throw new BizException("缺少Server ID");
        }
        if (StringUtils.isBlank(params.getApiUri())) {
            throw new BizException("缺少Api Uri");
        }
        if (StringUtils.isBlank(params.getApiVersion())) {
            throw new BizException("缺少Api Version");
        }
        try {
            String apiResult = imService.post(params.getServerUri() + params.getApiUri(), params.getToken(), params.getData());
            JSONObject res = JSONObject.parseObject(apiResult);
            if(res.containsKey(ACCESS_TOKEN_KEY) && StringUtils.isNotBlank(res.getString(ACCESS_TOKEN_KEY))) {
                imApiRecordService.synchronizeServerData(params.getServerId(), params.getServerUri(), res.getString(ACCESS_TOKEN_KEY), null, null, false, null);
            }
            return R.ok(res);
        } catch (IMErrorException e) {
            log.error(e.getMessage());
            return R.failed(e.getMessage());
        }
    }

    /**
     * 同步数据
     * @param params 参数
     * @return
     */
    @ApiOperation(value = "同步数据", notes = "同步数据")
    @PostMapping("/synchronize")
    public R<Boolean> synchronize(@RequestBody RequestDTO params) {
        params.setServerUri(sysConfig.getServerUri());
//        if (StringUtils.isBlank(params.getServerUri())) {
//            throw new BizException("缺少Server Uri");
//        }
        if (StringUtils.isBlank(params.getToken())) {
            throw new BizException("当前登录状态失效");
        }
        imApiRecordService.synchronizeServerData(params.getServerId(), params.getServerUri(), params.getToken(), params.getType(), params.getData(), params.getRealTime(), params.getRtKey());
        return R.ok();
    }

    /**
     * request
     * @param params 参数
     * @return
     */
    @ApiOperation(value = "发送请求", notes = "发送请求")
    @PostMapping("/handle")
    public R<JSONObject> handle(@RequestBody RequestDTO params) {
        log.debug("REST request to handle : {}", params);
        params.setServerUri(sysConfig.getServerUri());
//        if (StringUtils.isBlank(params.getServerUri())) {
//            throw new BizException("缺少Server Uri");
//        }
        if (StringUtils.isBlank(params.getApiUri())) {
            throw new BizException("缺少Api Uri");
        }
        if (StringUtils.isBlank(params.getApiVersion())) {
            throw new BizException("缺少Api Version");
        }
        return imApiRecordService.handle(params);
    }

    /**
     * request
     * @param params 参数
     * @return
     */
    @ApiOperation(value = "下载文件", notes = "下载文件")
    @PostMapping("/handle/download/image")
    public R<DrawingDTO> handleDownloadToImage(@RequestBody RequestDTO params) {
        log.info("handleDownloadToImage {}", params);
        params.setServerUri(sysConfig.getServerUri());
//        if (StringUtils.isBlank(params.getServerUri())) {
//            throw new BizException("缺少Server Uri");
//        }
        if (StringUtils.isBlank(params.getApiUri())) {
            throw new BizException("缺少Api Uri");
        }
        if (StringUtils.isBlank(params.getApiVersion())) {
            throw new BizException("缺少Api Version");
        }
        if (StringUtils.isBlank(params.getData().getString("GUID"))) {
            throw new BizException("缺少DrawingFile GUID");
        }

        Drawing currentDrawing = JSONObject.toJavaObject(params.getData(), Drawing.class);
        currentDrawing.setCompanyId(params.getCompanyId());
        currentDrawing.setCompanyName(params.getCompanyName());
        currentDrawing.setServerId(params.getServerId());
        currentDrawing.setServerName(params.getServerName());
        currentDrawing = drawingService.completeDrawingData(currentDrawing, params.getServerUri(), params.getToken());

        return currentDrawing == null ? R.failed("连接Server超时，请重试") : R.ok(DrawingConverter.INSTANCE.to(currentDrawing));
    }

}
