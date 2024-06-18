package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.ApiRtDataMapper;
import com.agile.qdmp.standalong.model.entity.integrator.ApiRtData;
import com.agile.qdmp.standalong.model.enums.ApiEnum;
import com.agile.qdmp.standalong.service.integrator.IApiRtDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 接口数据 服务类
 *
 * @author hyzh code generator
 * @date 2022-12-09 09:37:49
 */
@Service
public class ApiRtDataServiceImpl extends ServiceImpl<ApiRtDataMapper, ApiRtData> implements IApiRtDataService {

    @Override
    public int removeByType(String type, String rtKey) {
        return baseMapper.removeByType(type, rtKey);
    }

    @Override
    public int cleanData(String apiUri) {
        Integer result = 0;
        switch (ApiEnum.getByDesc(apiUri)) {
            case PART:
                result = baseMapper.cleanPart(apiUri);
                break;
            case JOB:
                result = baseMapper.cleanJob(apiUri);
                break;
            case LOT:
                result = baseMapper.cleanLot(apiUri);
                break;
            case SAMPLE:
                result = baseMapper.cleanSample(apiUri);
                break;
            case DIM:
                result = baseMapper.cleanDim(apiUri);
                break;
            case RESULT:
                result = baseMapper.cleanResult(apiUri);
                break;
            case FAILED_RESULT:
                break;
            case COMPANY:
                result = baseMapper.cleanCompany(apiUri);
                break;
            case RECEIVER:
                result = baseMapper.cleanReceiver(apiUri);
                break;
            case INSPCENTER:
                result = baseMapper.cleanInspCenter(apiUri);
                break;
            case NCR:
                result = baseMapper.cleanNcr(apiUri);
                break;
            case NCR_RESULT:
                result = baseMapper.cleanNcrResult(apiUri);
                break;
            case CONTACT:
                result = baseMapper.cleanContact(apiUri);
                break;
            case PROCEDURE:
                result = baseMapper.cleanProcedure(apiUri);
                break;
            case OPERATION:
                result = baseMapper.cleanOperation(apiUri);
                break;
            case GAGE:
                result = baseMapper.cleanGage(apiUri);
                break;
            case DRAWING:
                result = baseMapper.cleanDrawing(apiUri);
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public int cleanUcData(String apiUri) {
        Integer result = 0;
        switch (ApiEnum.getByDesc(apiUri)) {
            case PART:
                result = baseMapper.cleanUcPart();
                break;
            case JOB:
                result = baseMapper.cleanUcJob();
                break;
            case LOT:
                result = baseMapper.cleanUcLot();
                break;
            case SAMPLE:
                result = baseMapper.cleanUcSample();
                break;
            case DIM:
                result = baseMapper.cleanUcDim();
                break;
            case RESULT:
                result = baseMapper.cleanUcResult();
                break;
            case FAILED_RESULT:
                result = baseMapper.cleanUcResultFailed();
                break;
            case COMPANY:
                result = baseMapper.cleanUcCompany();
                break;
            case RECEIVER:
                result = baseMapper.cleanUcReceiver();
                break;
            case INSPCENTER:
                result = baseMapper.cleanUcInspCenter();
                break;
            case NCR:
                result = baseMapper.cleanUcNcr();
                break;
            case NCR_RESULT:
                result = baseMapper.cleanUcNcrResult();
                break;
            case CONTACT:
                result = baseMapper.cleanUcContact();
                break;
            case PROCEDURE:
                result = baseMapper.cleanUcProcedure();
                break;
            case OPERATION:
                result = baseMapper.cleanUcOperation();
                break;
            case GAGE:
                result = baseMapper.cleanUcGage();
                break;
            case DRAWING:
                result = baseMapper.cleanUcDrawing();
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public int delByIds(List<String> removedIds) {
        return baseMapper.delByIds(removedIds);
    }
}
