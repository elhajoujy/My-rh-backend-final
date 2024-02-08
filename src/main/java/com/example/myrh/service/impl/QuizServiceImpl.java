package com.example.myrh.service.impl;

import com.example.myrh.dto.responses.QuestionResponse;
import com.example.myrh.mapper.QuestionMapper;
import com.example.myrh.repository.QuestionsRepository;
import com.example.myrh.service.QuizService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuestionsRepository questionsRepository;
    private final QuestionMapper questionMapper;

    public QuizServiceImpl(QuestionsRepository questionsRepository, QuestionMapper questionMapper) {
        this.questionsRepository = questionsRepository;
        this.questionMapper = questionMapper;
    }

    @Override
    public Page<QuestionResponse> getListOfQuestions(Long profileId, Map<String, String> queryParams) {
        String size = queryParams.getOrDefault("size", "10");
        String page = queryParams.getOrDefault("page", "0");

        PageRequest pageRequest;
        pageRequest = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));

        return this.questionsRepository.findAllByProfileId(profileId, pageRequest).map(questionMapper::toQuestionResponseWithAnswers);
    }

    @Override
    public QuestionResponse getQuestionById(Long questionId) {
        return null;
    }

    @Override
    public QuestionResponse createQuestion(QuestionResponse question) {
        return null;
    }

    @Override
    public QuestionResponse updateQuestion(Long questionId, QuestionResponse question) {
        return null;
    }
}
