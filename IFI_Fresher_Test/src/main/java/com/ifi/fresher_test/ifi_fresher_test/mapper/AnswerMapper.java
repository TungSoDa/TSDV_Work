package com.ifi.fresher_test.ifi_fresher_test.mapper;

import com.ifi.fresher_test.ifi_fresher_test.dto.AnswerDTO;
import com.ifi.fresher_test.ifi_fresher_test.model.Answer;

import java.util.ArrayList;
import java.util.List;

public class AnswerMapper {
    public static AnswerDTO entityToDTO(Answer answer) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setAnswerID(answer.getAnswerID());
        answerDTO.setContent(answer.getContent());
        answerDTO.setIsCorrect(answer.getIsCorrect());
        answerDTO.setIsDeleted(answer.getIsDeleted());
        answerDTO.setQuestionID(answer.getQuestionID());
        return answerDTO;
    }

    public static List<AnswerDTO> arrayEntityToDTO(List<Answer> answers) {
        List<AnswerDTO> answerDTOS = new ArrayList<>();
        for (Answer answer : answers) {
            answerDTOS.add(new AnswerDTO(
                            answer.getAnswerID(),
                            answer.getContent(),
                            answer.getIsCorrect(),
                            answer.getIsDeleted(),
                            answer.getQuestionID()
                    )
            );
        }
        return answerDTOS;
    }

    public static Answer dtoToEntity(AnswerDTO contestantDTO) {
        Answer answer = new Answer();
        answer.setAnswerID(contestantDTO.getAnswerID());
        answer.setContent(contestantDTO.getContent());
        answer.setIsCorrect(contestantDTO.getIsCorrect());
        answer.setIsDeleted(contestantDTO.getIsDeleted());
        answer.setQuestionID(contestantDTO.getQuestionID());
        return answer;
    }
}
