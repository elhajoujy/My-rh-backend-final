package com.example.myrh.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnswerRequest {
    private String title;
    private String description;
    private boolean isCorrect;
    private Long questionId;
}
