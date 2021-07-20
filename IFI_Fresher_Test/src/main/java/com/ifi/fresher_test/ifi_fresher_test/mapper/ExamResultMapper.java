package com.ifi.fresher_test.ifi_fresher_test.mapper;

import com.ifi.fresher_test.ifi_fresher_test.dto.ExamResultDTO;
import com.ifi.fresher_test.ifi_fresher_test.dto.QuestionDTO;
import com.ifi.fresher_test.ifi_fresher_test.model.ExamResult;
import com.ifi.fresher_test.ifi_fresher_test.repository.ExamRepository;
import com.ifi.fresher_test.ifi_fresher_test.service.QuestionService;
import com.ifi.fresher_test.ifi_fresher_test.util.MessageResource;

import java.util.ArrayList;
import java.util.List;

public class ExamResultMapper {
    public static ExamResultDTO entityToDTO(ExamResult examResult, List<QuestionDTO> questionList) {
        ExamResultDTO examResultDTO = new ExamResultDTO();
        examResultDTO.setExamResultID(examResult.getExamResultID());
        examResultDTO.setExamID(examResult.getExamID());
        examResultDTO.setContestantUsername(examResult.getContestantUsername());
        examResultDTO.setTestMark(examResult.getTestMark());
        examResultDTO.setSelectedAnswers(examResult.getSelectedAnswers());
        examResultDTO.setIsDeleted(examResult.getIsDeleted());
        examResultDTO.setQuestionList(questionList);
        return examResultDTO;
    }

    public static List<ExamResultDTO> arrayEntityToDTO(List<ExamResult> examResults, ExamRepository examRepository, QuestionService questionService) {
        List<ExamResultDTO> examResultDTOS = new ArrayList<>();
        for (ExamResult examResult : examResults) {
            if (examRepository.findExamByExamIDAndIsDeletedFalse(examResult.getExamID()).get().equals(MessageResource.SYNTHESIS_TOPIC)) {
                examResultDTOS.add(new ExamResultDTO(
                        examResult.getExamResultID(),
                        examResult.getExamID(),
                        examResult.getContestantUsername(),
                        examResult.getTestMark(),
                        examResult.getSelectedAnswers(),
                        examResult.getIsDeleted(),
                        questionService.stringToListQuestionDTO(examRepository.findExamByExamIDAndIsDeletedFalse(examResult.getExamID()).get().getListQuestionID(), MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER)
                        )
                );
            } else {
                examResultDTOS.add(new ExamResultDTO(
                                examResult.getExamResultID(),
                                examResult.getExamID(),
                                examResult.getContestantUsername(),
                                examResult.getTestMark(),
                                examResult.getSelectedAnswers(),
                                examResult.getIsDeleted(),
                                questionService.stringToListQuestionDTO(examRepository.findExamByExamIDAndIsDeletedFalse(examResult.getExamID()).get().getListQuestionID(), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER)
                        )
                );
            }
        }
        return examResultDTOS;
    }

    public static ExamResult dtoToEntity(ExamResultDTO examResultDTO) {
        ExamResult examResult = new ExamResult();
        examResult.setExamResultID(examResultDTO.getExamResultID());
        examResult.setExamID(examResultDTO.getExamID());
        examResult.setContestantUsername(examResultDTO.getContestantUsername());
        examResult.setSelectedAnswers(examResultDTO.getSelectedAnswers());
        examResult.setTestMark(examResultDTO.getTestMark());
        examResult.setIsDeleted(examResultDTO.getIsDeleted());
        return examResult;
    }
}
