package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.ApiDataMapper;
import com.agile.qdmp.standalong.model.entity.integrator.ApiData;
import com.agile.qdmp.standalong.model.enums.ApiEnum;
import com.agile.qdmp.standalong.service.integrator.IApiDataService;
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
public class ApiDataServiceImpl extends ServiceImpl<ApiDataMapper, ApiData> implements IApiDataService {

    @Override
    public int removeByType(String type) {
        return baseMapper.removeByType(type);
    }

    @Override
    public int cleanData(String apiUri) {
        Integer result = 0;
        switch (ApiEnum.getByDesc(apiUri)) {
            case PART:
                result = baseMapper.cleanPart(apiUri);
                result = baseMapper.cleanUcPart();
                break;
            case JOB:
                result = baseMapper.cleanJob(apiUri);
                result = baseMapper.cleanUcJob();
                break;
            case LOT:
                result = baseMapper.cleanLot(apiUri);
                result = baseMapper.cleanUcLot();
                break;
            case SAMPLE:
                result = baseMapper.cleanSample(apiUri);
                result = baseMapper.cleanUcSample();
                break;
            case DIM:
                result = baseMapper.cleanDim(apiUri);
                result = baseMapper.cleanUcDim();
                break;
            case RESULT:
                result = baseMapper.cleanResult(apiUri);
                result = baseMapper.cleanUcResult();
                break;
            case FAILED_RESULT:
                result = baseMapper.cleanUcResultFailed();
                break;
            case COMPANY:
                result = baseMapper.cleanCompany(apiUri);
                result = baseMapper.cleanUcCompany();
                break;
            case RECEIVER:
                result = baseMapper.cleanReceiver(apiUri);
                result = baseMapper.cleanUcReceiver();
                break;
            case INSPCENTER:
                result = baseMapper.cleanInspCenter(apiUri);
                result = baseMapper.cleanUcInspCenter();
                break;
            case NCR:
                result = baseMapper.cleanNcr(apiUri);
                result = baseMapper.cleanUcNcr();
                break;
            case NCR_RESULT:
                result = baseMapper.cleanNcrResult(apiUri);
                result = baseMapper.cleanUcNcrResult();
                break;
            case CONTACT:
                result = baseMapper.cleanContact(apiUri);
                result = baseMapper.cleanUcContact();
                break;
            case PROCEDURE:
                result = baseMapper.cleanProcedure(apiUri);
                result = baseMapper.cleanUcProcedure();
                break;
            case OPERATION:
                result = baseMapper.cleanOperation(apiUri);
                result = baseMapper.cleanUcOperation();
                break;
            case GAGE:
                result = baseMapper.cleanGage(apiUri);
                result = baseMapper.cleanUcGage();
                break;
            case DRAWING:
                result = baseMapper.cleanDrawing(apiUri);
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
