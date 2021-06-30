package com.ifi.fresher_test.ifi_fresher_test.mapper;

import com.ifi.fresher_test.ifi_fresher_test.dto.ExamDTO;
import com.ifi.fresher_test.ifi_fresher_test.dto.QuestionDTO;
import com.ifi.fresher_test.ifi_fresher_test.model.Exam;
import com.ifi.fresher_test.ifi_fresher_test.service.QuestionService;
import com.ifi.fresher_test.ifi_fresher_test.util.MessageResource;

import java.util.ArrayList;
import java.util.List;

public class ExamMapper {
    public static ExamDTO entityToDTO(Exam exam, List<QuestionDTO> questionList) {
        ExamDTO examDTO = new ExamDTO();
        examDTO.setExamID(exam.getExamID());
        examDTO.setName(exam.getName());
        examDTO.setTopic(exam.getTopic());
        examDTO.setListQuestionID(exam.getListQuestionID());
        examDTO.setIsDeleted(exam.getIsDeleted());
        examDTO.setQuestionList(questionList);
        return examDTO;
    }

    public static List<ExamDTO> arrayEntityToDTO(List<Exam> exams, QuestionService questionService) {
        List<ExamDTO> examDTOS = new ArrayList<>();
        for (Exam exam : exams) {
            if (exam.getTopic().equals(MessageResource.SYNTHESIS_TOPIC)) {
                examDTOS.add(new ExamDTO(
                                exam.getExamID(),
                                exam.getName(),
                                exam.getTopic(),
                                exam.getListQuestionID(),
                                exam.getIsDeleted(),
                                questionService.stringToListQuestionDTO(exam.getListQuestionID(), MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER)
                        )
                );
            } else {
                examDTOS.add(new ExamDTO(
                                exam.getExamID(),
                                exam.getName(),
                                exam.getTopic(),
                                exam.getListQuestionID(),
                                exam.getIsDeleted(),
                                questionService.stringToListQuestionDTO(exam.getListQuestionID(), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER)
                        )
                );
            }
        }
        return examDTOS;
    }

    public static Exam dtoToEntity(ExamDTO examDTO) {
        Exam exam = new Exam();
        exam.setName(examDTO.getName());
        exam.setTopic(examDTO.getTopic());
        exam.setListQuestionID(examDTO.getListQuestionID());
        exam.setIsDeleted(examDTO.getIsDeleted());
        return exam;
    }
}
