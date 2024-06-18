package com.agile.qdmp.standalong.model.convert.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.JobDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Job;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * JOB 数据转换
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:43
 */
@Mapper
public interface JobConverter extends Convert<Job, JobDTO> {
    JobConverter INSTANCE = Mappers.getMapper(JobConverter.class);
}
