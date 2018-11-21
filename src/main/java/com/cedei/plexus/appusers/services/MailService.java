package com.cedei.plexus.appusers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 * mailService
 */
@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${mail.registration.subject}")
    private String registrationSubject;
    
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    MailContentBuilder contentBuilder;

    public void sendRegistrationEmail(String toSend, String username, String password) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(this.emailFrom);
            messageHelper.setTo(toSend);
            messageHelper.setSubject(this.registrationSubject);
            messageHelper.setText(contentBuilder.buildRegistrateEmail(username, password), true);
        };
        this.send(messagePreparator);
    }
    
    private void send(MimeMessagePreparator emailData){
        try {
            mailSender.send(emailData);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}