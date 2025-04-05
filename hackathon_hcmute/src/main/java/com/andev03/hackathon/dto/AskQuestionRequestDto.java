package com.andev03.hackathon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AskQuestionRequestDto {

    private int totalScore;
    private String type;
}
