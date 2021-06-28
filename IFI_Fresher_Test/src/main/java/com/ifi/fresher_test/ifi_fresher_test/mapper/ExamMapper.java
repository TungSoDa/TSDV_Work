package com.ifi.fresher_test.ifi_fresher_test.mapper;

import com.ifi.fresher_test.ifi_fresher_test.dto.ExamDTO;
import com.ifi.fresher_test.ifi_fresher_test.dto.QuestionDTO;
import com.ifi.fresher_test.ifi_fresher_test.model.Exam;
import com.ifi.fresher_test.ifi_fresher_test.model.Question;

import java.util.List;

public class ExamMapper {
    public static ExamDTO entityToDTO(Exam exam, List<QuestionDTO> questionList) {
        ExamDTO examDTO = new ExamDTO();
        examDTO.setExamID(exam.getExamID());
        examDTO.setTopic(exam.getTopic());
        examDTO.setListQuestionID(exam.getListQuestionID());
        examDTO.setIsDeleted(exam.getIsDeleted());
        examDTO.setQuestionList(questionList);
        return examDTO;
    }

    public static Exam dtoToEntity(ExamDTO examDTO) {
        Exam exam = new Exam();
        exam.setTopic(examDTO.getTopic());
        exam.setListQuestionID(examDTO.getListQuestionID());
        exam.setIsDeleted(examDTO.getIsDeleted());
        return exam;
    }
}
