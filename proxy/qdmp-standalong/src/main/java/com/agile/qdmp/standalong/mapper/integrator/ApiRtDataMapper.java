package com.agile.qdmp.standalong.mapper.integrator;

import com.agile.qdmp.standalong.model.entity.integrator.ApiRtData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 接口数据
 *
 * @author hyzh code generator
 * @date 2022-12-09 09:37:49
 */
public interface ApiRtDataMapper extends BaseMapper<ApiRtData> {

    /**
     * 根据Type删除
     * @param type
     * @return
     */
    int removeByType(@Param("type") String type, @Param("source") String rtKey);

    /**
     * 清理多余数据
     */
    @Delete("delete from im_part where guid not in (select guid from im_api_rt_data where type=#{type})")
    int cleanPart(@Param("type") String apiUri);
    @Delete("delete from im_job where guid not in (select guid from im_api_rt_data where type=#{type})")
    int cleanJob(String apiUri);
    @Delete("delete from im_lot where guid not in (select guid from im_api_rt_data where type=#{type})")
    int cleanLot(String apiUri);
    @Delete("delete from im_sample where guid not in (select guid from im_api_rt_data where type=#{type})")
    int cleanSample(String apiUri);
    @Delete("delete from im_dimension where guid not in (select guid from im_api_rt_data where type=#{type})")
    int cleanDim(String apiUri);
    @Delete("delete from im_result where guid not in (select guid from im_api_rt_data where type=#{type})")
    int cleanResult(String apiUri);
    @Delete("delete from im_company where guid not in (select guid from im_api_rt_data where type=#{type})")
    int cleanCompany(String apiUri);
    @Delete("delete from im_receiver where guid not in (select guid from im_api_rt_data where type=#{type})")
    int cleanReceiver(String apiUri);
    @Delete("delete from im_insp_center where guid not in (select guid from im_api_rt_data where type=#{type})")
    int cleanInspCenter(String apiUri);
    @Delete("delete from im_ncr where guid not in (select guid from im_api_rt_data where type=#{type})")
    int cleanNcr(String apiUri);
    @Delete("delete from im_ncr_result where result_guid not in (select guid from im_api_rt_data where type=#{type})")
    int cleanNcrResult(String apiUri);
    @Delete("delete from im_contact where guid not in (select guid from im_api_rt_data where type=#{type})")
    int cleanContact(String apiUri);
    @Delete("delete from im_procedure where guid not in (select guid from im_api_rt_data where type=#{type})")
    int cleanProcedure(String apiUri);
    @Delete("delete from im_operation where guid not in (select guid from im_api_rt_data where type=#{type})")
    int cleanOperation(String apiUri);
    @Delete("delete from im_gage where guid not in (select guid from im_api_rt_data where type=#{type})")
    int cleanGage(String apiUri);
    @Delete("delete from im_drawing where guid not in (select guid from im_api_rt_data where type=#{type})")
    int cleanDrawing(String apiUri);

    /**
     * 清理Unchanged数据
     */
    @Delete("delete from im_api_rt_data where flag in (select flag from im_part where deleted=0)")
    int cleanUcPart();
    @Delete("delete from im_api_rt_data where flag in (select flag from im_job where deleted=0)")
    int cleanUcJob();
    @Delete("delete from im_api_rt_data where flag in (select flag from im_lot where deleted=0)")
    int cleanUcLot();
    @Delete("delete from im_api_rt_data where flag in (select flag from im_sample where deleted=0)")
    int cleanUcSample();
    @Delete("delete from im_api_rt_data where flag in (select flag from im_dimension where deleted=0)")
    int cleanUcDim();
    @Delete("delete from im_api_rt_data where flag in (select flag from im_result where deleted=0)")
    int cleanUcResult();
    @Delete("delete from im_api_rt_data where flag in (select failed_flag from im_result where deleted=0)")
    int cleanUcResultFailed();
    @Delete("delete from im_api_rt_data where flag in (select flag from im_company where deleted=0)")
    int cleanUcCompany();
    @Delete("delete from im_api_rt_data where flag in (select flag from im_receiver where deleted=0)")
    int cleanUcReceiver();
    @Delete("delete from im_api_rt_data where flag in (select flag from im_insp_center where deleted=0)")
    int cleanUcInspCenter();
    @Delete("delete from im_api_rt_data where flag in (select flag from im_ncr where deleted=0)")
    int cleanUcNcr();
    @Delete("delete from im_api_rt_data where flag in (select flag from im_ncr_result where deleted=0)")
    int cleanUcNcrResult();
    @Delete("delete from im_api_rt_data where flag in (select flag from im_contact where deleted=0)")
    int cleanUcContact();
    @Delete("delete from im_api_rt_data where flag in (select flag from im_procedure where deleted=0)")
    int cleanUcProcedure();
    @Delete("delete from im_api_rt_data where flag in (select flag from im_operation where deleted=0)")
    int cleanUcOperation();
    @Delete("delete from im_api_rt_data where flag in (select flag from im_gage where deleted=0)")
    int cleanUcGage();
    @Delete("delete from im_api_rt_data where flag in (select flag from im_drawing where deleted=0)")
    int cleanUcDrawing();

    @Delete("<script>" +
            "delete from im_api_rt_data where guid in " +
            "<foreach collection='ids' item='item' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")

    int delByIds(@Param("ids") List<String> removedIds);
}
