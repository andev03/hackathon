package com.andev03.hackathon.service;

import com.andev03.hackathon.dto.AskQuestionRequestDto;

public interface IQnAService {

    String getAnswer(AskQuestionRequestDto askQuestionRequestDto);
}
