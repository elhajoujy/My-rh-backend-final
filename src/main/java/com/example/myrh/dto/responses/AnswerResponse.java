package com.example.myrh.dto.responses;

import com.example.myrh.model.Question;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AnswerResponse {
    private Long id;
    private String title;
    private String description;
    private boolean isCorrect;
    private String question_id;

}
