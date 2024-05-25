package com.payc.tool.utils;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * <p>Description: </p>
 *
 * @author lenovo
 * @date 2020/12/14 17:24
 * ----------------------History---------
 * <date> <author> <desc>
 * 2020/12/14 lenovo 初始创建
 * -----------------------------------------
 */
public class DateTime {
    public static String today() {
        GregorianCalendar now = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(now.getTime());
    }

    public static String paidTime() {
        GregorianCalendar now = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        return sdf.format(now.getTime());
    }

}
