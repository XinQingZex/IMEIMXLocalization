package com.agile.qdmp.standalong.controller.integrator;

import com.agile.qdmp.standalong.model.convert.integrator.DrawingConverter;
import com.agile.qdmp.standalong.model.convert.integrator.JobConverter;
import com.agile.qdmp.standalong.model.convert.integrator.PartConverter;
import com.agile.qdmp.standalong.model.dto.integrator.JobDTO;
import com.agile.qdmp.standalong.model.dto.integrator.PartDTO;
import com.agile.qdmp.standalong.model.dto.integrator.RequestDTO;
import com.agile.qdmp.standalong.model.dto.integrator.query.JobQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Drawing;
import com.agile.qdmp.standalong.service.integrator.IDrawingService;
import com.agile.qdmp.standalong.service.integrator.IJobService;
import com.agile.qdmp.standalong.service.integrator.IPartService;
import com.agile.qdmp.standalong.service.integrator.SetUtils;
import com.agile.tem.common.core.controller.SuperController;
import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.entity.integrator.Job;
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
 * JOB 前端控制器
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:43
 */
@Slf4j
@RestController
@RequestMapping("/api" )
@Api(value = "job", tags = "JOB管理")
public class JobController extends SuperController {

    private final IJobService jobService;
    private final IPartService partService;
    private final IDrawingService drawingService;
    private final SetUtils setUtils;

    public JobController(IJobService jobService, IPartService partService, IDrawingService drawingService, SetUtils setUtils) {
        this.jobService = jobService;
        this.partService = partService;
        this.drawingService = drawingService;
        this.setUtils = setUtils;
    }

    /**
     * 新增或修改JOB
     * @param params
     * @return
     */
    @ApiOperation(value = "新增或修改JOB", notes = "新增或修改JOB")
    @PostMapping("/job/set")
    public R<JobDTO> setJob(@RequestBody RequestDTO params) {
        log.debug("REST request to set Job : {}", params);
//        if (StringUtils.isBlank(params.getServerUri())) {
//            throw new BizException("缺少Server Uri");
//        }
        if (StringUtils.isBlank(params.getApiUri())) {
            throw new BizException("缺少Api Uri");
        }
        if (StringUtils.isBlank(params.getApiVersion())) {
            throw new BizException("缺少Api Version");
        }
        Job newData = setUtils.setJob(params);
        return Objects.isNull(newData) ? R.failed("添加失败") : R.ok(JobConverter.INSTANCE.to(newData), "添加成功");
    }

    /**
     * 新增JOB
     * @param jobDTO JOB
     * @return
     */
    @ApiOperation(value = "新增JOB", notes = "新增JOB")
    @PostMapping("/job")
    public R<JobDTO> createJob(@RequestBody JobDTO jobDTO) {
        log.debug("REST request to save Job : {}", jobDTO);
        if (jobDTO.getId() != null) {
            throw new BizException("不能包含主键");
        }

        Job newData = JobConverter.INSTANCE.from(jobDTO);
        Job data = jobService.lambdaQuery().eq(Job::getGuid, jobDTO.getGuid()).select(Job::getId).one();
        boolean result;
        if(data == null) {
            result = jobService.save(newData);
        } else {
            newData.setId(data.getId());
            result = jobService.updateById(newData);
        }
        if(result) {
            jobDTO.setId(newData.getId());
            return R.ok(jobDTO, "添加成功");
        } else {
            return R.failed(jobDTO, "添加失败");
        }
    }

    /**
     * 修改JOB
     * @param jobDTO JOB
     * @return
     */
    @ApiOperation(value = "修改JOB", notes = "修改JOB")
    @PutMapping("/job")
    public R<JobDTO> updateJob(@RequestBody JobDTO jobDTO) {
        log.debug("REST request to update Job : {}", jobDTO);
        if (jobDTO.getId() == null) {
            throw new BizException("缺少主键");
        }
        Job data = JobConverter.INSTANCE.from(jobDTO);
        return jobService.updateById(data) ? R.ok(jobDTO, "修改成功") : R.failed(jobDTO, "修改失败");
    }

//    /**
//     * 根据查询条件获取分页数据
//     * @param queryDTO JOB
//     * @return
//     */
//    @ApiOperation(value = "分页查询JOB", notes = "分页查询JOB")
//    // @JsonFieldFilter(type = JobDTO.class, include = {"id", ""})
//    // @JsonFieldFilter(type = JobDTO.class, exclude = {"id", ""})
//    // @JsonFieldFilter(type = IPage.class, exclude = {"optimizeCountSql", "hitCount", "orders", "searchCount"})
//    // @JsonFieldFilters( value = {
//    //     @JsonFieldFilter(type = JobDTO.class, include = {"id", "username", "mobile"}),
//    //     @JsonFieldFilter(type = JobDTO.class, include = {"id", "username", "mobile"})
//    // })
//    // @Desensitization
//    @GetMapping("/jobs")
//    public R<IPage<JobDTO>> getAllJobs(JobQueryDTO queryDTO) {
//        log.debug("REST request to get a page of Jobs");
//        String[] ascParameters = null;
//        String[] descParameters = null;
//        if(StringUtils.isNotBlank(queryDTO.getAscParameter())) {
//            ascParameters = new String[] {queryDTO.getAscParameter()};
//        }
//        if(StringUtils.isNotBlank(queryDTO.getDescParameter())) {
//            descParameters = new String[] {queryDTO.getDescParameter()};
//        }
//        IPage<JobDTO> result = jobService.lambdaQuery()
//                .or(StringUtils.isNotEmpty(queryDTO.getKeyWord()), o -> o.like(Job::getNumber, queryDTO.getKeyWord()).or().like(Job::getTitle, queryDTO.getKeyWord()).or())
////                .like(StringUtils.isNotEmpty(queryDTO.getName()), Job::getName, queryDTO.getName())
//                .eq(StringUtils.isNotEmpty(queryDTO.getGuid()), Job::getGuid, queryDTO.getGuid())
//                .eq(StringUtils.isNotEmpty(queryDTO.getPartName()), Job::getPartName, queryDTO.getPartName())
//                .eq(StringUtils.isNotEmpty(queryDTO.getPartNumber()), Job::getPartNumber, queryDTO.getPartNumber())
//                .eq(StringUtils.isNotEmpty(queryDTO.getPartRevision()), Job::getPartRevision, queryDTO.getPartRevision())
//                .eq(StringUtils.isNotEmpty(queryDTO.getPartGuid()), Job::getPartGuid, queryDTO.getPartGuid())
//                .eq(Objects.nonNull(queryDTO.getServerId()), Job::getServerId, queryDTO.getServerId())
//                .le(Job::getStatus, 4)
//                .page(this.<Job>getPage(queryDTO.getCursor(), queryDTO.getLimit(), queryDTO.getSearchCount(), ascParameters, descParameters))
//                .convert(dto -> JobConverter.INSTANCE.to(dto));
//        return R.ok(result, "查询列表成功");
//    }


    /**
     * 根据查询条件获取分页数据
     * @param queryDTO JOB
     * @return
     */
    @ApiOperation(value = "分页查询JOB", notes = "分页查询JOB")
    @GetMapping("/jobs")
    public R<List<JSONObject>> getAllJobs(JobQueryDTO queryDTO) {
        log.debug("REST request to get a page of Jobs {}", queryDTO);
        List<JSONObject> result = new ArrayList<>();
        if(StringUtils.isBlank(queryDTO.getPartName())) {
            result = jobService.listAll();
        } else {
            result = jobService.listByQuery(queryDTO);
        }
        return R.ok(result, "查询列表成功");
    }

    /**
     * 根据ID获取JOB
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id查询JOB", notes = "通过id查询JOB")
    @GetMapping("/job/{id}")
    public R<JobDTO> getJob(@PathVariable String id) {
        log.debug("REST request to get Job : {}", id);
        Job job = jobService.getById(id);
        return job == null ? R.failed("未查询到数据") : R.ok(JobConverter.INSTANCE.to(job), "查询成功");
    }

    @ApiOperation(value = "通过id查询JOB", notes = "通过id查询JOB")
    @PostMapping("/job/get")
    public R<JSONObject> getJob(@RequestBody JobQueryDTO queryDTO) {
        log.debug("REST request to get Job : {}", queryDTO);
        JSONObject job = jobService.getByJobId(queryDTO.getJobId());
        if (job != null) {
            if (Objects.nonNull(queryDTO.getWithPart()) && queryDTO.getWithPart()) {
                JSONObject part = partService.getByPartId(job.getString("JobPartID"));
                job.put("part", Objects.isNull(part) ? null : part);
//                if(Objects.nonNull(part)) {
//                    partDTO = PartConverter.INSTANCE.to(part);
//                    List<Drawing> ds = drawingService.lambdaQuery().eq(Drawing::getPartGuid, part.getGuid()).list();
//                    partDTO.setDrawings(DrawingConverter.INSTANCE.to(ds));
//                }
//                result.setPart(Objects.isNull(partDTO) ? null : partDTO);
            }
        }
        return job == null ? R.failed("未查询到数据") : R.ok(job, "查询成功");
    }

    /**
     * 通过id删除JOB
     * @param id id
     * @return
     */
    @ApiOperation(value = "通过id删除JOB", notes = "通过id删除JOB")
    @DeleteMapping("/job/{id}")
    public R deleteJob(@PathVariable Long id) {
        log.debug("REST request to delete Jobs : {}", id);
        return jobService.removeById(id) ? R.ok("删除成功") : R.failed("删除失败");
    }
}
