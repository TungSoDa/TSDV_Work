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
        StringBuilder listQuestionID = new StringBuilder();
        for (int i = 0; i < questionDTOList.size(); i++) {
            if (i == questionDTOList.size()-1) {
                listQuestionID.append(questionDTOList.get(i).getQuestionID().toString());
            }
            else {
                listQuestionID.append(questionDTOList.get(i).getQuestionID().toString()).append(",");
            }
        }
        return listQuestionID.toString();
    }

    public String getExamNameByTopicAndIndex(String topic) {
        String[] splitStr = topic.split(" ");
        StringBuilder returnName = new StringBuilder();
        for (String s : splitStr) {
            returnName.append(s);
        }

        Integer index = 1;
        while (examRepository.findExamByName(returnName + String.valueOf(index)).isPresent()) {
            index++;
        }
        return returnName+String.valueOf(index);
    }

    public boolean isAllTopicQuestionContainListQuestionID(String allTopicQuestion, String listQuestionID) {
        String[] allTopicQuestionID = allTopicQuestion.split(",");
        String[] examListQuestionID = listQuestionID.split(",");
        Integer count = 0;
        for (String allQuestion : allTopicQuestionID) {
            for (String examQuestion : examListQuestionID) {
                if (examQuestion.equals(allQuestion)) {
                    count++;
                }
            }
        }
        return count.equals(examListQuestionID.length);
    }

    public boolean isSameQuestionList(ExamDTO examDTO, List<Exam> topicExamList, Integer examID) {
        String[] addQuestionList = examDTO.getListQuestionID().split(",");

        int count = 0;

        for (Exam exam : topicExamList) {
            if (examDTO.getListQuestionID().equals(exam.getListQuestionID()) && exam.getExamID().equals(examID)) {
                break;
            } else {
                for (String s : addQuestionList) {
                    if (exam.getListQuestionID().contains(s)) {
                        count++;
                    }
                }
                if (count == addQuestionList.length) {
                    break;
                } else {
                    count = 0;
                }
            }
        }

        if (examDTO.getTopic().equals(MessageResource.SYNTHESIS_TOPIC)) {
            return count == MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER;
        } else {
            return count == MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER;
        }

    }

    public ExamDTO findExamDTOByID(Integer id) {
        Optional<Exam> optionalExam = examRepository.findExamByExamIDAndIsDeletedFalse(id);
        List<QuestionDTO> questionDTOList = null;
        if (optionalExam.isPresent()) {
            if (optionalExam.get().getTopic().equals(MessageResource.SYNTHESIS_TOPIC)) {
                questionDTOList = questionService.stringToListQuestionDTO(optionalExam.get().getListQuestionID(), MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER);
            } else {
                questionDTOList = questionService.stringToListQuestionDTO(optionalExam.get().getListQuestionID(), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER);
            }
        }
        return ExamMapper.entityToDTO(optionalExam.get(), questionDTOList);
    }

    public List<ExamDTO> findAll() {
        List<Exam> examList = examRepository.findAllByIsDeletedFalse().get();
        return ExamMapper.arrayEntityToDTO(examList, questionService);
    }

    public ResponseEntity<?> findExamByID(Integer id) {
        Optional<Exam> optionalExam = examRepository.findExamByExamIDAndIsDeletedFalse(id);
        List<QuestionDTO> questionDTOList;
        if (optionalExam.isPresent()) {
            if (optionalExam.get().getTopic().equals(MessageResource.SYNTHESIS_TOPIC)) {
                questionDTOList = questionService.stringToListQuestionDTO(optionalExam.get().getListQuestionID(), MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER);
            } else {
                questionDTOList = questionService.stringToListQuestionDTO(optionalExam.get().getListQuestionID(), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER);
            }
            return optionalExam.map(exam -> new ResponseEntity<>(
                    ExamMapper.entityToDTO(exam, questionDTOList), HttpStatus.OK)
            ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.EXAM + " " + MessageResource.NOT_CREATED_YET + " " + MessageResource.OR_IS_DELETED, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> findExamByTopic(String topic) {
        String topicData = topic.replace('_', ' ');
        Optional<List<Exam>> optionalExamList = examRepository.findExamByTopicAndIsDeletedFalse(topicData);
        if (optionalExamList.isPresent()) {
            return optionalExamList.map(examList -> new ResponseEntity<>(
                    ExamMapper.arrayEntityToDTO(examList, questionService), HttpStatus.OK)
            ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.EXAM + " " + MessageResource.IS_EMPTY, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addExam(ExamDTO examDTO) {
        List<QuestionDTO> examQuestion;
        if (examDTO.getListQuestionID() == null) {
            if (examDTO.getTopic().equals(MessageResource.SYNTHESIS_TOPIC)) {
                examQuestion = randomQuestionToExam(questionService.findAll(), MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER);
            } else {
                if (questionService.findQuestionByTopic(examDTO.getTopic()).isEmpty()) {
                    return new ResponseEntity<String>(MessageResource.NO_QUESTIONS_WITH_THIS_TOPIC, HttpStatus.NOT_FOUND);
                } else {
                    examQuestion = randomQuestionToExam(questionService.findQuestionByTopic(examDTO.getTopic()), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER);
                }
            }
        } else {
            if (examDTO.getTopic().equals(MessageResource.SYNTHESIS_TOPIC)) {
                if (examDTO.getListQuestionID().split(",").length != MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER) {
                    return new ResponseEntity<String>(MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER_MUST_BE + " " + MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER, HttpStatus.BAD_REQUEST);
                } else {
                    examQuestion = questionService.stringToListQuestionDTO(examDTO.getListQuestionID(), MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER);
                }
            } else {
                if (examDTO.getListQuestionID().split(",").length != MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER) {
                    return new ResponseEntity<String>(MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER_MUST_BE + " " + MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER, HttpStatus.BAD_REQUEST);
                } else {
                    examQuestion = questionService.stringToListQuestionDTO(examDTO.getListQuestionID(), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER);
                }
            }
        }

        examDTO.setName(getExamNameByTopicAndIndex(examDTO.getTopic()));

        examDTO.setListQuestionID(getRandomListQuestionID(examQuestion));

        examDTO.setIsDeleted(false);

        if (isSameQuestionList(examDTO, examRepository.findExamByTopicAndIsDeletedFalse(examDTO.getTopic()).get(), 0)) {
            return new ResponseEntity<String>(MessageResource.EXAM + " " + MessageResource.WITH_QUESTION_LIST_IS_CREATED, HttpStatus.NOT_FOUND);
        } else if (!isAllTopicQuestionContainListQuestionID(getRandomListQuestionID(questionService.findQuestionByTopic(examDTO.getTopic())), examDTO.getListQuestionID()) && !examDTO.getTopic().equals(MessageResource.SYNTHESIS_TOPIC)) {
            return new ResponseEntity<String>(MessageResource.INCORRECT_QUESTION_LIST_WITH_TOPIC, HttpStatus.NOT_FOUND);
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
        Optional<Exam> optionalExam = examRepository.findExamByExamIDAndIsDeletedFalse(id);
        if (!optionalExam.isPresent()) {
            return new ResponseEntity<String>(MessageResource.EXAM + " " + MessageResource.NOT_CREATED_YET + " " + MessageResource.OR_IS_DELETED, HttpStatus.NOT_FOUND);
        } else if (examRepository.findExamByNameAndIsDeletedFalse(examDTO.getName()).isPresent() && !examRepository.findExamByExamIDAndIsDeletedFalse(id).get().getName().equals(examDTO.getName())) {
            return new ResponseEntity<String>(MessageResource.EXAM + " " + MessageResource.WITH_THE_SAME_NAME + " " + MessageResource.ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
        } else if (isSameQuestionList(examDTO, examRepository.findExamByTopicAndIsDeletedFalse(examDTO.getTopic()).get(), id)) {
            return new ResponseEntity<String>(MessageResource.EXAM + " " + MessageResource.WITH_QUESTION_LIST_IS_CREATED, HttpStatus.NOT_FOUND);
        } else if (examDTO.getTopic().equals(MessageResource.SYNTHESIS_TOPIC)) {
            if (examDTO.getListQuestionID().split(",").length != MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER) {
                return new ResponseEntity<String>(MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER_MUST_BE + " " + MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER, HttpStatus.BAD_REQUEST);
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
                            questionService.stringToListQuestionDTO(exam.getListQuestionID(), MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER)
                    ), HttpStatus.OK);
                }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            }
        } else {
            if (examDTO.getListQuestionID().split(",").length != MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER) {
                return new ResponseEntity<String>(MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER_MUST_BE + " " + MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER, HttpStatus.BAD_REQUEST);
            } else if (!isAllTopicQuestionContainListQuestionID(getRandomListQuestionID(questionService.findQuestionByTopic(examDTO.getTopic())), examDTO.getListQuestionID())) {
                return new ResponseEntity<String>(MessageResource.INCORRECT_QUESTION_LIST_WITH_TOPIC, HttpStatus.NOT_FOUND);
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
                            questionService.stringToListQuestionDTO(exam.getListQuestionID(), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER)
                    ), HttpStatus.OK);
                }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            }
        }
    }

    public ResponseEntity<?> deleteExam(Integer id) {
        Optional<Exam> optionalExam = examRepository.findExamByExamIDAndIsDeletedFalse(id);
        if (optionalExam.isPresent()) {
            return optionalExam.map(exam -> {
                exam.setIsDeleted(true);
                examRepository.save(exam);
                if (exam.getTopic().equals(MessageResource.SYNTHESIS_TOPIC)) {
                    return new ResponseEntity<ExamDTO>(new ExamDTO(
                            exam.getExamID(),
                            exam.getName(),
                            exam.getTopic(),
                            exam.getListQuestionID(),
                            exam.getIsDeleted(),
                            questionService.stringToListQuestionDTO(exam.getListQuestionID(), MessageResource.ALL_TOPIC_EXAM_QUESTION_NUMBER)
                    ), HttpStatus.OK);
                } else {
                    return new ResponseEntity<ExamDTO>(new ExamDTO(
                            exam.getExamID(),
                            exam.getName(),
                            exam.getTopic(),
                            exam.getListQuestionID(),
                            exam.getIsDeleted(),
                            questionService.stringToListQuestionDTO(exam.getListQuestionID(), MessageResource.ONE_TOPIC_EXAM_QUESTION_NUMBER)
                    ), HttpStatus.OK);
                }
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.EXAM + " " + MessageResource.NOT_CREATED_YET + " " + MessageResource.OR_IS_DELETED, HttpStatus.NOT_FOUND);
        }
    }

    public List<ExamDTO> getDeletedExamList() {
        return ExamMapper.arrayEntityToDTO(examRepository.findExamsByIsDeletedTrue().get(), questionService);
    }
}
