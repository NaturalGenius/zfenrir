package com.zfenrir.core.email.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.zfenrir.core.email.ZfenrirEmailService;

public class ZfenrirEmailServiceImpl implements ZfenrirEmailService{

    private JavaMailSender javaMailSender;
    
    public ZfenrirEmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * 发送检单的文本邮件
     * @param fromEmail
     * @param toEmail
     * @param subject
     * @param text
     */
    @Override
    public void sendNormalText(String fromEmail, String toEmail, String subject, String text) {
        sendNormalText(fromEmail, new String[] {toEmail} , subject, text);
    }

    /**
     * 批量发送检单的文本邮件
     * @param fromEmail
     * @param toEmails
     * @param subject
     * @param text
     */
    @Override
    public void sendNormalText(String fromEmail, String[] toEmails, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmails);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
        
    }
    
    /**
     * 发送html类型文件
     * @param fromEmail
     * @param toEmail
     * @param subject
     * @param text
     * @throws MessagingException
     */
    @Override
    public void sendHtml(String fromEmail, String toEmail, String subject, String text) throws MessagingException {
       sendHtml(fromEmail, new String[] {toEmail}, subject, text);
    }
    
    /**
     * 批量发送html类型文件
     * @param fromEmail
     * @param toEmails
     * @param subject
     * @param text
     * @throws MessagingException
     */
    @Override
    public void sendHtml(String fromEmail, String[] toEmails, String subject, String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(fromEmail);
        helper.setTo(toEmails);
        helper.setSubject(subject);
        helper.setText(text, true);
        javaMailSender.send(message);
    }
    
    /**
     * 发送带附件的邮件
     * @param fromEmail
     * @param toEmail
     * @param subject
     * @param text
     * @param attachments
     * @throws MessagingException
     */
    @Override
    public void sendAttachment(String fromEmail, String toEmail, String subject, String text,
        Map<String, InputStreamResource> attachments) throws MessagingException {
        sendAttachment(fromEmail, new String[] {toEmail}, subject, text, attachments);
    }

    /**
     * 批量带附件的邮件
     * @param fromEmail
     * @param toEmail
     * @param subject
     * @param text
     * @param attachments
     * @throws MessagingException
     */
    @Override
    public void sendAttachment(String fromEmail, String[] toEmails, String subject, String text,
        Map<String, InputStreamResource> attachments) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(fromEmail);
        helper.setTo(toEmails);
        helper.setSubject(subject);
        helper.setText(text, true);
        Set<Entry<String, InputStreamResource>> entrySet = attachments.entrySet();
        for (Entry<String, InputStreamResource> entry : entrySet) {
            helper.addAttachment(entry.getKey(), entry.getValue());
        }
        javaMailSender.send(message);
    }
}
