package com.example.StudyAPI.dtos.sessionDtos;

import com.example.StudyAPI.models.StudySession;

public class SessionResponseDto {
    private Long id;
    private String title;
    private StudySession.Subject subject;
    private String notes;
    private String date;

    public SessionResponseDto(Long id,
                              String title,
                              StudySession.Subject subject,
                              String notes,
                              String date){
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.notes = notes;
        this.date = date;
    }

    public StudySession.Subject getSubject() {
        return subject;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    public Long getId() {
        return id;
    }
}
