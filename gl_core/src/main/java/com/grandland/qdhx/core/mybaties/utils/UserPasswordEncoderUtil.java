package com.grandland.qdhx.core.mybaties.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * 用户密码加密的工具类
 * @author loverqi
 * @date 2018年1月15日
 */
public class UserPasswordEncoderUtil {

    /** TODO
     * 获取加密后的初始密码,后期修改为从配置表中读取
     * @return 
     */
    public static String getInitialPassword() {
        return getEncodelPassword("123456");
    }

    /**
     * 密码加密的方法
     */
    public static String getEncodelPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
        return passwordEncoder.encode(DigestUtils.md5DigestAsHex(password.getBytes()));
    }

}
