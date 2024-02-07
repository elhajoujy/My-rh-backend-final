package com.example.myrh.mapper;


import com.example.myrh.dto.requests.QuestionRequest;
import com.example.myrh.dto.responses.QuestionResponse;
import com.example.myrh.model.Question;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionMapper implements IMapper<Question, QuestionRequest, QuestionResponse> {


    private final ModelMapper modelMapper;
    private final AnswerMapper answerMapper;


    @Override
    public QuestionResponse toRes(Question question) {
        return modelMapper.map(question, QuestionResponse.class);
    }


    public QuestionResponse toQuestionResponseWithAnswers(Question question) {
        QuestionResponse questionResponse = modelMapper.map(question, QuestionResponse.class);
        // Add answers to question response
//        question.getAnswers().forEach(
//                answer -> questionResponse.getAnswers().add(answerMapper.toRes(answer))
//        );
        return questionResponse;
    }

    @Override
    public QuestionRequest toReq(Question question) {
        return modelMapper.map(question, QuestionRequest.class);
    }

    @Override
    public Question resToEntity(QuestionResponse questionResponse) {
        return modelMapper.map(questionResponse, Question.class);
    }

    @Override
    public Question reqToEntity(QuestionRequest questionRequest) {
        return modelMapper.map(questionRequest, Question.class);
    }


}
