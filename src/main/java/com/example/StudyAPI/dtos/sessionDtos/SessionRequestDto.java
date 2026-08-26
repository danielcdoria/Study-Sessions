package com.example.StudyAPI.dtos.sessionDtos;

import com.example.StudyAPI.models.StudySession;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class SessionRequestDto {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotNull(message = "Subject cannot be null")
    private StudySession.Subject subject;
    @Positive(message = "Duration minutes must be above 0.")
    private int durationMinutes;
    @NotBlank(message = "Notes cannot be blank.")
    private String notes;
    @NotBlank(message = "Date cannot be blank")
    private String date;

    public StudySession.Subject getSubject() {
        return subject;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getNotes() {
        return notes;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
