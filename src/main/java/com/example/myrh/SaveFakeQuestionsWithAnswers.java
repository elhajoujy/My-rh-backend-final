package com.example.myrh;

import com.example.myrh.model.Answer;
import com.example.myrh.model.Profile;
import com.example.myrh.model.Question;
import com.example.myrh.repository.AnswerRepository;
import com.example.myrh.repository.ProfileRepository;
import com.example.myrh.repository.QuestionsRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class SaveFakeQuestionsWithAnswers {
    private final QuestionsRepository questionsRepository;
    private final AnswerRepository answerRepository;
    private final ProfileRepository profileRepository;

    public SaveFakeQuestionsWithAnswers(QuestionsRepository questionsRepository, AnswerRepository answerRepository, ProfileRepository profileRepository) {
        this.questionsRepository = questionsRepository;
        this.answerRepository = answerRepository;
        this.profileRepository = profileRepository;
    }

    public void SaveListQuestionsWithAnswers() {
        List<String> answerTitles = List.of("int", "short", "Long", "byte");
        List<Boolean> correctAnswers = List.of(false, false, true, false);

        saveQuestionsWithAnswers("from this List , which is not a preemptive data type in java", "from this List , which is not a preemptive data type in java", "MCQ", answerTitles, correctAnswers);
    }

    private void saveQuestionsWithAnswers(String title, String description, String type, List<String> answerTitles, List<Boolean> correctAnswers) {
        Profile profile = this.profileRepository.getById(1L);
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setType(type);
        question.setProfile(profile);
        Question savedQuestion = this.questionsRepository.save(question);

        for (int i = 0; i < answerTitles.size(); i++) {
            Answer answer = new Answer();
            answer.setTitle(answerTitles.get(i));
            answer.setCorrect(correctAnswers.get(i));
            answer.setQuestion(savedQuestion);
            this.answerRepository.save(answer);
            savedQuestion.getAnswers().add(answer);
        }

        this.questionsRepository.save(savedQuestion);
    }

}
