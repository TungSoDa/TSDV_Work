package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.dto.ExamDTO;
import com.ifi.fresher_test.ifi_fresher_test.dto.QuestionDTO;
import com.ifi.fresher_test.ifi_fresher_test.mapper.ExamMapper;
import com.ifi.fresher_test.ifi_fresher_test.model.Exam;
import com.ifi.fresher_test.ifi_fresher_test.repository.ExamRepository;
import com.ifi.fresher_test.ifi_fresher_test.util.MessageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ExamService {
    ExamRepository examRepository;

    @Autowired
    public void setExamRepository(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public List<QuestionDTO> randomQuestionToExam(List<QuestionDTO> questionList, Integer questionNumber) {
        List<QuestionDTO> examQuestion = new ArrayList();
        Random random = new Random();
        for (int i = 0; i < questionNumber; i++) {
            int randomIndex = random.nextInt(questionList.size());
            QuestionDTO questionDTO = questionList.get(randomIndex);
            examQuestion.add(questionDTO);
        }
        return examQuestion;
    }

    public ResponseEntity<?> addExam(ExamDTO examDTO) {
        Exam exam = ExamMapper.dtoToEntity(examDTO);
//        examRepository.save(exam);
        return new ResponseEntity<ExamDTO>(
                new ExamDTO(
                        exam.getExamID(),
                        exam.getTopic(),
                        exam.getIsDeleted(),
                        randomQuestionToExam(questionService.findQuestionByTopic(examDTO.getTopic()), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER)
                ), HttpStatus.CREATED
        );
    }
}
