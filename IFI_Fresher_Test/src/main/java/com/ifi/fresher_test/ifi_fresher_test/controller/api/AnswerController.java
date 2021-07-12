package com.ifi.fresher_test.ifi_fresher_test.controller.api;

import com.ifi.fresher_test.ifi_fresher_test.dto.AnswerDTO;

import com.ifi.fresher_test.ifi_fresher_test.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/answer")
public class AnswerController {
    AnswerService answerService;

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/all")
    public List<AnswerDTO> findAll() {
        return answerService.findAllByIsDeletedFalse();
    }

    @GetMapping("/deletedList")
    public List<AnswerDTO> getDeletedAnswerList() {
        return answerService.getDeletedAnswerList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAnswerByID(@PathVariable Integer id) {
        return answerService.findAnswerByID(id);
    }

    @GetMapping("/question/{questionID}")
    public ResponseEntity<?> findAnswerByQuestionID(@PathVariable Integer questionID) {
        return answerService.findAnswerByQuestionID(questionID);
    }

    @GetMapping("/correct/{questionID}")
    public ResponseEntity<?> getCorrectAnswerOfQuestion(@PathVariable Integer questionID) {
        return answerService.getCorrectAnswerOfQuestion(questionID);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAnswer(@RequestBody AnswerDTO answerDTO) {
        return answerService.addAnswer(answerDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAnswer(@PathVariable Integer id, @RequestBody AnswerDTO answerDTO) {
        return answerService.updateAnswer(id, answerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Integer id) {
        return answerService.deleteAnswer(id);
    }
}
