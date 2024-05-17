package com.example.emailnotification.repository;

import com.example.emailnotification.entity.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocalUserRepository extends JpaRepository<LocalUser, Long > {

    Optional<LocalUser> findByEmail(String email);
}
