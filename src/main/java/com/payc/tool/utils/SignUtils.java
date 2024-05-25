package com.payc.tool.utils;


/**
 * @author yangshubao
 * @date 2020-05-21 9:26 上午
 * @Description:单据中心的工具类
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
public class SignUtils {

    /**
     * 输入值进行md5加密
     *
     * @param key
     * @return
     */
    static public String sign(String key) {
        String md5DigestAsHex = null;
        try {
            md5DigestAsHex = DigestUtils.md5DigestAsHex(key.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5DigestAsHex;
    }

    static public String signByDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String key = format.format(date);
        return sign(key);
    }

    public static void main(String[] args) {
        String date = "20220620";
        System.out.println("加密:" + signByDate());
    }


}
