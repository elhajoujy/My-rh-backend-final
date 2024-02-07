package com.example.myrh.dto.responses;

import com.example.myrh.model.Question;
import jakarta.persistence.ManyToOne;

public class AnswerResponse {
    private Long id;
    private String title;
    private String description;
    private boolean isCorrect;
    private QuestionResponse question;
}
