package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.dto.AnswerDTO;
import com.ifi.fresher_test.ifi_fresher_test.mapper.AnswerMapper;
import com.ifi.fresher_test.ifi_fresher_test.model.Answer;
import com.ifi.fresher_test.ifi_fresher_test.repository.AnswerRepository;
import com.ifi.fresher_test.ifi_fresher_test.util.MessageResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    AnswerRepository answerRepository;

    @Autowired
    public void setAnswerRepository(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public static final Logger logger = LoggerFactory.getLogger(AnswerService.class);

    public List<AnswerDTO> findAllByIsDeletedFalse() {
        logger.info(MessageResource.SHOW_ALL_ANSWER);
        return AnswerMapper.arrayEntityToDTO(answerRepository.findAllByIsDeletedFalse().get());
    }

    public AnswerDTO findAnswerDTOByID(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findAnswersByAnswerIDAndIsDeletedFalse(id);
        return AnswerMapper.entityToDTO(optionalAnswer.get());
    }

    public ResponseEntity<?> findAnswerByID(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findAnswersByAnswerIDAndIsDeletedFalse(id);
        if (optionalAnswer.isPresent()) {
            logger.info(MessageResource.SHOW_ANSWER_BY_ID + " " + id);
            return optionalAnswer.map(answer -> new ResponseEntity<>(
                    AnswerMapper.entityToDTO(answer), HttpStatus.OK)
            ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            logger.error(MessageResource.ANSWER + " " + MessageResource.NOT_CREATED_YET  + " " + MessageResource.OR_IS_DELETED);
            return new ResponseEntity<String>(MessageResource.ANSWER + " " + MessageResource.NOT_CREATED_YET  + " " + MessageResource.OR_IS_DELETED, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> findAnswerByQuestionID(Integer questionID) {
        Optional<List<Answer>> optionalAnswerList = answerRepository.findAnswersByQuestionIDAndIsDeletedFalse(questionID);
        if (optionalAnswerList.isPresent()) {
            logger.info(MessageResource.SHOW_LIST_ANSWER_OF_QUESTION + " " + questionID);
            return optionalAnswerList.map(answers -> new ResponseEntity<List<AnswerDTO>>(
                    AnswerMapper.arrayEntityToDTO(answers), HttpStatus.OK)
            ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            logger.error(MessageResource.QUESTION + " " + MessageResource.NOT_CREATED_YET + " " + MessageResource.OR_IS_DELETED);
            return new ResponseEntity<String>(MessageResource.QUESTION + " " + MessageResource.NOT_CREATED_YET + " " + MessageResource.OR_IS_DELETED, HttpStatus.NOT_FOUND);
        }
    }

    public List<AnswerDTO> findListAnswerByQuestionID(Integer questionID) {
        List<Answer> answerList = answerRepository.findAnswersByQuestionIDAndIsDeletedFalse(questionID).get();
        Collections.shuffle(answerList);
        return  AnswerMapper.arrayEntityToDTO(answerList);
    }

    public ResponseEntity<?> getCorrectAnswerOfQuestion(Integer questionID) {
        Optional<Answer> optionalAnswer = answerRepository.findAnswersByQuestionIDAndIsCorrectTrueAndIsDeletedFalse(questionID);
        if (optionalAnswer.isPresent()) {
            logger.info(MessageResource.SHOW_CORRECTED_ANSWER_OF_QUESTION + " " + questionID);
            return optionalAnswer.map(answer -> new ResponseEntity<AnswerDTO>(
                    AnswerMapper.entityToDTO(answer), HttpStatus.OK)
            ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            logger.error(MessageResource.QUESTION_HAS_NO_CORRECT_ANSWER_YET);
            return new ResponseEntity<String>(MessageResource.QUESTION_HAS_NO_CORRECT_ANSWER_YET, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addAnswer(AnswerDTO answerDTO) {
        answerDTO.setIsDeleted(false);
        try {
            if (!answerRepository.findAnswersByContentAndQuestionIDAndIsDeletedFalse(answerDTO.getContent(), answerDTO.getQuestionID()).isPresent()) {
                if (answerRepository.findAnswersByContentAndQuestionIDAndIsDeletedFalse(answerDTO.getContent(), answerDTO.getQuestionID()).isPresent()) {
                    logger.error(MessageResource.ANSWER_CONTENT_ALREADY_EXISTS_IN_THIS_QUESTION);
                    return new ResponseEntity<String>(MessageResource.ANSWER_CONTENT_ALREADY_EXISTS_IN_THIS_QUESTION, HttpStatus.ALREADY_REPORTED);
                }
                else if(answerRepository.findAnswersByQuestionIDAndIsCorrectTrueAndIsDeletedFalse(answerDTO.getQuestionID()).isPresent() && answerDTO.getIsCorrect().equals(true)) {
                    logger.error(MessageResource.ONLY_ONE_CORRECT_ANSWER_IN_THIS_QUESTION);
                    return new ResponseEntity<String>(MessageResource.ONLY_ONE_CORRECT_ANSWER_IN_THIS_QUESTION, HttpStatus.ALREADY_REPORTED);
                } else {
                    Answer answer = AnswerMapper.dtoToEntity(answerDTO);
                    answerRepository.save(answer);
                    logger.info(MessageResource.ADD_ANSWER_SUCCESSFUL);
                    return new ResponseEntity<AnswerDTO>(
                            new AnswerDTO(
                                    answer.getAnswerID(),
                                    answer.getContent(),
                                    answer.getIsCorrect(),
                                    answer.getIsDeleted(),
                                    answer.getQuestionID()
                            ), HttpStatus.CREATED
                    );
                }
            } else {
                logger.error(MessageResource.ANSWER + " " + MessageResource.ALREADY_EXISTS + " trong câu hỏi");
                return new ResponseEntity<String>(MessageResource.ANSWER + " " + MessageResource.ALREADY_EXISTS + " trong câu hỏi", HttpStatus.ALREADY_REPORTED);
            }
        } catch (DataIntegrityViolationException e) {
            logger.error(MessageResource.QUESTION + " " + MessageResource.NOT_CREATED_YET + " " + MessageResource.OR_IS_DELETED);
            return new ResponseEntity<String>(MessageResource.QUESTION + " " + MessageResource.NOT_CREATED_YET + " " + MessageResource.OR_IS_DELETED, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> updateAnswer(Integer id, AnswerDTO answerDTO) {
        Optional<Answer> optionalAnswer = answerRepository.findAnswersByAnswerIDAndIsDeletedFalse(id);
        if (optionalAnswer.isPresent()) {
            if (answerRepository.findAnswersByContentAndQuestionIDAndIsDeletedFalse(answerDTO.getContent(), answerDTO.getQuestionID()).isPresent() &&
                    answerRepository.findAnswersByContentAndQuestionIDAndIsDeletedFalse(answerDTO.getContent(), answerDTO.getQuestionID()).get().getIsCorrect().equals(answerDTO.getIsCorrect())) {
                logger.error(MessageResource.ANSWER_CONTENT_ALREADY_EXISTS_IN_THIS_QUESTION + " " + MessageResource.OR_NOT_CHANGE_CONTENT_QUESTION);
                return new ResponseEntity<String>(MessageResource.ANSWER_CONTENT_ALREADY_EXISTS_IN_THIS_QUESTION + " " + MessageResource.OR_NOT_CHANGE_CONTENT_QUESTION, HttpStatus.ALREADY_REPORTED);
            } else if(answerRepository.findAnswersByQuestionIDAndIsCorrectTrueAndIsDeletedFalse(answerDTO.getQuestionID()).isPresent() && answerDTO.getIsCorrect().equals(true) &&
                    !answerRepository.findAnswersByQuestionIDAndIsCorrectTrueAndIsDeletedFalse(answerDTO.getQuestionID()).get().getAnswerID().equals(id)) {
                logger.error(MessageResource.ONLY_ONE_CORRECT_ANSWER_IN_THIS_QUESTION);
                return new ResponseEntity<String>(MessageResource.ONLY_ONE_CORRECT_ANSWER_IN_THIS_QUESTION, HttpStatus.ALREADY_REPORTED);
            } else {
                return optionalAnswer.map(answer -> {
                    answer.setContent(answerDTO.getContent());
                    answer.setIsCorrect(answerDTO.getIsCorrect());
                    answer.setQuestionID(answerDTO.getQuestionID());
                    answerRepository.save(answer);
                    logger.info(MessageResource.EDIT_ANSWER_SUCCESSFUL);
                    return new ResponseEntity<AnswerDTO>(new AnswerDTO(
                            answer.getAnswerID(),
                            answer.getContent(),
                            answer.getIsCorrect(),
                            answer.getIsDeleted(),
                            answer.getQuestionID()
                    ), HttpStatus.OK);
                }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            }
        } else {
            logger.error(MessageResource.ANSWER + " " + MessageResource.NOT_CREATED_YET + " " + MessageResource.OR_IS_DELETED);
            return new ResponseEntity<String>(MessageResource.ANSWER + " " + MessageResource.NOT_CREATED_YET + " " + MessageResource.OR_IS_DELETED, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteAnswer(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findAnswersByAnswerIDAndIsDeletedFalse(id);
        if (optionalAnswer.isPresent()) {
            return optionalAnswer.map(answer -> {
                answer.setIsDeleted(true);
                answerRepository.save(answer);
                logger.info(MessageResource.DELETE_ANSWER_SUCCESSFUL);
                return new ResponseEntity<AnswerDTO>(new AnswerDTO(
                        answer.getAnswerID(),
                        answer.getContent(),
                        answer.getIsCorrect(),
                        answer.getIsDeleted(),
                        answer.getQuestionID()
                ), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            logger.error(MessageResource.ANSWER + " " + MessageResource.NOT_CREATED_YET + " " + MessageResource.OR_IS_DELETED);
            return new ResponseEntity<String>(MessageResource.ANSWER + " " + MessageResource.NOT_CREATED_YET + " " + MessageResource.OR_IS_DELETED, HttpStatus.NOT_FOUND);
        }
    }

    public List<AnswerDTO> getDeletedAnswerList() {
        return AnswerMapper.arrayEntityToDTO(answerRepository.findAnswersByIsDeletedTrue().get());
    }
}
