package com.payc.tool;


import com.payc.tool.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.payc.tool.service.impl.EmailUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendEmailApplicationTests {

    /**
     * 注入发送邮件的接口
     */
    @Autowired
    private EmailService emailService;

    /**
     * 测试发送文本邮件
     */
    @Test
    public void sendmail() {
        emailService.sendSimpleMail("yangshubao911@163.com","主题：你好普通邮件云厨1","内容：云厨第一封邮件1");
//        mailService.sendSimpleMail("yangshubao@payctop.cn","主题：你好普通邮件","内容：第一封邮件");
    }

//    @Test
//    public void sendmails() {
//        mailService.sendSimpleMails("835121345@qq.com,developer@payctop.cn","主题：你好普通邮件云厨2","内容：云厨第一封邮件2");
//    }
//
//    @Test
//    public void sendmailHtml(){
//        mailService.sendHtmlMail("835121345@qq.com,developer@payctop.cn","主题：你好html邮件","<h1>内容：第一封html邮件</h1>");
//    }
}