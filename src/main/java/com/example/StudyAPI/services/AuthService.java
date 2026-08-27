package com.example.StudyAPI.services;

import com.example.StudyAPI.dtos.authDtos.AuthResponseDto;
import com.example.StudyAPI.dtos.authDtos.LoginRequestDto;
import com.example.StudyAPI.dtos.authDtos.RegisterRequestDto;
import com.example.StudyAPI.models.User;
import com.example.StudyAPI.repositories.UserRepository;
import com.example.StudyAPI.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public AuthService(UserRepository repository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponseDto register(RegisterRequestDto dto){
        if (repository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("This email is already being userd.");
        }
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User user = new User(
                dto.getName(),
                dto.getEmail(),
                encodedPassword
        );
        repository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }

    public AuthResponseDto login(LoginRequestDto dto){
        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Invalid password or email");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }


}
