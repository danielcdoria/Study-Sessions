package com.example.StudyAPI.models;

import jakarta.persistence.*;

@Entity
public class StudySession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Subject subject;
    private int durationMinutes;
    private String notes;
    private String date;

    @ManyToOne
    private User user;

    public enum Subject{
        MATH, PROGRAMMING, LANGUAGE, HISTORY, SCIENCE, OTHER
    }

    public StudySession(){}

    public StudySession(String title,
                        Subject subject,
                        int durationMinutes,
                        String notes,
                        String date){
        this.title = title;
        this.subject = subject;
        this.durationMinutes = durationMinutes;
        this.notes = notes;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getNotes() {
        return notes;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
