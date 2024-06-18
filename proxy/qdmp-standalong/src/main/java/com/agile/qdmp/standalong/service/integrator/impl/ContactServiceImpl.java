package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.mapper.integrator.ContactMapper;
import com.agile.qdmp.standalong.model.entity.integrator.Contact;
import com.agile.qdmp.standalong.service.integrator.IContactService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contacts 服务类
 *
 * @author hyzh code generator
 * @date 2022-12-02 11:15:44
 */
@Service
public class ContactServiceImpl extends ServiceImpl<ContactMapper, Contact> implements IContactService {

    @Override
    public Boolean createContactMulti(List<Contact> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Map<String, Contact> dataList = new HashMap<>(10);
        for(Contact dto : list) {
            dataList.put(dto.getFlag(), dto);
        }
        List<Contact> exists = lambdaQuery().in(Contact::getFlag, dataList.keySet()).select(Contact::getFlag).list();
        if(exists.size() == dataList.size()) {
            return true;
        }
        for(Contact contact : exists) {
            dataList.remove(contact.getFlag());
        }

        Map<String, Contact> guidList = new HashMap<>(10);
        for(Contact data : dataList.values()) {
            guidList.put(data.getGuid(), data);
        }

        // 不存在的
        List<Contact> checkGuidList = lambdaQuery().in(Contact::getGuid, guidList.keySet()).select(Contact::getGuid, Contact::getId).list();
        if(checkGuidList != null && checkGuidList.size() > 0) {
            List<Contact> updates = new ArrayList<>();
            for(Contact contact : checkGuidList) {
                Contact update = guidList.get(contact.getGuid());
                update.setId(contact.getId());
                updates.add(update);
                guidList.remove(contact.getGuid());
            }
            if(updates.size() > 0) {
                updateBatchById(updates);
            }
        }
        if(guidList.size() > 0) {
            saveBatch(guidList.values());
        }
        return true;
    }

    @Override
    public Contact getByCustomUser(String username) {
        return baseMapper.getByCustomUser(username);
    }
}
