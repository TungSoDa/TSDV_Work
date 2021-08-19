package com.ifi.fresher_test.ifi_fresher_test.controller;

import com.ifi.fresher_test.ifi_fresher_test.dto.ExamDTO;
import com.ifi.fresher_test.ifi_fresher_test.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
@CrossOrigin
public class ExamController {
    ExamService examService;

    @Autowired
    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/all")
    public List<ExamDTO> findAll() {
        return examService.findAll();
    }

    @GetMapping("/deletedList")
    public List<ExamDTO> getDeletedExamList() {
        return examService.getDeletedExamList();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findExamByID(@PathVariable Integer id) {
        return examService.findExamByID(id);
    }

    @GetMapping("/topic/{topic}")
    public ResponseEntity<?> findExamByTopic(@PathVariable String topic) {
        return examService.findExamByTopic(topic);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addExam(@RequestBody ExamDTO examDTO) {
        return examService.addExam(examDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateExam(@PathVariable Integer id, @RequestBody ExamDTO examDTO) {
        return examService.updateExam(id, examDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExam(@PathVariable Integer id) {
        return examService.deleteExam(id);
    }
}
