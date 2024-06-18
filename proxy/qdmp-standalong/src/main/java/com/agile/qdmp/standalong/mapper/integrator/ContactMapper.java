package com.agile.qdmp.standalong.mapper.integrator;

import com.agile.qdmp.standalong.model.entity.integrator.Contact;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Contacts
 *
 * @author hyzh code generator
 * @date 2022-12-02 11:15:44
 */
public interface ContactMapper extends BaseMapper<Contact> {

    @Select("SELECT c.ContactID as employee_id,c.GlobalID as guid,c.CustomUser as username FROM Core_UserAccounts c WHERE c.IsDeleted=0 and c.Deleted=0 AND c.CustomUser=#{username}")
    Contact getByCustomUser(@Param("username") String username);
}
