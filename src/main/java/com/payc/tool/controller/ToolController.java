package com.payc.tool.controller;

import com.payc.tool.domain.Result;
import com.payc.tool.domain.dto.MailRequestDTO;
import com.payc.tool.service.EmailService;
import com.payc.tool.service.impl.EmailUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 工具服务类
 *
 * @author yangshubao
 * @since 2024-04-01
 */
@RestController
@RequestMapping("/tool")
@Api(value = "工具服务", tags = "tool API V1")
@Slf4j
public class ToolController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMail")
    @ApiOperation(value = "发送邮件", notes = "发送邮件")
    public Result<Boolean> sendMail(@RequestBody MailRequestDTO requestDTO) {
        emailService.sendSimpleMails(requestDTO.getTo(), requestDTO.getSubject(), requestDTO.getText());
        return Result.success(true);
    }


}
