package com.ifi.fresher_test.ifi_fresher_test.controller;

import com.ifi.fresher_test.ifi_fresher_test.dto.QuestionDTO;
import com.ifi.fresher_test.ifi_fresher_test.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@CrossOrigin
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

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findQuestionByID(@PathVariable Integer id) {
        return questionService.findQuestionByID(id);
    }

    @GetMapping("/topic/{topic}")
    public ResponseEntity<?> findQuestionByTopic(@PathVariable String topic) {
        return questionService.findQuestionDTOByTopic(topic);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestBody QuestionDTO questionDTO) {
        return questionService.addQuestion(questionDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable Integer id, @RequestBody QuestionDTO questionDTO) {
        return questionService.updateQuestion(id, questionDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Integer id) {
        return questionService.deleteQuestion(id);
    }

    @PutMapping("/undelete/{id}")
    public ResponseEntity<?> undeleteQuestion(@PathVariable Integer id) {
        return questionService.undeleteQuestion(id);
    }
}
