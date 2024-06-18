package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.entity.integrator.Contact;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Contacts
 *
 * @author hyzh code generator
 * @date 2022-12-02 11:15:44
 */
public interface IContactService extends IService<Contact> {

    /**
     * 批量新增Contact
     * @param list
     * @return
     */
    Boolean createContactMulti(List<Contact> list);

    /**
     * 查找账户
     * @param username
     * @return
     */
    Contact getByCustomUser(String username);
}
