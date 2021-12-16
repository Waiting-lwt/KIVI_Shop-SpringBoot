package com.waiting.test.repository;

import com.waiting.test.service.MailService;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

@Repository
public class MailDao implements MailService {
    //定义邮件发送器
    @Autowired
    private JavaMailSender mailSender;

    //定义邮件发送者
    private String from = "1453828952@qq.com";

    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();//创建一个简单文本邮件的对象
        message.setTo(to);//收信人
        message.setSubject(subject);//主题
        message.setText(content);//内容
        message.setFrom(from);//发信人
        mailSender.send(message);
    }

    public void sendHtmlMail(String to, String subject, String content) {

    }

    public void sendAttachmentMail(String to, String subject, String content, String filePath) {

    }

    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {

    }

}


