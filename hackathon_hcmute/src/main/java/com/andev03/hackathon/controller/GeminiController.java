package com.andev03.hackathon.controller;

import com.andev03.hackathon.dto.AskQuestionRequestDto;
import com.andev03.hackathon.dto.ResultDto;
import com.andev03.hackathon.pojo.Result;
import com.andev03.hackathon.service.IQnAService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/qna")
@CrossOrigin(origins = "http://localhost:5173")
public class GeminiController {

    private final IQnAService qnAService;


    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody AskQuestionRequestDto askQuestionRequestDto) throws JsonProcessingException {
        String answer = qnAService.getAnswer(askQuestionRequestDto);
        if (askQuestionRequestDto.getUsername() == null) return ResponseEntity.ok(answer);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(answer);
        String text = root
                .path("candidates").get(0)
                .path("content")
                .path("parts").get(0)
                .path("text")
                .asText();
        ResultDto tmp = new ResultDto(askQuestionRequestDto.getUsername(), text);
        qnAService.save(tmp);
        return ResponseEntity.ok(answer);
    }
}
