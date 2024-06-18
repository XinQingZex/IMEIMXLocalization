package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.entity.integrator.Receiver;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Receivers
 *
 * @author hyzh code generator
 * @date 2022-11-22 18:41:33
 */
public interface IReceiverService extends IService<Receiver> {

    /**
     * 批量新增Receiver
     * @param receiverList
     * @return
     */
    Boolean createReceiverMulti(List<Receiver> receiverList);
}
