package com.example.myrh.dto.responses;

import com.example.myrh.model.JobSeeker;
import com.example.myrh.model.Question;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class ProfileResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
   // private List<QuestionResponse> questions ;
    //private List<JobSeekerRes> jobSeekers ;
}
