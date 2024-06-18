package com.agile.qdmp.standalong.service.uaa;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Description:
 * @Author: wenbinglei
 * @Date: 2020/12/1 18:37
 */
public interface MyUserDetailsService extends UserDetailsService {

    /**
     * 手机号码登录
     * @param mobile
     * @return
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException;

    /**
     * 自定义的用户名密码登录
     * 因为不能覆盖oauth/token
     * @param username
     * @param password
     * @return
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserByUsernameAndPwd(String username, String password) throws UsernameNotFoundException;


    /**
     * 登录IM
     * @param username
     * @param password
     * @return
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserByIM(String username, String password, String serverUri) throws UsernameNotFoundException;

    /**
     * 面部识别登录
     * @param filePath
     * @return
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserByPhoto(String filePath) throws UsernameNotFoundException;
}
