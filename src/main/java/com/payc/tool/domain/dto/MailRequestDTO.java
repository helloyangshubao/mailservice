package com.payc.tool.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;


/**
 * @author zhaodong
 * @date 2019/8/22 13:26
 */
@Data
@ApiModel(value = "MailRequestDTO", description = "邮件内容")
public class MailRequestDTO {

    private String to;//邮件接收人（多个邮箱则用逗号","隔开）
    private String subject;//邮件主题
    private String text;//邮件内容
}
