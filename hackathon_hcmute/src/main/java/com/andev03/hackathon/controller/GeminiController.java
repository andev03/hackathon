package com.andev03.hackathon.controller;

import com.andev03.hackathon.dto.AskQuestionRequestDto;
import com.andev03.hackathon.service.IQnAService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/qna")
public class GeminiController {

    private final IQnAService qnAService;

    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody AskQuestionRequestDto askQuestionRequestDto) {
        String answer = qnAService.getAnswer(askQuestionRequestDto);
        return ResponseEntity.ok(answer);
    }
}
