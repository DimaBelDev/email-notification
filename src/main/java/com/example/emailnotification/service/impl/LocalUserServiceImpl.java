package com.example.emailnotification.service.impl;


import com.example.emailnotification.entity.LocalUser;
import com.example.emailnotification.repository.LocalUserRepository;
import com.example.emailnotification.service.LocalUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalUserServiceImpl implements LocalUserService {

    private final LocalUserRepository localUserRepository;


    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                     LocalUser localUsert = localUserRepository.findByEmail(email);
                     if(localUsert!=null) {
                         return localUsert;
                     } else {
                         throw new UsernameNotFoundException("User is not found");
                     }
            }
        };
    }
}
