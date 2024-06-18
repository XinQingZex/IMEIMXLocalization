package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.DrawingConverter;
import com.agile.qdmp.standalong.model.dto.integrator.ItemDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.ItemQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.*;
import com.agile.qdmp.standalong.service.integrator.*;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.util.R;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
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
@Api(value = "item", tags = "Item管理")
public class ItemController extends SuperController {

    private final IPartService partService;
    private final IJobService jobService;
    private final ILotService lotService;
    private final ISampleService sampleService;
    private final IImService imService;

    public ItemController(IPartService partService, IJobService jobService, ILotService lotService, ISampleService sampleService, IImService imService) {
        this.partService = partService;
        this.jobService = jobService;
        this.lotService = lotService;
        this.sampleService = sampleService;
        this.imService = imService;
    }

    /**
     * 根据查询条件获取分页数据
     *
     * @param queryDTO PART
     * @return
     */
    @ApiOperation(value = "分页搜索", notes = "分页搜索")
    @GetMapping("/search")
    public R<List<JSONObject>> search(@RequestParam HashMap<String, String> queryDTO) {
        log.debug("REST request to get a page of Items {}", queryDTO);
        List<JSONObject> result = imService.search(queryDTO);
        return R.ok(result, "查询列表成功");
    }


    /**
     * 根据查询条件获取分页数据
     *
     * @param queryDTO PART
     * @return
     */
    @ApiOperation(value = "分页搜索", notes = "分页搜索")
    @GetMapping("/scan")
    public R<ItemDTO> scan(ItemQueryDTO queryDTO) {
        log.debug("REST request to get a page of Items {}", queryDTO);

        if(StringUtils.isBlank(queryDTO.getKeyWord())) {
            return R.failed("未找到对应数据");
        }
        ItemDTO result = null;
        // part
        List<Part> parts = partService.lambdaQuery().or(StringUtils.isNotEmpty(queryDTO.getKeyWord()), o -> o.eq(Part::getPartName, queryDTO.getKeyWord()).or().eq(Part::getPartNumber, queryDTO.getKeyWord())).list();
        if(Objects.nonNull(parts) && parts.size() > 0) {
            Part part = parts.get(0);
            result = new ItemDTO();
            result.setItemId(part.getPartName());
            result.setItemName(part.getPartName());
            result.setItemType("零件");
            result.setItemDescription(part.getPartComments());
            result.setPartNumber(part.getPartNumber());
            result.setPartGuid(part.getGuid());
            return R.ok(result);
        }

        // job
        List<Job> jobs = jobService.lambdaQuery().eq(Job::getNumber, queryDTO.getKeyWord()).list();
        if(Objects.nonNull(jobs) && jobs.size() > 0) {
            Job job = jobs.get(0);
            result.setItemId(job.getNumber());
            result.setItemName(job.getTitle());
            result.setItemType("工单");
            result.setItemDescription(job.getTitle());
            result.setPartNumber(job.getPartGuid());
            result.setPartGuid(job.getPartGuid());
            result.setJobGuid(job.getGuid());
            return R.ok(result);
        }

        // lots
        List<Lot> lots = lotService.lambdaQuery().eq(Lot::getNumber, queryDTO.getKeyWord()).list();
        if(Objects.nonNull(lots) && lots.size() > 0) {
            Lot lot = lots.get(0);
            result.setItemId(lot.getNumber());
            result.setItemName(lot.getNumber());
            result.setItemType("批次");
            result.setItemDescription(lot.getNumber());
            result.setPartNumber(lot.getPartGuid());
            result.setPartGuid(lot.getPartGuid());
            result.setJobGuid(lot.getJobGuid());
            result.setLotGuid(lot.getGuid());
            return R.ok(result);
        }

        // samples
        List<Sample> samples = sampleService.lambdaQuery().eq(Sample::getSerialNumber, queryDTO.getKeyWord()).list();
        if(Objects.nonNull(samples) && samples.size() > 0) {
            Sample sample = samples.get(0);
            result.setItemId(sample.getSerialNumber());
            result.setItemName(sample.getSerialNumber());
            result.setItemType("样品");
            result.setItemDescription(sample.getSerialNumber());
            result.setPartNumber(sample.getPartGuid());
            result.setPartGuid(sample.getPartGuid());
            result.setJobGuid(sample.getJobGuid());
            result.setLotGuid(sample.getLotGuid());
            result.setSampleGuid(sample.getGuid());
            return R.ok(result);
        }
        return R.failed("未找到对应数据");
    }

}
