package com.payc.tool.constants.enums;


import com.payc.tool.exception.ErrorCode;
import lombok.Getter;

/**
 * @author yangshbuao
 * @date 2021/7/2 14:28
 */
@Getter
public enum ErrorCodeEnum implements ErrorCode {

    /**
     * 成功
     */
    SUCCESS(0, "success", "成功"),
    FAIL(1, "fail", "失败"),
    PARAM_ERROR(2, "param error", "参数错误"),
    REPEAT_ERROR(2, "repeat error", "已有该数据，请勿重复添加"),
    SYSTEM_ERROR(999, "system error", "系统错误"),
    SIGNATURE_ERROR(4003, "signature error", "签名错误"),
    DATA_REPEAT_ERROR(1001, "data repeat error", "数据已存在"),
    DATA_EMPTY_ERROR(1002, "data empty error", "数据不能为空"),
    EXCEL_UPLOAD_FILETYPE_FAILED_ERROR_MSG(2001, "file type error", "只支持xlsx格式文件"),
    EXCEL_UPLOAD_DATA_FORMAT_ERROR_MSG(406, "data format error", "导入文件数据格式错误，请重新选择文件"),
    NO_DATA_ENTER(2002, "no data enter", "导入数据不能为空");
    private Integer code;
    private String desc;
    private String descCN;

    ErrorCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    ErrorCodeEnum(Integer code, String desc, String descCN) {
        this.code = code;
        this.desc = desc;
        this.descCN = descCN;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String getDescCN() {
        return descCN;
    }

}
