package com.andev03.hackathon.service;

import com.andev03.hackathon.dto.QuestionAnswerDto;

public interface IQuestionService {
    QuestionAnswerDto getQuestionsByType(String type);
}
