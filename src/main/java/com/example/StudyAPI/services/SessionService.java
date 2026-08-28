package com.example.StudyAPI.services;

import com.example.StudyAPI.dtos.sessionDtos.SessionRequestDto;
import com.example.StudyAPI.dtos.sessionDtos.SessionResponseDto;
import com.example.StudyAPI.models.StudySession;
import com.example.StudyAPI.models.User;
import com.example.StudyAPI.repositories.SessionsRepository;
import com.example.StudyAPI.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public SessionResponseDto convertToDto(StudySession studySession){
        return new SessionResponseDto(
                studySession.getId(),
                studySession.getTitle(),
                studySession.getSubject(),
                studySession.getNotes(),
                studySession.getDate()
        );
    }

    public List<SessionResponseDto> list(){
        User user = getLoggedUser();
        return repository.findByUser(user)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public SessionResponseDto create(SessionRequestDto dto){
        User user = getLoggedUser();
        StudySession session = new StudySession(
                dto.getTitle(),
                dto.getSubject(),
                dto.getDurationMinutes(),
                dto.getNotes(),
                dto.getDate()
        );
        session.setUser(user);
        repository.save(session);
        return convertToDto(session);
    }

    public SessionResponseDto findById(Long id){
        User user = getLoggedUser();
        StudySession session = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));
        if (!session.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("Acess denied.");
        }
        return convertToDto(session);
    }

    public String remove(Long id){
        User user = getLoggedUser();
        StudySession session = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Session not found."));
        if (!session.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("Acess denied.");
        }
        repository.delete(session);
        return "This session was removed successfully!";
    }

    public List<SessionResponseDto> findBySubject(StudySession.Subject subject){
        User user = getLoggedUser();
        return repository.findByUserAndSubject(user, subject)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<SessionResponseDto> findByDate(String date){
        User user = getLoggedUser();
        return repository.findByUserAndDate(user, date)
                .stream()
                .map(this::convertToDto)
                .toList();
    }
}
