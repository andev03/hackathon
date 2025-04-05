package com.andev03.hackathon.dto;

import com.andev03.hackathon.pojo.Answer;
import com.andev03.hackathon.pojo.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerDto {
    Question questions;
    List<Answer> answers;
}
