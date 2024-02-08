package com.example.myrh.mapper;

import com.example.myrh.dto.requests.AnswerRequest;
import com.example.myrh.dto.responses.AnswerResponse;
import com.example.myrh.model.Answer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnswerMapper implements IMapper<Answer, AnswerRequest, AnswerResponse> {

    private final ModelMapper modelMapper;

    @Override
    public AnswerResponse toRes(Answer answer) {
        return this.modelMapper.map(answer, AnswerResponse.class);
    }

    @Override
    public AnswerRequest toReq(Answer answer) {
        return this.modelMapper.map(answer, AnswerRequest.class);
    }

    @Override
    public Answer resToEntity(AnswerResponse answerResponse) {
        return this.modelMapper.map(answerResponse, Answer.class);
    }

    @Override
    public Answer reqToEntity(AnswerRequest answerRequest) {
        return this.modelMapper.map(answerRequest, Answer.class);
    }
}
