package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.service.integrator.*;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.util.R;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


/**
 * Item 前端控制器
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:11
 */
@Slf4j
@RestController
@RequestMapping("/api")
@Api(value = "im", tags = "IM数据查询管理")
public class ImController extends SuperController {

    private final IImService imService;

    public ImController(IImService imService) {
        this.imService = imService;
    }

    /**
     * 根据查询条件获取Project数据
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "根据查询条件获取Project数据", notes = "根据查询条件获取Project数据")
    @GetMapping("/project/list")
    public R<List<JSONObject>> getProjects(HashMap<String, String> queryDTO) {
        List<JSONObject> result = imService.listProjectsByQuery(queryDTO);
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据查询条件获取Operations数据
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "根据查询条件获取Operations数据", notes = "根据查询条件获取Operations数据")
    @GetMapping("/prc/list")
    public R<List<JSONObject>> getOperations(@RequestParam HashMap<String, String> queryDTO) {
        log.info("getOperations queryDTO {}", queryDTO);
        List<JSONObject> result = imService.listOperationsByQuery(queryDTO);
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据查询条件获取AQL数据
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "根据查询条件获取AQL数据", notes = "根据查询条件获取AQL数据")
    @GetMapping("/aqlHeader/list")
    public R<List<JSONObject>> getAQLHeaders(@RequestParam HashMap<String, String> queryDTO) {
        log.info("aqlHeaders queryDTO {}", queryDTO);
        List<JSONObject> result = imService.listAQLHeaderByQuery(queryDTO);
        return R.ok(result, "查询列表成功");
    }
}
