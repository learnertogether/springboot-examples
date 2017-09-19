package com.zxl.examples.controller;

import com.zxl.examples.email.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created by Administrator on 2017/7/26.
 */
@RestController
public class EmailController {

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    private TemplateEngine templateEngine;

    @GetMapping("/email/sendSimpleMail")
    public void sendSimpleMail(){
        emailService.sendSimpleMail("zhangxiaolong@weimingedu.com","title","this is a simple email for test.");
    }

    @GetMapping("/email/sendHtmlMail")
    public void sendHtmlMail(){
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>this is a simple email for test.</h3>\n" +
                "</body>\n" +
                "</html>";
        emailService.sendHtmlMail("zhangxiaolong@weimingedu.com","title",content);
    }

    @GetMapping("/email/sendAttachmentsMail")
    public void sendAttachmentsMail(){
        emailService.sendAttachmentsMail("zhangxiaolong@weimingedu.com","title","this is a have attachments email for test.","d:\\桌面.jpg");
    }

    @GetMapping("/email/sendInlineResourceMail")
    public void sendInlineResourceMail(){
        String rscId = "zxl001";
        String content="<html><body>this is a imgage：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "d:\\桌面.jpg";


        emailService.sendInlineResourceMail("zhangxiaolong@weimingedu.com","title",content,imgPath,rscId);
    }

    @GetMapping("/email/sendTemplateMail")
    public void sendTemplateMail(){

        //一用模版创建邮件内容
        Context context = new Context();
        context.setVariable("name", "百度链接");
        String emailContent = templateEngine.process("emailTemplate", context);


        emailService.sendHtmlMail("zhangxiaolong@weimingedu.com","title",emailContent);
    }
}
