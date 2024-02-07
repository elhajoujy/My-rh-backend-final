package com.example.myrh.dto.requests;

import com.example.myrh.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionRequest {

    private String title;
    private String description;
    private String type;
    private String id_profile;
}
