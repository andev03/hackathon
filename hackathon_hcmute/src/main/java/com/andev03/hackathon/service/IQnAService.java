package com.andev03.hackathon.service;

import com.andev03.hackathon.dto.AskQuestionRequestDto;
import com.andev03.hackathon.dto.ResultDto;
import com.andev03.hackathon.pojo.Result;

public interface IQnAService {

    String getAnswer(AskQuestionRequestDto askQuestionRequestDto);

    Result save(ResultDto resultDto);
}
