package com.example.emailnotification.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface LocalUserService {

    UserDetailsService userDetailsService();
}
