package com.example.emailnotification.service;

import com.example.emailnotification.dto.EmailSendDetailsRequest;

public interface EmailService {

    void sendVerificationEmail(EmailSendDetailsRequest emailSendDetailsRequest);

}
