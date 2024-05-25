package com.payc.tool.constants;

/**
 * 常用常量类
 *
 * @author dyy
 */
public class BaseConstants {


    /**
     * 批量插入大小
     */
    public static final Integer BATCH_SIZE = 200;

    /**
     * 默认分页大小
     */
    public static final Integer FETCH_SIZE = 10;

    /**
     * 方法执行预警时间,单位毫秒
     */
    public static final Long TIME_WARNING = 3000L;

    /**
     * 逗号
     */
    public static final String COMMA = ",";

    /**
     * 百分号
     */
    public static final String SYMBOL_PERCENT = "%";

    public static final String EMPTY = "";
    public static final String COLON = ":";
    public static final String SEMICOLON = ";";
    public static final String UNDERLINE = "_";
    public static final String DASH = "-";
    public static final String SLASH = "/";
    public static final String BAR = "|";

    /**
     * 递增量
     */
    public static final Long INCREMENTAL = 1L;

    /**
     * 递减量
     */
    public static final Long DECREMENT = 1L;


    /**********************是否删除******************/

    /**
     * 未删除
     */
    public static final Integer DEL_STATUS_FALSE = 0;

    /**
     * 删除
     */
    public static final Integer  DEL_STATUS_TRUE = 1;

    /**
     * 无效
     */
    public static final Integer STATUS_FALSE = 0;

    /**
     * 有效
     */
    public static final Integer STATUS_TRUE = 1;


    /**
     * 默认分页 每页显示数量10
     */
    public static final int DEFALUL_PAGE_SIZE = 10;

    /**
     * 默认分页 当前页码1
     */
    public static final int DEFALUL_PAGE_NO = 1;

    /**
     * system
     */
    public static final String SYSTEM = "system";

    /**
     * 日期格式相关
     */
    public static final String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_TIME_MINUTE = "yyyy-MM-dd HH:mm";

    public final static String DATE_TO_STRING_PATTERN = "yyyy/MM/dd HH:mm:ss";

    public static final String FORMAT_DATE = "yyyy-MM-dd";

    public static final String FORMAT_DATE_M = "yyyy-MM";

    public static final String FORMAT_DATE_A = "yyMMdd";

    public static final String FORMAT_DATE_B = "yyyyMM";

    public static final String FORMAT_DATE_C = "yyyyMMdd";

    public static final String FORMAT_DATE_D = "yyyyMMddHHmmss";

    public static final String FORMAT_DATE_E = "yyyy/MM/dd";


}
