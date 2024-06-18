package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.DrawingConverter;
import com.agile.qdmp.standalong.model.convert.integrator.JobConverter;
import com.agile.qdmp.standalong.model.convert.integrator.LotConverter;
import com.agile.qdmp.standalong.model.convert.integrator.PartConverter;
import com.agile.qdmp.standalong.model.dto.integrator.LotDTO;
import com.agile.qdmp.standalong.model.dto.integrator.PartDTO;
import com.agile.qdmp.standalong.model.dto.integrator.RequestDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.LotQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Drawing;
import com.agile.qdmp.standalong.service.integrator.*;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.Job;
import com.agile.qdmp.standalong.model.entity.integrator.Lot;
import com.agile.qdmp.standalong.model.entity.integrator.Part;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * LOT 前端控制器
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:16
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "lot", tags = "LOT管理")
public class LotController extends SuperController {

    private final ILotService lotService;
    private final IJobService jobService;
    private final IPartService partService;
    private final IDrawingService drawingService;
    private final SetUtils setUtils;

    public LotController(ILotService lotService, IJobService jobService, IPartService partService, IDrawingService drawingService, SetUtils setUtils) {
        this.lotService = lotService;
        this.jobService = jobService;
        this.partService = partService;
        this.drawingService = drawingService;
        this.setUtils = setUtils;
    }

    /**
     * 新增或修改Lot
     * @param params
     * @return
     */
    @ApiOperation(value = "新增或修改Lot", notes = "新增或修改Lot")
    @PostMapping("/lot/set")
    public R<LotDTO> setLot(@RequestBody RequestDTO params) {
        log.debug("REST request to set Lot : {}", params);
        if (StringUtils.isBlank(params.getServerUri())) {
            throw new BizException("缺少Server Uri");
        }
        if (StringUtils.isBlank(params.getApiUri())) {
            throw new BizException("缺少Api Uri");
        }
        if (StringUtils.isBlank(params.getApiVersion())) {
            throw new BizException("缺少Api Version");
        }
        Lot newData = setUtils.setLot(params);
        return Objects.isNull(newData) ? R.failed("添加失败") : R.ok(LotConverter.INSTANCE.to(newData), "添加成功");
    }

    /**
     * 新增LOT
     * @param lotDTO LOT
     * @return
     */
    @ApiOperation(value = "新增LOT", notes = "新增LOT")
    @PostMapping("/lot")
    public R<LotDTO> createLot(@RequestBody LotDTO lotDTO) {
        log.debug("REST request to save Lot : {}", lotDTO);
        if (lotDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }
        Lot newData = LotConverter.INSTANCE.from(lotDTO);
        Lot data = lotService.lambdaQuery().eq(Lot::getGuid, lotDTO.getGuid()).select(Lot::getId).one();
        boolean result;
        if(data == null) {
            result = lotService.save(newData);
        } else {
            newData.setId(data.getId());
            result = lotService.updateById(newData);
        }
        if(result) {
            lotDTO.setId(newData.getId());
            return R.ok(lotDTO, "添加成功");
        } else {
            return R.failed(lotDTO, "添加失败");
        }
    }

    /**
     * 修改LOT
     * @param lotDTO LOT
     * @return
     */
    @ApiOperation(value = "修改LOT", notes = "修改LOT")
    @PutMapping("/lot")
    public R<LotDTO> updateLot(@RequestBody LotDTO lotDTO) {
        log.debug("REST request to update Lot : {}", lotDTO);
        if (lotDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Lot data = LotConverter.INSTANCE.from(lotDTO);
        return lotService.updateById(data) ? R.ok(lotDTO, "修改成功") : R.failed(lotDTO, "修改失败");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO LOT
     * @return
     */
    @ApiOperation(value = "分页查询LOT", notes = "分页查询LOT")
    @GetMapping("/lots")
    public R<List<JSONObject>> getAllLots(LotQueryDTO queryDTO) {
        List<JSONObject> result = lotService.listByQuery(queryDTO);
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据查询条件获取分页数据
     * @param queryDTO LOT
     * @return
     */
    @ApiOperation(value = "分页查询LOT", notes = "分页查询LOT")
    @GetMapping("/lots/byContactId")
    public R<List<JSONObject>> getAllLotsByContactId(LotQueryDTO queryDTO) {
        List<JSONObject> result = lotService.listByContactId(queryDTO);
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取LOT
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询LOT", notes = "通过id查询LOT")
    @GetMapping("/lot/{id}")
    public R<LotDTO> getLot(@PathVariable Long id) {
        log.debug("REST request to get Lot : {}", id);
        Lot lot = lotService.getById(id);
        return lot == null ? R.failed("未查询到数据") : R.ok(LotConverter.INSTANCE.to(lot), "查询成功");
    }

    @ApiOperation(value = "通过id查询LOT", notes = "通过id查询LOT")
    @PostMapping("/lot/get")
    public R<JSONObject> getLot(@RequestBody LotQueryDTO queryDTO) {
        log.debug("REST request to get Lot : {}", queryDTO);
        JSONObject lot = lotService.getByLotId(queryDTO.getLotId());
        if (lot != null) {
            if (Objects.nonNull(queryDTO.getWithJob()) && queryDTO.getWithJob()) {
                JSONObject job = jobService.getByJobId(lot.getString("LotJobID"));
                if(Objects.nonNull(job)) {
                    lot.put("job", Objects.isNull(job) ? null : job);
                    if (Objects.nonNull(queryDTO.getWithPart())) {
                        JSONObject part = partService.getByPartId(job.getString("JobPartID"));
                        lot.put("part", Objects.isNull(part) ? null : part);
                    }
                }
            }
//            if (Objects.nonNull(queryDTO.getWithPart()) && queryDTO.getWithPart()) {
//                JSONObject part = partService.getByPartId(lot.getString("JobPartID"));
//                lot.put("part", Objects.isNull(part) ? null : part);
////                Part part = partService.lambdaQuery().eq(Part::getGuid, lot.getPartGuid()).one();
////                PartDTO partDTO = null;
////                if(Objects.nonNull(part)) {
////                    partDTO = PartConverter.INSTANCE.to(part);
////                    List<Drawing> ds = drawingService.lambdaQuery().eq(Drawing::getPartGuid, part.getGuid()).list();
////                    partDTO.setDrawings(DrawingConverter.INSTANCE.to(ds));
////                }
////                result.setPart(Objects.isNull(partDTO) ? null : partDTO);
//            }
        }
        return lot == null ? R.failed("未查询到数据") : R.ok(lot, "查询成功");
    }

    /**
     * 通过id删除LOT
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除LOT", notes = "通过id删除LOT")
    @DeleteMapping("/lot/{id}")
    public R deleteLot(@PathVariable Long id) {
        log.debug("REST request to delete Lots : {}", id);
        return lotService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
