package com.agile.qdmp.standalong.mapper.integrator;

import com.agile.qdmp.standalong.model.entity.integrator.ImApiRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * IM_API_RECORD
 *
 * @author hyzh code generator
 * @date 2022-09-19 09:56:55
 */
public interface ImApiRecordMapper extends BaseMapper<ImApiRecord> {

    /**
     * 整理JOB数据
     * @return
     */
    int normalizeJob();

    /**
     * 整理Part数据
     * @return
     */
    int normalizePartTotal();

    /**
     * 整理Part企业数据
     * @return
     */
    int normalizePartCompany();

    /**
     * 整理Dim数据
     * @return
     */
    int normalizeDim();
}
