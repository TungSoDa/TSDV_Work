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
                            questionList.get(i).getQuestionID(),
                            questionList.get(i).getContent(),
                            questionList.get(i).getImage(),
                            questionList.get(i).getTopic(),
                            questionList.get(i).getIsDeleted(),
                            questionList.get(i).getContributorID(),
                            answerService.findListAnswerByQuestionID(questionList.get(i).getQuestionID())
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

    public ResponseEntity<?> addQuestion(QuestionDTO questionDTO) {
        if (questionRepository.findQuestionByContentAndTopic(questionDTO.getContent(), questionDTO.getTopic()).isPresent()) {
            return new ResponseEntity<String>(MessageResource.QUESTION_CONTENT_WITH_TOPIC + " " + questionDTO.getTopic() + " " + MessageResource.ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
        }
        else {
            Question question = QuestionMapper.dtoToEntity(questionDTO);
            questionRepository.save(question);
            return new ResponseEntity<QuestionDTO>(
                    new QuestionDTO(
                            question.getQuestionID(),
                            question.getContent(),
                            question.getImage(),
                            question.getTopic(),
                            question.getIsDeleted(),
                            question.getContributorID()
                    ), HttpStatus.CREATED
            );
        }
    }
}
