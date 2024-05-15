package com.example.emailnotification.service.impl;


import com.example.emailnotification.dto.EmailSendDetailsRequest;
import com.example.emailnotification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    public void sendVerificationEmail(EmailSendDetailsRequest emailSendDetailsRequest) {

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom(fromMail);
            simpleMailMessage.setTo(emailSendDetailsRequest.getRecipient());
            simpleMailMessage.setSubject(emailSendDetailsRequest.getSubject());
            simpleMailMessage.setText(emailSendDetailsRequest.getMessageBody());

            mailSender.send(simpleMailMessage);
            log.info("Email sent successfully");
    }
}
