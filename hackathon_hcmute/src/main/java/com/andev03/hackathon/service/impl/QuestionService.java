package com.andev03.hackathon.service.impl;

import com.andev03.hackathon.dto.QuestionAnswerDto;
import com.andev03.hackathon.pojo.Question;
import com.andev03.hackathon.repository.AnswerRepository;
import com.andev03.hackathon.repository.QuestionRepository;
import com.andev03.hackathon.service.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Override
    public List<QuestionAnswerDto> getQuestionsByType(String type) {
        List<Question> questions = questionRepository.findByType(type);

        List<QuestionAnswerDto> result =  new ArrayList<>();

        for (Question question : questions) {
            result.add(new QuestionAnswerDto(question, answerRepository.findByAnswerType(type)));
        }
        return result;
    }
}
