package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.dto.ExamResultDTO;
import com.ifi.fresher_test.ifi_fresher_test.dto.QuestionDTO;
import com.ifi.fresher_test.ifi_fresher_test.mapper.ExamResultMapper;
import com.ifi.fresher_test.ifi_fresher_test.model.Exam;
import com.ifi.fresher_test.ifi_fresher_test.model.ExamResult;
import com.ifi.fresher_test.ifi_fresher_test.repository.ExamResultRepository;
import com.ifi.fresher_test.ifi_fresher_test.util.MessageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamResultService {
    ExamResultRepository examResultRepository;

    @Autowired
    public void setExamResultRepository(ExamResultRepository examResultRepository) {
        this.examResultRepository = examResultRepository;
    }

    ExamService examService;

    @Autowired
    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public List<ExamResultDTO> findAll() {
        List<ExamResult> examResultList = examResultRepository.findAllByIsDeletedFalse().get();
        return ExamResultMapper.arrayEntityToDTO(examResultList, examService.examRepository, questionService);
    }

    public ResponseEntity<?> findExamResultByID(Integer id) {
        Optional<ExamResult> optionalExamResult = examResultRepository.findExamResultByExamResultIDAndIsDeletedFalse(id);
        List<QuestionDTO> questionDTOList;
        if (optionalExamResult.isPresent()) {
            if (optionalExamResult.get().getTopic().equals(MessageResource.SYNTHESIS_TOPIC)) {
                questionDTOList = questionService.stringToListQuestionDTO(examService.examRepository.findExamByExamIDAndIsDeletedFalse(optionalExamResult.get().getExamID()).get().getListQuestionID(), MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER);
            } else {
                questionDTOList = questionService.stringToListQuestionDTO(examService.examRepository.findExamByExamIDAndIsDeletedFalse(optionalExamResult.get().getExamID()).get().getListQuestionID(), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER);
            }
            return optionalExamResult.map(examResult -> new ResponseEntity<>(
                    ExamResultMapper.entityToDTO(examResult, questionDTOList), HttpStatus.OK)
            ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.EXAM + " " + MessageResource.UNTESTED + " " + MessageResource.OR_IS_DELETED, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addExamResult(ExamResultDTO examResultDTO) {
        Exam exam = examService.examRepository.findExamByExamIDAndIsDeletedFalse(examResultDTO.getExamID()).get();
        examResultDTO.setExamName(exam.getName());
        examResultDTO.setTopic(exam.getTopic());
        examResultDTO.setIsDeleted(false);

        List<QuestionDTO> examQuestion;
        if (examResultDTO.getTopic().equals(MessageResource.SYNTHESIS_TOPIC)) {
            examQuestion = examService.questionService.stringToListQuestionDTO(exam.getListQuestionID(), MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER);
        } else {
            examQuestion = examService.questionService.stringToListQuestionDTO(exam.getListQuestionID(), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER);
        }

        ExamResult examResult = ExamResultMapper.dtoToEntity(examResultDTO);
        examResultRepository.save(examResult);
        return new ResponseEntity<ExamResultDTO>(
                new ExamResultDTO(
                        examResult.getExamResultID(),
                        examResult.getExamID(),
                        examResult.getExamName(),
                        examResult.getContestantUsername(),
                        examResult.getTestMark(),
                        examResult.getTopic(),
                        examResult.getIsDeleted(),
                        examQuestion
                ), HttpStatus.CREATED
        );
    }

    public ResponseEntity<?> deleteExamResult(Integer id) {
        Optional<ExamResult> optionalExamResult = examResultRepository.findExamResultByExamResultIDAndIsDeletedFalse(id);
        if (optionalExamResult.isPresent()) {
            return optionalExamResult.map(examResult -> {
                examResult.setIsDeleted(true);
                examResultRepository.save(examResult);
                Exam exam = examService.examRepository.findExamByExamIDAndIsDeletedFalse(optionalExamResult.get().getExamID()).get();
                if (optionalExamResult.get().getTopic().equals(MessageResource.SYNTHESIS_TOPIC)) {
                    return new ResponseEntity<ExamResultDTO>(new ExamResultDTO(
                            examResult.getExamResultID(),
                            examResult.getExamID(),
                            examResult.getExamName(),
                            examResult.getContestantUsername(),
                            examResult.getTestMark(),
                            examResult.getTopic(),
                            examResult.getIsDeleted(),
                            questionService.stringToListQuestionDTO(exam.getListQuestionID(), MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER)
                    ), HttpStatus.OK);
                } else {
                    return new ResponseEntity<ExamResultDTO>(new ExamResultDTO(
                            examResult.getExamResultID(),
                            examResult.getExamID(),
                            examResult.getExamName(),
                            examResult.getContestantUsername(),
                            examResult.getTestMark(),
                            examResult.getTopic(),
                            examResult.getIsDeleted(),
                            questionService.stringToListQuestionDTO(exam.getListQuestionID(), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER)
                    ), HttpStatus.OK);
                }
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.EXAM + " " + MessageResource.UNTESTED + " " + MessageResource.OR_IS_DELETED, HttpStatus.NOT_FOUND);
        }
    }
}
