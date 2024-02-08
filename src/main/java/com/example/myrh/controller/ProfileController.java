package com.example.myrh.controller;


import com.example.myrh.dto.responses.QuestionResponse;
import com.example.myrh.service.QuizService;
import org.springframework.data.domain.Page;
import com.example.myrh.dto.requests.ProfileRequest;
import com.example.myrh.dto.responses.ProfileResponse;
import com.example.myrh.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Map;

@RestController
@RequestMapping("api/v1/public/profiles")
public class ProfileController {

    private final QuizService quizService;
    private final ProfileService profileService;
    
  public ProfileController(QuizService quizService,ProfileService profileService ) {
      this.quizService = quizService;
      this.profileService = profileService; 
    }

    @GetMapping("/{id}/quizzes")
    public ResponseEntity<Page<QuestionResponse>> getQuizzesReleatedToProfile(@PathVariable("id") Long profileId, @RequestParam Map<String, String> queryParams) {
        return ResponseEntity.ok(
                quizService.getListOfQuestions(profileId, queryParams));
    }
    
    @GetMapping
    public ResponseEntity<List<ProfileResponse>> getAllProfiles() {
        List<ProfileResponse> profiles = profileService.getAllProfile();
        return ResponseEntity.ok(profiles);
    }
  
   }
    
    


