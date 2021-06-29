package com.ifi.fresher_test.ifi_fresher_test.mapper;

import com.ifi.fresher_test.ifi_fresher_test.dto.AnswerDTO;
import com.ifi.fresher_test.ifi_fresher_test.dto.QuestionDTO;
import com.ifi.fresher_test.ifi_fresher_test.model.Question;

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
        questionDTO.setContributorID(question.getContributorID());
        questionDTO.setAnswerList(answerList);
        return questionDTO;
    }

    public static List<QuestionDTO> arrayEntityToDTO(List<Question> questions) {
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            questionDTOS.add(new QuestionDTO(
                    questions.get(i).getQuestionId(),
                    questions.get(i).getContent(),
                    questions.get(i).getImage(),
                    questions.get(i).getTopic(),
                    questions.get(i).getIsDeleted(),
                    questions.get(i).getContributorID()
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
        question.setContributorID(questionDTO.getContributorID());
        return question;
    }
}
