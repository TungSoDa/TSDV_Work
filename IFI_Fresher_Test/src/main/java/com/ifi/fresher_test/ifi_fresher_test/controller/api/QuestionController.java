package com.ifi.fresher_test.ifi_fresher_test.controller.api;

import com.ifi.fresher_test.ifi_fresher_test.dto.AnswerDTO;
import com.ifi.fresher_test.ifi_fresher_test.dto.QuestionDTO;
import com.ifi.fresher_test.ifi_fresher_test.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public List<QuestionDTO> findAll() {
        return questionService.findAll();
    }

    @GetMapping("/deletedList")
    public List<QuestionDTO> getDeletedQuestionList() {
        return questionService.getDeletedQuestionList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findQuestionByID(@PathVariable Integer id) {
        return questionService.findQuestionByID(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestBody QuestionDTO questionDTO) {
        return questionService.addQuestion(questionDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAnswer(@PathVariable Integer id, @RequestBody QuestionDTO questionDTO) {
        return questionService.updateQuestion(id, questionDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Integer id) {
        return questionService.deleteQuestion(id);
    }
}
