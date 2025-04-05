package com.andev03.hackathon.service.impl;

import com.andev03.hackathon.dto.AskQuestionRequestDto;
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

    public QnAService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    @Override
    public String getAnswer(AskQuestionRequestDto askQuestionRequestDto) {

        String typeReport = classifyReport(askQuestionRequestDto.getType());

        String prompt = "Tôi đang ở " + askQuestionRequestDto.getTotalScore() + " trên thang điểm "
                + typeReport + ". Hãy cho tôi câu trả lời với 3 tiêu chí là lời khuyên, hướng dẫn giảm căng thẳng. " +
                "Bắt đầu bằng 'chào bạn, điểm số '" + askQuestionRequestDto.getTotalScore() + "trên thang điểm "
                + typeReport + ", và làm ơn đừng in đậm câu trả lời của bạn";

        System.out.println(prompt);

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


    @Override
    public Result save(ResultDto resultDto) {
        Result result = new Result();
        Account account = accountRepository.findByUsername(resultDto.getEmail()).get();
        result.setAccount(account);
        result.setResultContent(resultDto.getResult());
        return resultRepository.save(result);
    }
}
