package com.example.myrh.controller;

import com.example.myrh.dto.responses.QuestionResponse;
import com.example.myrh.service.QuizService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/profiles")
public class ProfileController {

    private final QuizService quizService;

    public ProfileController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/{id}/quizzes")
    public ResponseEntity<Page<QuestionResponse>> getQuizzesReleatedToProfile(@PathVariable("id") Long profileId, @RequestParam Map<String, String> queryParams) {
        return ResponseEntity.ok(
                quizService.getListOfQuestions(profileId, queryParams));
    }
}
