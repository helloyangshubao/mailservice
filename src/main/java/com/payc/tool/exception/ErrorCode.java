package com.payc.tool.exception;

/**
 * @author yangshbuao
 * @date 2021/7/2 13:44
 */
public interface ErrorCode {

    /**
     * getCode
     *
     * @return
     */
    Integer getCode();

    /**
     * getDesc
     *
     * @return
     */
    String getDesc();

    /**
     * getDescCN
     *
     * @return
     */
    String getDescCN();

}
