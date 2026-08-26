package com.example.StudyAPI.repositories;

import com.example.StudyAPI.models.StudySession;
import com.example.StudyAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionsRepository extends JpaRepository<StudySession, Long> {
    List<StudySession> findByUser(User user);
    List<StudySession> findByUserAndSubject(User user, StudySession.Subject subject);
    List<StudySession> findByUserAndDate(User user, String date);
}
