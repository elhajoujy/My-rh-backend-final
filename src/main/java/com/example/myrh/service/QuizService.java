package com.example.myrh.service;

import com.example.myrh.dto.responses.QuestionResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface QuizService {

    public Page<QuestionResponse> getListOfQuestions(Long profileId, Map<String, String> queryParams);

    public QuestionResponse getQuestionById(Long questionId);

    public QuestionResponse createQuestion(QuestionResponse question);

    public QuestionResponse updateQuestion(Long questionId, QuestionResponse question);

}
