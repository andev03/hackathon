package com.andev03.hackathon.controller;

import com.andev03.hackathon.service.IQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@CrossOrigin(origins = "https://hackathon.nguyenhoangan.site")
public class QuestionController {

    private final IQuestionService questionService;

    @GetMapping("/questions")
    public ResponseEntity<Object> getQuestionsByType(@RequestParam(name = "type") String type) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionsByType(type));
    }
}
