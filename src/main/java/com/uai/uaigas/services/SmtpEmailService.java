package com.uai.uaigas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SmtpEmailService extends AbstractEmailService {

    @Autowired
    private MailSender mailSender;
    
    @Override
    public void sendEmail(SimpleMailMessage mail) {
	mailSender.send(mail);
    }

}
