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
import java.util.Optional;
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

    public List<QuestionDTO> stringToListQuestionDTO(String listQuestionID, Integer questionNumber) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        String[] questionIDs = listQuestionID.split(",");
        for (int i = 0; i < questionNumber; i++) {
            questionDTOList.add(questionService.finQuestionDTOByID(Integer.parseInt(questionIDs[i])));
        }
        return questionDTOList;
    }

    public String getExamNameByTopicAndIndex(String topic) {
        String[] splitStr = topic.split(" ");
        String returnName = "";
        for (int i = 0; i < splitStr.length; i++) {
            returnName += splitStr[i];
        }

        Integer index = 1;
        while (examRepository.findExamByName(returnName + String.valueOf(index)).isPresent()) {
            index++;
        }
        return returnName+String.valueOf(index);
    }

    public List<ExamDTO> findAll() {
        List<ExamDTO> examDTOList = new ArrayList<>();
        List<Exam> examList = examRepository.findAll();
        for (int i = 0; i < examList.size(); i++) {
            if (examList.get(i).getTopic().equals(MessageResource.SYNTHESIS_TOPIC)) {
                examDTOList.add(
                        new ExamDTO(
                                examList.get(i).getExamID(),
                                examList.get(i).getName(),
                                examList.get(i).getTopic(),
                                examList.get(i).getListQuestionID(),
                                examList.get(i).getIsDeleted(),
                                stringToListQuestionDTO(examList.get(i).getListQuestionID(), MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER)
                        )
                );
            } else {
                examDTOList.add(
                        new ExamDTO(
                                examList.get(i).getExamID(),
                                examList.get(i).getName(),
                                examList.get(i).getTopic(),
                                examList.get(i).getListQuestionID(),
                                examList.get(i).getIsDeleted(),
                                stringToListQuestionDTO(examList.get(i).getListQuestionID(), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER)
                        )
                );
            }
        }
        return examDTOList;
    }

    public ResponseEntity<?> findExamByID(Integer id) {
        Optional<Exam> optionalExam = examRepository.findById(id);
        List<QuestionDTO> questionDTOList;
        if (optionalExam.isPresent()) {
            if (optionalExam.get().getTopic().equals(MessageResource.SYNTHESIS_TOPIC)) {
                questionDTOList = stringToListQuestionDTO(optionalExam.get().getListQuestionID(), MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER);
            } else {
                questionDTOList = stringToListQuestionDTO(optionalExam.get().getListQuestionID(), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER);
            }
            return optionalExam.map(exam -> new ResponseEntity<>(
                    ExamMapper.entityToDTO(exam, questionDTOList), HttpStatus.OK)
            ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.EXAM + " " + id + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addExam(ExamDTO examDTO) {
        List<QuestionDTO> examQuestion = new ArrayList<>();
        if (examDTO.getTopic().equals(MessageResource.SYNTHESIS_TOPIC)) {
            examQuestion = randomQuestionToExam(questionService.findAll(), MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER);
        } else {
            examQuestion = randomQuestionToExam(questionService.findQuestionByTopic(examDTO.getTopic()), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER);
        }

        examDTO.setListQuestionID(getRandomListQuestionID(examQuestion));
        examDTO.setName(getExamNameByTopicAndIndex(examDTO.getTopic()));
        if (examRepository.findExamByListQuestionID(examDTO.getListQuestionID()).isPresent()) {
            return new ResponseEntity<String>(MessageResource.EXAM + " " + MessageResource.ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
        } else {
            Exam exam = ExamMapper.dtoToEntity(examDTO);
            examRepository.save(exam);
            return new ResponseEntity<ExamDTO>(
                    new ExamDTO(
                            exam.getExamID(),
                            exam.getName(),
                            exam.getTopic(),
                            getRandomListQuestionID(examQuestion),
                            exam.getIsDeleted(),
                            examQuestion
                    ), HttpStatus.CREATED
            );
        }
    }

    public ResponseEntity<?> updateExam(Integer id, ExamDTO examDTO) {
        Optional<Exam> optionalExam = examRepository.findById(id);
        if (!optionalExam.isPresent()) {
            return new ResponseEntity<String>(MessageResource.EXAM + " " + id + " " + MessageResource.NOT_CREATED_YET, HttpStatus.ALREADY_REPORTED);
        }
//        else if (examRepository.findExamByName(examDTO.getName()).isPresent()) {
//            return new ResponseEntity<String>(MessageResource.EXAM + " " + examDTO.getName() + " " + MessageResource.ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
//        }
        else if (examDTO.getTopic().equals(MessageResource.SYNTHESIS_TOPIC)) {
            if (examDTO.getListQuestionID().split(",").length != MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER) {
                return new ResponseEntity<String>("QUESTION NUMBER FOR " + examDTO.getTopic() + " MUST BE " + MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER, HttpStatus.ALREADY_REPORTED);
            } else {
                return optionalExam.map(exam -> {
                    exam.setName(examDTO.getName());
                    exam.setTopic(examDTO.getTopic());
                    exam.setListQuestionID(examDTO.getListQuestionID());
                    examRepository.save(exam);
                    return new ResponseEntity<ExamDTO>(new ExamDTO(
                            exam.getExamID(),
                            exam.getName(),
                            exam.getTopic(),
                            exam.getListQuestionID(),
                            exam.getIsDeleted(),
                            stringToListQuestionDTO(exam.getListQuestionID(), MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER)
                    ), HttpStatus.OK);
                }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            }
        } else {
            if (examDTO.getListQuestionID().split(",").length != MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER) {
                return new ResponseEntity<String>("QUESTION NUMBER FOR " + examDTO.getTopic() + " MUST BE " + MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER, HttpStatus.ALREADY_REPORTED);
            }
//            else if (!questionService.findQuestionByTopic(examDTO.getTopic()).contains(stringToListQuestionDTO(examDTO.getListQuestionID(), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER))) {
//                return new ResponseEntity<String>(MessageResource.INCORRECT_QUESTION_LIST_WITH_TOPIC, HttpStatus.ALREADY_REPORTED);
//            }
            else {
                return optionalExam.map(exam -> {
                    exam.setName(examDTO.getName());
                    exam.setTopic(examDTO.getTopic());
                    exam.setListQuestionID(examDTO.getListQuestionID());
                    examRepository.save(exam);
                    return new ResponseEntity<ExamDTO>(new ExamDTO(
                            exam.getExamID(),
                            exam.getName(),
                            exam.getTopic(),
                            exam.getListQuestionID(),
                            exam.getIsDeleted(),
                            stringToListQuestionDTO(exam.getListQuestionID(), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER)
                    ), HttpStatus.OK);
                }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            }
        }
    }

    public ResponseEntity<?> deleteExam(Integer id) {
        Optional<Exam> optionalExam = examRepository.findById(id);
        if (optionalExam.isPresent()) {
            return optionalExam.map(exam -> {
                exam.setIsDeleted(true);
                examRepository.save(exam);
                return new ResponseEntity<ExamDTO>(new ExamDTO(
                        exam.getExamID(),
                        exam.getName(),
                        exam.getTopic(),
                        exam.getListQuestionID(),
                        exam.getIsDeleted(),
                        stringToListQuestionDTO(exam.getListQuestionID(), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER)
                ), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.EXAM + " " + id + " " + MessageResource.NOT_CREATED_YET, HttpStatus.ALREADY_REPORTED);
        }
    }
}
