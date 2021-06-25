package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.dto.AnswerDTO;
import com.ifi.fresher_test.ifi_fresher_test.mapper.AnswerMapper;
import com.ifi.fresher_test.ifi_fresher_test.model.Answer;
import com.ifi.fresher_test.ifi_fresher_test.repository.AnswerRepository;
import com.ifi.fresher_test.ifi_fresher_test.util.MessageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    AnswerRepository answerRepository;

    @Autowired
    public void setAnswerRepository(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<AnswerDTO> findAll() {
        return AnswerMapper.arrayEntityToDTO(answerRepository.findAll());
    }

    public ResponseEntity<AnswerDTO> findAnswerByID(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        return optionalAnswer.map(answer -> new ResponseEntity<>(
                AnswerMapper.entityToDTO(answer), HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<List<AnswerDTO>> findAnswerByQuestionID(Integer questionID) {
        Optional<List<Answer>> optionalAnswerList = answerRepository.findAnswersByQuestionID(questionID);
        return optionalAnswerList.map(answers -> new ResponseEntity<>(
                AnswerMapper.arrayEntityToDTO(answers), HttpStatus.OK)
        ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    public ResponseEntity<AnswerDTO> getCorrectAnswerOfQuestion(Integer questionID) {
//        Optional<Answer> optionalAnswerList = answerRepository.findAnswersByQuestionIDAndIsCorrectTrue(questionID);
//        return optionalAnswerList.map(answer -> new ResponseEntity<>(
//                AnswerMapper.entityToDTO(answer), HttpStatus.OK)
//        ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    public ResponseEntity<?> addAnswer(AnswerDTO answerDTO) {
        try {
            if (!answerRepository.findAnswersByContentAndQuestionID(answerDTO.getContent(), answerDTO.getQuestionID()).isPresent()) {
                if (answerRepository.findAnswersByQuestionID(answerDTO.getQuestionID()).get().contains(answerDTO.getContent())) {
                    return new ResponseEntity<String>(MessageResource.ANSWER_CONTENT_ALREADY_EXISTS_IN_THIS_QUESTION, HttpStatus.ALREADY_REPORTED);
                }
                else if(answerRepository.findAnswersByQuestionIDAndIsCorrectTrue(answerDTO.getQuestionID()).isPresent() && answerDTO.getIsCorrect().equals(true)) {
                    return new ResponseEntity<String>(MessageResource.ONLY_ONE_CORRECT_ANSWER_IN_THIS_QUESTION, HttpStatus.ALREADY_REPORTED);
                }
                else {
                    Answer answer = AnswerMapper.dtoToEntity(answerDTO);
                    answerRepository.save(answer);
                    return new ResponseEntity<AnswerDTO>(
                            new AnswerDTO(
//                                answer.getAnswerID(),
                                    answer.getContent(),
                                    answer.getIsCorrect(),
                                    answer.getIsDeleted(),
                                    answer.getQuestionID()
                            ), HttpStatus.CREATED
                    );
                }
            } else {
                return new ResponseEntity<String>(MessageResource.ANSWER_ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<String>(MessageResource.ANSWER_ID_ALREADY_EXISTS, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> updateAnswer(Integer id, AnswerDTO answerDTO) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent()) {
            if (answerRepository.findAnswersByQuestionID(answerDTO.getQuestionID()).get().contains(answerDTO.getContent())) {
                return new ResponseEntity<String>(MessageResource.ANSWER_CONTENT_ALREADY_EXISTS_IN_THIS_QUESTION, HttpStatus.ALREADY_REPORTED);
            }
            else if(answerRepository.findAnswersByQuestionIDAndIsCorrectTrue(answerDTO.getQuestionID()).isPresent() && answerDTO.getIsCorrect().equals(true)) {
                return new ResponseEntity<String>(MessageResource.ONLY_ONE_CORRECT_ANSWER_IN_THIS_QUESTION, HttpStatus.ALREADY_REPORTED);
            }
            else {
                return optionalAnswer.map(answer -> {
                    answer.setContent(answerDTO.getContent());
                    answer.setIsCorrect(answerDTO.getIsCorrect());
                    answer.setIsDeleted(answerDTO.getIsDeleted());
                    answer.setQuestionID(answerDTO.getQuestionID());
                    answerRepository.save(answer);
                    return new ResponseEntity<AnswerDTO>(new AnswerDTO(
//                            answer.getAnswerID(),
                            answer.getContent(),
                            answer.getIsCorrect(),
                            answer.getIsDeleted(),
                            answer.getQuestionID()
                    ), HttpStatus.OK);
                }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            }
        } else {
            return new ResponseEntity<String>(MessageResource.QUESTION_NOT_CREATED_YET + " or " + MessageResource.ANSWER_NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }
}
