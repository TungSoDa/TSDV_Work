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

    AnswerService answerService;

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    public List<QuestionDTO> randomQuestionToExam(List<QuestionDTO> questionList, Integer questionNumber) {
        List<QuestionDTO> examQuestion = new ArrayList();
        Random random = new Random();
        for (int i = 0; i < questionNumber; i++) {
            int randomIndex = random.nextInt(questionList.size());
            QuestionDTO questionDTO = questionList.get(randomIndex);
            if (!examQuestion.contains(questionDTO)) {
                questionDTO.setAnswerList(answerService.findListAnswerByQuestionID(questionDTO.getQuestionID()));
                examQuestion.add(questionDTO);
            }
            else {
                questionNumber++;
            }
        }
        return examQuestion;
    }

    public String getRandomListQuestionID(List<QuestionDTO> questionDTOList) {
        String listQuestionID = "";
        for (int i = 0; i < questionDTOList.size(); i++) {
            if (i == questionDTOList.size()-1) {
                listQuestionID += questionDTOList.get(i).getQuestionID().toString();
            }
            else {
                listQuestionID += questionDTOList.get(i).getQuestionID().toString() + ",";
            }
        }
        return listQuestionID;
    }

//    public List<ExamDTO> findAll() {
//        List<ExamDTO> examDTOList = new ArrayList<>();
//        List<Exam> examList = examRepository.findAll();
//        for (int i = 0; i < examList.size(); i++) {
//            examDTOList.add(
//                    new ExamDTO(
//                            examList.get(i).getExamID(),
//                            examList.get(i).getTopic(),
//                            examList.get(i).getIsDeleted(),
//                            randomQuestionToExam(questionService.findQuestionByTopic(examList.get(i).getTopic()), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER)
//                    )
//            );
//        }
//        return examDTOList;
//    }

//    public ResponseEntity<?> findExamByID(Integer id) {
//        Optional<Exam> optionalExam = examRepository.findById(id);
//        List<QuestionDTO> questionDTOList;
//        if (optionalExam.isPresent()) {
//            return optionalExam.map(exam -> new ResponseEntity<>(
//                    ExamMapper.entityToDTO(exam, questionDTOList), HttpStatus.OK)
//            ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        } else {
//
//        }
//    }

    public ResponseEntity<?> addExam(ExamDTO examDTO) {
        Exam exam = ExamMapper.dtoToEntity(examDTO);
//        examRepository.save(exam);
        return new ResponseEntity<ExamDTO>(
                new ExamDTO(
                        exam.getExamID(),
                        exam.getTopic(),
                        getRandomListQuestionID(randomQuestionToExam(questionService.findQuestionByTopic(examDTO.getTopic()), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER)),
                        exam.getIsDeleted(),
                        randomQuestionToExam(questionService.findQuestionByTopic(examDTO.getTopic()), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER)
                ), HttpStatus.CREATED
        );
    }
}
