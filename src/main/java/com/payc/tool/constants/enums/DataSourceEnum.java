package com.payc.tool.constants.enums;

/**
 * @author yangshubao
 * Created on 2021/9/13
 */

public enum DataSourceEnum {

    DB1("db1"), DB2("db2"), DB3("db3"), DB4("db4");

    private String value;

    DataSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

