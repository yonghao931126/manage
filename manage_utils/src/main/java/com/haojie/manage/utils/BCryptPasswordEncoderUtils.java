package com.haojie.manage.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String  encode(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    //测试
    public static void main(String[] args) {
        String password="111";  //tom 123  jerry321  alllen 111
        String encode = BCryptPasswordEncoderUtils.encode(password);
        System.out.println(encode);
    }
    /**
     * BCryptPasswordEncoder 加密时每次加密会随机生成 盐 所以每次的密码都不一样
     *                       解密时先得到盐才能解密
     */
}
