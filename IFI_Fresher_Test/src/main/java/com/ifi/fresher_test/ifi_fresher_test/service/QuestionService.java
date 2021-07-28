package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.dto.AnswerDTO;
import com.ifi.fresher_test.ifi_fresher_test.dto.QuestionDTO;
import com.ifi.fresher_test.ifi_fresher_test.mapper.QuestionMapper;
import com.ifi.fresher_test.ifi_fresher_test.model.Question;
import com.ifi.fresher_test.ifi_fresher_test.repository.QuestionRepository;
import com.ifi.fresher_test.ifi_fresher_test.util.MessageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    QuestionRepository questionRepository;

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    AnswerService answerService;

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    public List<QuestionDTO> stringToListQuestionDTO(String listQuestionID, Integer questionNumber) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        String[] questionIDs = listQuestionID.split(",");
        for (int i = 0; i < questionNumber; i++) {
            questionDTOList.add(findQuestionDTOByID(Integer.parseInt(questionIDs[i])));
        }
        Collections.shuffle(questionDTOList);
        return questionDTOList;
    }

    public List<QuestionDTO> findAll() {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questionList = questionRepository.findAllByIsDeletedFalse().get();
        for (Question question : questionList) {
            questionDTOList.add(
                    new QuestionDTO(
                            question.getQuestionId(),
                            question.getContent(),
                            question.getImage(),
                            question.getTopic(),
                            question.getIsDeleted(),
                            answerService.findListAnswerByQuestionID(question.getQuestionId())
                    )
            );
        }
        return questionDTOList;
    }

    public ResponseEntity<?> findQuestionByID(Integer id) {
        Optional<Question> optionalQuestion = questionRepository.findQuestionByQuestionIdAndIsDeletedFalse(id);
        List<AnswerDTO> answerList = answerService.findListAnswerByQuestionID(id);
        if (optionalQuestion.isPresent()) {
            return optionalQuestion.map(question -> new ResponseEntity<>(
                    QuestionMapper.entityToDTO(question, answerList), HttpStatus.OK)
            ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.QUESTION + " " + MessageResource.NOT_CREATED_YET + " " + MessageResource.OR_IS_DELETED, HttpStatus.NOT_FOUND);
        }
    }

    public QuestionDTO findQuestionDTOByID(Integer id) {
        Optional<Question> optionalQuestion = questionRepository.findQuestionByQuestionIdAndIsDeletedFalse(id);
        List<AnswerDTO> answerList = answerService.findListAnswerByQuestionID(id);
        return QuestionMapper.entityToDTO(optionalQuestion.get(), answerList);
    }

    public List<QuestionDTO> findQuestionByTopic(String topic) {
        Optional<List<Question>> optionalQuestion = questionRepository.findQuestionByTopicAndIsDeletedFalse(topic);
        return QuestionMapper.arrayEntityToDTO(optionalQuestion.get(), answerService);
    }

    public ResponseEntity<?> findQuestionDTOByTopic(String topic) {
        Optional<List<Question>> optionalQuestion = questionRepository.findQuestionByTopicAndIsDeletedFalse(topic.replace("_", " "));
        if (optionalQuestion.isPresent()) {
            return optionalQuestion.map(question -> new ResponseEntity<>(
                    QuestionMapper.arrayEntityToDTO(optionalQuestion.get(), answerService), HttpStatus.OK)
            ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.QUESTION + " " + MessageResource.NOT_CREATED_YET + " " + MessageResource.OR_IS_DELETED, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addQuestion(QuestionDTO questionDTO) {
        questionDTO.setIsDeleted(false);
        if (questionRepository.findQuestionByContentAndTopicAndIsDeletedFalse(questionDTO.getContent(), questionDTO.getTopic()).isPresent()) {
            return new ResponseEntity<String>(MessageResource.THIS_QUESTION_CONTENT + " " + MessageResource.ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
        } else {
            Question question = QuestionMapper.dtoToEntity(questionDTO);
            questionRepository.save(question);
            return new ResponseEntity<QuestionDTO>(
                    new QuestionDTO(
                            question.getQuestionId(),
                            question.getContent(),
                            question.getImage(),
                            question.getTopic(),
                            question.getIsDeleted()
                    ), HttpStatus.CREATED
            );
        }
    }

    public ResponseEntity<?> updateQuestion(Integer id, QuestionDTO questionDTO) {
        Optional<Question> optionalQuestion = questionRepository.findQuestionByQuestionIdAndIsDeletedFalse(id);
        if (!optionalQuestion.isPresent()) {
            return new ResponseEntity<String>(MessageResource.QUESTION + " " + id + " " + MessageResource.NOT_CREATED_YET + " " + MessageResource.OR_IS_DELETED, HttpStatus.ALREADY_REPORTED);
        } else if (questionRepository.findQuestionByContentAndTopicAndIsDeletedFalse(questionDTO.getContent(), questionDTO.getTopic()).isPresent()) {
            return new ResponseEntity<String>(MessageResource.THIS_QUESTION_CONTENT + " " + MessageResource.ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
        } else if (questionRepository.findQuestionByQuestionIdAndIsDeletedFalse(id).get().getContent().toLowerCase().contains(questionDTO.getContent().toLowerCase()) ||
                questionDTO.getContent().toLowerCase().contains(questionRepository.findQuestionByQuestionIdAndIsDeletedFalse(id).get().getContent().toLowerCase())) {
            return new ResponseEntity<String>(MessageResource.THIS_QUESTION_CONTENT + "  " + MessageResource.MAY_BE_THE_SAME_CONTENT_AS_EXISTING_QUESTION, HttpStatus.ALREADY_REPORTED);
        } else {
            return optionalQuestion.map(question -> {
                question.setContent(questionDTO.getContent());
                question.setImage(questionDTO.getImage());
                question.setTopic(questionDTO.getTopic());
                questionRepository.save(question);
                return new ResponseEntity<QuestionDTO>(new QuestionDTO(
                        question.getQuestionId(),
                        question.getContent(),
                        question.getImage(),
                        question.getTopic(),
                        question.getIsDeleted()
                ), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }

    public ResponseEntity<?> deleteQuestion(Integer id) {
        Optional<Question> optionalQuestion = questionRepository.findQuestionByQuestionIdAndIsDeletedFalse(id);
        if (optionalQuestion.isPresent()) {
            return optionalQuestion.map(question -> {
                question.setIsDeleted(true);
                questionRepository.save(question);
                return new ResponseEntity<QuestionDTO>(new QuestionDTO(
                        question.getQuestionId(),
                        question.getContent(),
                        question.getImage(),
                        question.getTopic(),
                        question.getIsDeleted(),
                        answerService.findListAnswerByQuestionID(question.getQuestionId())
                ), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.QUESTION + " " + MessageResource.NOT_CREATED_YET + " " + MessageResource.OR_IS_DELETED, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> undeleteQuestion(Integer id) {
        Optional<Question> optionalQuestion = questionRepository.findQuestionByQuestionIdAndIsDeletedTrue(id);
        if (optionalQuestion.isPresent()) {
            return optionalQuestion.map(question -> {
                question.setIsDeleted(false);
                questionRepository.save(question);
                return new ResponseEntity<QuestionDTO>(new QuestionDTO(
                        question.getQuestionId(),
                        question.getContent(),
                        question.getImage(),
                        question.getTopic(),
                        question.getIsDeleted(),
                        answerService.findListAnswerByQuestionID(question.getQuestionId())
                ), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.QUESTION + " " + MessageResource.NOT_CREATED_YET + " " + MessageResource.OR_IS_NOT_DELETED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public List<QuestionDTO> getDeletedQuestionList() {
        return QuestionMapper.arrayEntityToDTO(questionRepository.findQuestionsByIsDeletedTrue().get(), answerService);
    }
}
