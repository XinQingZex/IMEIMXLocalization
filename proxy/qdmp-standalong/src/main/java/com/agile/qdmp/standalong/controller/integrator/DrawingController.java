package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.DrawingConverter;
import com.agile.qdmp.standalong.model.dto.integrator.DrawingDTO;
import com.agile.qdmp.standalong.model.dto.integrator.RequestDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.DrawingQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Part;
import com.agile.qdmp.standalong.service.integrator.IDrawingService;
import com.agile.qdmp.standalong.service.integrator.IImApiRecordService;
import com.agile.qdmp.standalong.service.integrator.IPartService;
import com.agile.tem.common.core.util.R;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.qdmp.standalong.model.entity.integrator.Drawing;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * DRAWING 前端控制器
 *
 * @author wenbinglei
 * @date 2022-10-22 15:03:30
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "drawing", tags = "DRAWING管理")
public class DrawingController extends SuperController {

    private final IDrawingService drawingService;
    private final IPartService partService;
    private final IImApiRecordService imApiRecordService;

    public DrawingController(IDrawingService drawingService, IPartService partService, IImApiRecordService imApiRecordService) {
        this.drawingService = drawingService;
        this.partService = partService;
        this.imApiRecordService = imApiRecordService;
    }

    /**
     * 修改DRAWING
     * @param drawingDTO DRAWING
     * @return
     */
    @ApiOperation(value = "修改DRAWING", notes = "修改DRAWING")
    @PutMapping("/drawing")
    public R<DrawingDTO> updateDrawing(@RequestBody DrawingDTO drawingDTO) {
        log.debug("REST request to update Drawing : {}", drawingDTO);
        if (drawingDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Drawing data = DrawingConverter.INSTANCE.from(drawingDTO);
        return drawingService.updateById(data) ? R.ok(drawingDTO, "修改成功") : R.failed(drawingDTO, "修改失败");
    }

    /**
     * 获取文件列表
     * @param params
     * @return
     */
    @ApiOperation(value = "获取文件列表", notes = "获取文件列表")
    @PostMapping("/drawings/files")
    public R<List<JSONObject>> fetchFileList(@RequestBody HashMap<String, String> params) {
        log.debug("获取文件列表 {}", params);
        List<JSONObject> result = drawingService.fetchFileList(params);
        return R.ok(result, "查询列表成功");
    }

    /**
     * 获取文件
     * @param id
     * @return
     */
    @ApiOperation(value = "获取文件列表", notes = "获取文件列表")
    @GetMapping("/drawings/file/dim/{id}")
    public R<JSONObject> fetchDimFile(@PathVariable Long id) {
        log.debug("获取文件 {}", id);
        JSONObject result = drawingService.fetchDimFileList(id);

        List<String> sizes = parseImageSize(result.getString("DrawingComments"));
        if(sizes.size() == 4) {
            result.put("imageWidth", Integer.parseInt(sizes.get(0)));
            result.put("imageHeight", Integer.parseInt(sizes.get(1)));
            result.put("drawingWidth", Integer.parseInt(sizes.get(2)));
            result.put("drawingHeight", Integer.parseInt(sizes.get(3)));
        }

        return R.ok(result, "查询列表成功");
    }

    /**
     * 过滤notes中的图纸尺寸
     * @param content
     * @return
     */
    private List<String> parseImageSize(String content){
        // 编写正则表达式
        String regFileName = "(\\d+)\\sx\\s(\\d+)\\spx";
        // 匹配当前正则表达式
        Matcher matcher = Pattern.compile(regFileName).matcher(content);
        List<String> sizes = new ArrayList<String>();
        while(matcher.find()) {
            sizes.add(matcher.group(1));
            sizes.add(matcher.group(2));
        }
        return sizes;
    }

    /**
     * 获取Drawing列表（无PartGUID版本）
     * @param params
     * @return
     */
    @ApiOperation(value = "获取Drawing列表（无PartGUID版本）", notes = "获取Drawing列表（无PartGUID版本）")
    @PostMapping("/drawings/noPartGUID")
    public R<List<DrawingDTO>> getAllDrawingsNoPartGUID(@RequestBody RequestDTO params) {
        log.debug("REST request to get a page of 获取Drawing列表（无PartGUID版本） {}", params);
        if (StringUtils.isBlank(params.getServerUri())) {
            throw new BizException("缺少Server Uri");
        }
        if (StringUtils.isBlank(params.getApiUri())) {
            throw new BizException("缺少Api Uri");
        }

        JSONObject partJson = params.getData();
        List<Part> partList = partService.lambdaQuery().eq(Part::getHandleState, false).eq(Part::getPartName, partJson.getString("partName")).eq(Part::getPartNumber, partJson.getString("partNumber")).eq(Part::getPartRevisionLevel, partJson.getString("partRevisionLevel")).select(Part::getGuid).list();

        JSONObject drawingParams = new JSONObject();
        List<DrawingDTO> result = new ArrayList<>();
        for(Part part : partList) {
            drawingParams.put("PartGUID", part.getGuid());
            params.setData(drawingParams);
            R<JSONObject> drawingRes = imApiRecordService.handle(params);
            if(drawingRes.getCode() == 0) {
                List<Drawing> drawings = JSONArray.parseArray(drawingRes.getData().getJSONArray("Drawings").toJSONString(), Drawing.class);
                for(Drawing drawing : drawings) {
                    drawingService.lambdaUpdate().set(Drawing::getPartGuid, part.getGuid()).eq(Drawing::getGuid, drawing.getGuid()).update();
                    result.add(DrawingConverter.INSTANCE.to(drawingService.lambdaQuery().eq(Drawing::getGuid, drawing.getGuid()).one()));
                }
            }
        }
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO DRAWING
     * @return
     */
    @ApiOperation(value = "分页查询DRAWING", notes = "分页查询DRAWING")
    // @JsonFieldFilter(type = DrawingDTO.class, include = {"id", ""})
    // @JsonFieldFilter(type = DrawingDTO.class, exclude = {"id", ""})
    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
    // @JsonFieldFilters( value = {
    //     @JsonFieldFilter(type = DrawingDTO.class, include = {"id", "username", "mobile"}),
    //     @JsonFieldFilter(type = DrawingDTO.class, include = {"id", "username", "mobile"})
    // })
    // @Desensitization
    @GetMapping("/drawings")
    public R<IPage<DrawingDTO>> getAllDrawings(DrawingQueryDTO queryDTO) {
        log.debug("REST request to get a page of Drawings");
        String[] ascParameters = null;
        String[] descParameters = null;
        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
            ascParameters = new String[] {queryDTO.getAscParameter()};
        }
        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
            descParameters = new String[] {queryDTO.getDescParameter()};
        }
        IPage<DrawingDTO> result = drawingService.lambdaQuery()
                .like(StringUtils.isNotEmpty(queryDTO.getKeyWord()), Drawing::getTitle, queryDTO.getKeyWord())
                .eq(StringUtils.isNotEmpty(queryDTO.getPartGuid()), Drawing::getPartGuid, queryDTO.getPartGuid())
                .eq(Objects.nonNull(queryDTO.getServerId()), Drawing::getServerId, queryDTO.getServerId())
                .page(this.<Drawing>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
                .convert(dto -> DrawingConverter.INSTANCE.to(dto));
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据GUID或DrawingGUID获取DRAWING
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "根据GUID或DrawingGUID获取DRAWING", notes = "根据GUID或DrawingGUID获取DRAWING")
    @PostMapping("/drawing/get")
    public R<DrawingDTO> getDrawing(@RequestBody DrawingQueryDTO queryDTO) {
        log.debug("REST request to get Drawing queryDTO : {}", queryDTO);
        Drawing drawing = null;
        if(StringUtils.isNotBlank(queryDTO.getDrawingFile())) {
            drawing = drawingService.lambdaQuery().eq(Drawing::getDrawingFile, queryDTO.getDrawingFile()).one();
        }

        if(StringUtils.isNotBlank(queryDTO.getGuid())) {
            drawing = drawingService.lambdaQuery().eq(Drawing::getGuid, queryDTO.getGuid()).one();
        }

        return drawing == null ? R.failed("未查询到数据") : R.ok(DrawingConverter.INSTANCE.to(drawing), "查询成功");
    }

    /**
     * 通过id删除DRAWING
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除DRAWING", notes = "通过id删除DRAWING")
    @DeleteMapping("/drawing/{id}")
    public R deleteDrawing(@PathVariable Long id) {
        log.debug("REST request to delete Drawings : {}", id);
        return drawingService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
