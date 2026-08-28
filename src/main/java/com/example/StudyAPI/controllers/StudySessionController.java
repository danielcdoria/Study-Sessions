package com.example.StudyAPI.controllers;

import com.example.StudyAPI.dtos.sessionDtos.SessionRequestDto;
import com.example.StudyAPI.dtos.sessionDtos.SessionResponseDto;
import com.example.StudyAPI.models.StudySession;
import com.example.StudyAPI.services.SessionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudySessionController {
    private SessionService service;
    public StudySessionController(SessionService service){
        this.service = service;
    }

    @GetMapping("/sessions")
    public ResponseEntity<List<SessionResponseDto>> list(){
        return ResponseEntity.ok(service.list());
    }

    @PostMapping("/sessions")
    public ResponseEntity<SessionResponseDto> create(@Valid @RequestBody SessionRequestDto dto){
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @GetMapping("/sessions/{id}")
    public ResponseEntity<SessionResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/sessions/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id){
        return ResponseEntity.ok(service.remove(id));
    }

    @GetMapping("/sessions/subject")
    public ResponseEntity<List<SessionResponseDto>> findBySubject(@RequestParam StudySession.Subject subject){
        return ResponseEntity.ok(service.findBySubject(subject));
    }

    @GetMapping("/sessions/date")
    public ResponseEntity<List<SessionResponseDto>> findByDate(@RequestParam String date){
        return ResponseEntity.ok(service.findByDate(date));
    }

}
