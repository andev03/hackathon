package com.andev03.hackathon.service.impl;

import com.andev03.hackathon.dto.QuestionAnswerDto;
import com.andev03.hackathon.repository.AnswerRepository;
import com.andev03.hackathon.repository.QuestionRepository;
import com.andev03.hackathon.service.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Override
    public QuestionAnswerDto getQuestionsByType(String type) {

        return new QuestionAnswerDto(questionRepository.findByType(type), answerRepository.findByAnswerType(type));
    }
}
