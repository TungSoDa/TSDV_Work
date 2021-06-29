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

    public List<QuestionDTO> findAll() {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questionList = questionRepository.findAll();
        for (int i = 0; i < questionList.size(); i++) {
            questionDTOList.add(
                    new QuestionDTO(
                            questionList.get(i).getQuestionId(),
                            questionList.get(i).getContent(),
                            questionList.get(i).getImage(),
                            questionList.get(i).getTopic(),
                            questionList.get(i).getIsDeleted(),
                            questionList.get(i).getContributorID(),
                            answerService.findListAnswerByQuestionID(questionList.get(i).getQuestionId())
                    )
            );
        }
        return questionDTOList;
    }

    public ResponseEntity<?> findQuestionByID(Integer id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        List<AnswerDTO> answerList = answerService.findListAnswerByQuestionID(id);
        if (optionalQuestion.isPresent()) {
            return optionalQuestion.map(question -> new ResponseEntity<>(
                    QuestionMapper.entityToDTO(question, answerList), HttpStatus.OK)
            ).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.QUESTION + " " + id + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }

    public QuestionDTO finQuestionDTOByID(Integer id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        List<AnswerDTO> answerList = answerService.findListAnswerByQuestionID(id);
        return QuestionMapper.entityToDTO(optionalQuestion.get(), answerList);
    }

    public List<QuestionDTO> findQuestionByTopic(String topic) {
        Optional<List<Question>> optionalQuestion = questionRepository.findQuestionByTopic(topic);
        return QuestionMapper.arrayEntityToDTO(optionalQuestion.get());
    }

    public ResponseEntity<?> addQuestion(QuestionDTO questionDTO) {
        if (questionRepository.findQuestionByContentAndTopic(questionDTO.getContent(), questionDTO.getTopic()).isPresent()) {
            return new ResponseEntity<String>(MessageResource.THIS_QUESTION_CONTENT_WITH_TOPIC + " " + questionDTO.getTopic() + " " + MessageResource.ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
        } else if (questionRepository.findQuestionByContentAndTopic(questionDTO.getContent(), questionDTO.getTopic()).get().getContent().toLowerCase().contains(questionDTO.getContent().toLowerCase()) ||
                questionDTO.getContent().toLowerCase().contains(questionRepository.findQuestionByContentAndTopic(questionDTO.getContent(), questionDTO.getTopic()).get().getContent().toLowerCase())
        ) {
            return new ResponseEntity<String>(MessageResource.THIS_QUESTION_CONTENT_WITH_TOPIC + " " + questionDTO.getTopic() + "  " + MessageResource.MAY_BE_THE_SAME_CONTENT_AS_EXISTING_QUESTION, HttpStatus.ALREADY_REPORTED);
        } else {
            Question question = QuestionMapper.dtoToEntity(questionDTO);
            questionRepository.save(question);
            return new ResponseEntity<QuestionDTO>(
                    new QuestionDTO(
                            question.getQuestionId(),
                            question.getContent(),
                            question.getImage(),
                            question.getTopic(),
                            question.getIsDeleted(),
                            question.getContributorID()
                    ), HttpStatus.CREATED
            );
        }
    }

    public ResponseEntity<?> updateQuestion(Integer id, QuestionDTO questionDTO) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (!optionalQuestion.isPresent()) {
            return new ResponseEntity<String>(MessageResource.QUESTION + " " + id + " " + MessageResource.NOT_CREATED_YET, HttpStatus.ALREADY_REPORTED);
        } else if (questionRepository.findQuestionByContentAndTopic(questionDTO.getContent(), questionDTO.getTopic()).isPresent()) {
            return new ResponseEntity<String>(MessageResource.THIS_QUESTION_CONTENT_WITH_TOPIC + " " + questionDTO.getTopic() + " " + MessageResource.ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
        } else if (questionRepository.findById(id).get().getContent().toLowerCase().contains(questionDTO.getContent().toLowerCase()) ||
                questionDTO.getContent().toLowerCase().contains(questionRepository.findById(id).get().getContent().toLowerCase())
        ) {
            return new ResponseEntity<String>(MessageResource.THIS_QUESTION_CONTENT_WITH_TOPIC + " " + questionDTO.getTopic() + "  " + MessageResource.MAY_BE_THE_SAME_CONTENT_AS_EXISTING_QUESTION, HttpStatus.ALREADY_REPORTED);
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
                        question.getIsDeleted(),
                        question.getContributorID()
                ), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }

    public ResponseEntity<?> deleteQuestion(Integer id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
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
                        question.getContributorID()
                ), HttpStatus.OK);
            }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<String>(MessageResource.QUESTION + " " + id + " " + MessageResource.NOT_CREATED_YET, HttpStatus.NOT_FOUND);
        }
    }
}
