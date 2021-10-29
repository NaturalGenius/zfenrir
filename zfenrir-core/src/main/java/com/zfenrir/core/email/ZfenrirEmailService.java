package com.zfenrir.core.email;

import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.core.io.InputStreamResource;

/**
 * Zfenrir Email send Service
 * @author zhuliang
 * @Date  2021-10-28
 *
 */
public interface ZfenrirEmailService {

    
    /**
     * 发送检单的文本邮件
     * @param fromEmail
     * @param toEmail
     * @param subject
     * @param text
     */
    void sendNormalText(String fromEmail, String toEmail, String subject, String text);
    
    /**
     * 批量发送检单的文本邮件
     * @param fromEmail
     * @param toEmails
     * @param subject
     * @param text
     */
    void sendNormalText(String fromEmail, String[] toEmails, String subject, String text);
    
    /**
     * 发送html类型文件
     * @param from
     * @param to
     * @param subject
     * @param text
     * @throws MessagingException
     */
    void sendHtml(String fromEmail, String toEmail, String subject, String text) throws MessagingException;
    
    /**
     * 批量发送html类型文件
     * @param from
     * @param to
     * @param subject
     * @param text
     * @throws MessagingException
     */
    void sendHtml(String fromEmail, String[] toEmails, String subject, String text) throws MessagingException;
    
    /**
     * 发送带附件的邮件
     * @param fromEmail
     * @param toEmail
     * @param subject
     * @param text
     * @param attachments
     * @throws MessagingException
     */
    void sendAttachment(String fromEmail, String toEmail, String subject, String text, Map<String, InputStreamResource> attachments) throws MessagingException;
    
    /**
     * 批量发送带附件的邮件
     * @param fromEmail
     * @param toEmails
     * @param subject
     * @param text
     * @param attachments
     * @throws MessagingException
     */
    void sendAttachment(String fromEmail, String[] toEmails, String subject, String text, Map<String, InputStreamResource> attachments) throws MessagingException;
}
