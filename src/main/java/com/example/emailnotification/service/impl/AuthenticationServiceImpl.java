package com.example.emailnotification.service.impl;

import com.example.emailnotification.dto.EmailSendDetailsRequest;
import com.example.emailnotification.dto.RegistrationRequest;
import com.example.emailnotification.entity.LocalUser;
import com.example.emailnotification.exception.UserAlreadyExistException;
import com.example.emailnotification.repository.LocalUserRepository;
import com.example.emailnotification.service.AuthenticationService;
import com.example.emailnotification.service.EmailService;
import com.example.emailnotification.utils.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final LocalUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public void register(RegistrationRequest request) throws UserAlreadyExistException {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistException();
        }
        LocalUser user = LocalUser.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        EmailSendDetailsRequest emailSendDetailsRequest =
                EmailSendDetailsRequest.builder()
                                .recipient(user.getEmail())
                                .subject(EmailMessage.registrationSubject)
                                .messageBody(EmailMessage.registrationBody)
                                .build();

        emailService.sendVerificationEmail(emailSendDetailsRequest);

        userRepository.save(user);
    }
}
