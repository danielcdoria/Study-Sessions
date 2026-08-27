package com.example.StudyAPI.services;

import com.example.StudyAPI.models.User;
import com.example.StudyAPI.repositories.SessionsRepository;
import com.example.StudyAPI.repositories.UserRepository;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final SessionsRepository repository;
    private final UserRepository userRepository;
    public SessionService(SessionsRepository repository, UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public User getLoggedUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
    }
}
