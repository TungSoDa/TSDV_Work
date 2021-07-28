package com.ifi.fresher_test.ifi_fresher_test.mapper;

import com.ifi.fresher_test.ifi_fresher_test.dto.AnswerDTO;
import com.ifi.fresher_test.ifi_fresher_test.dto.QuestionDTO;
import com.ifi.fresher_test.ifi_fresher_test.model.Question;
import com.ifi.fresher_test.ifi_fresher_test.service.AnswerService;

import java.util.ArrayList;
import java.util.List;

public class QuestionMapper {
    public static QuestionDTO entityToDTO(Question question, List<AnswerDTO> answerList) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionID(question.getQuestionId());
        questionDTO.setContent(question.getContent());
        questionDTO.setImage(question.getImage());
        questionDTO.setTopic(question.getTopic());
        questionDTO.setIsDeleted(question.getIsDeleted());
        questionDTO.setAnswerList(answerList);
        return questionDTO;
    }

    public static List<QuestionDTO> arrayEntityToDTO(List<Question> questions, AnswerService answerService) {
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            questionDTOS.add(new QuestionDTO(
                            question.getQuestionId(),
                            question.getContent(),
                            question.getImage(),
                            question.getTopic(),
                            question.getIsDeleted(),
                            answerService.findListAnswerByQuestionID(question.getQuestionId())
                    )
            );
        }
        return questionDTOS;
    }

    public static Question dtoToEntity(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setContent(questionDTO.getContent());
        question.setImage(questionDTO.getImage());
        question.setTopic(questionDTO.getTopic());
        question.setIsDeleted(questionDTO.getIsDeleted());
        return question;
    }
}
