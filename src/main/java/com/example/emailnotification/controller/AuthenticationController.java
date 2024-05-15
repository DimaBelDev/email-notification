package com.example.emailnotification.controller;


import com.example.emailnotification.dto.RegistrationRequest;
import com.example.emailnotification.exception.UserAlreadyExistException;
import com.example.emailnotification.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {


    private final AuthenticationService authenticationService;


    @PostMapping
    public ResponseEntity<?> createLocalUser(@Valid  @RequestBody RegistrationRequest request) throws UserAlreadyExistException {

        authenticationService.register(request);

        return ResponseEntity.ok().build();

    }
}
