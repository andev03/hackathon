package com.andev03.hackathon.service.impl;

import com.andev03.hackathon.dto.AskQuestionRequestDto;
import com.andev03.hackathon.repository.QuestionRepository;
import com.andev03.hackathon.dto.ResultDto;
import com.andev03.hackathon.pojo.Account;
import com.andev03.hackathon.pojo.Result;
import com.andev03.hackathon.repository.AccountRepository;
import com.andev03.hackathon.repository.ResultRepository;
import com.andev03.hackathon.service.IQnAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class QnAService implements IQnAService {
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private AccountRepository accountRepository;

    //Access to APIKey and URL [Gemini]
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;

    private final QuestionRepository questionRepository;

    public QnAService(WebClient.Builder webClient, QuestionRepository questionRepository) {
        this.webClient = webClient.build();
        this.questionRepository = questionRepository;
    }

    @Override
    public String getAnswer(AskQuestionRequestDto askQuestionRequestDto) {

        String typeReport = classifyReport(askQuestionRequestDto.getType());

        int realScore = realScore(askQuestionRequestDto.getTotalScore(), typeReport);

        String prompt = "Tôi đang ở " + realScore + " trên thang điểm "
                + typeReport + ". Hãy cho tôi câu trả lời với 3 tiêu chí là lời khuyên, hướng dẫn giảm căng thẳng. " +
                "Bắt đầu bằng 'chào bạn, điểm số '" + realScore + "trên thang điểm "
                + typeReport + ", và làm ơn đừng in đậm câu trả lời của bạn";

        Map<String, Object> requestBody = Map.of("contents", new Object[]{
                Map.of("parts", new Object[]{
                        Map.of("text", prompt)
                })
        });

        //Make API Call
        return webClient.post().uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private String classifyReport(String typeLower) {
        String type = typeLower.toUpperCase();
        if (type.equalsIgnoreCase("LOVE")) {
            return "LAS (LOVE ATTITUDES SCALE)";
        } else if (type.equalsIgnoreCase("SOCIAL")) {
            return "SCHMIDT";
        } else if (type.equalsIgnoreCase("FAMILY")) {
            return "FAD (Family Assessment Device)";
        } else if (type.equalsIgnoreCase("STUDY")) {
            return "ASS (Academic Stress Scale)";
        } else if (type.equalsIgnoreCase("FRIEND")) {
            return "FQS (Friendship Qualities Scale)";
        }
        return "";
    }

    private int realScore(int totalScore, String typeReport) {
        double realScore = totalScore * ((double) questionRepository.countByType(typeReport) / 15);
        return (int) Math.ceil(realScore);
    }

    @Override
    public Result save(ResultDto resultDto) {
        Result result = new Result();
        Account account = accountRepository.findByUsername(resultDto.getEmail()).get();
        result.setAccount(account);
        result.setResultContent(resultDto.getResult());
        return resultRepository.save(result);
    }
}