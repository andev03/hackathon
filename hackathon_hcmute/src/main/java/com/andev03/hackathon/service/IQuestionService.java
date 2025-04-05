package com.andev03.hackathon.service;

import com.andev03.hackathon.dto.QuestionAnswerDto;

import java.util.List;

public interface IQuestionService {
    List<QuestionAnswerDto> getQuestionsByType(String type);
}
