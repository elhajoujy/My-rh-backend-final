package com.example.myrh.dto.responses;

import com.example.myrh.model.Answer;
import com.example.myrh.model.Profile;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class QuestionResponse {
    private Long id;
    private String title;
    private String description;
    private String type;
    private Profile profile;
  //  private List<AnswerResponse> answers;

}
