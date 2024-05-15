package com.example.emailnotification.service;

import com.example.emailnotification.dto.RegistrationRequest;
import com.example.emailnotification.exception.UserAlreadyExistException;

public interface AuthenticationService {

    void register(RegistrationRequest request) throws UserAlreadyExistException;
}
