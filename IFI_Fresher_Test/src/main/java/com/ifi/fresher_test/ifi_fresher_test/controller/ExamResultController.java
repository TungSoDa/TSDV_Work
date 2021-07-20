package com.ifi.fresher_test.ifi_fresher_test.controller;

import com.ifi.fresher_test.ifi_fresher_test.dto.ExamResultDTO;
import com.ifi.fresher_test.ifi_fresher_test.service.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examResult")
@CrossOrigin
public class ExamResultController {
    ExamResultService examResultService;

    @Autowired
    public void setExamResultService(ExamResultService examResultService) {
        this.examResultService = examResultService;
    }

    @GetMapping("/all")
    public List<ExamResultDTO> findAll() {
        return examResultService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findExamByID(@PathVariable Integer id) {
        return examResultService.findExamResultByID(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addExam(@RequestBody ExamResultDTO examResultDTO) {
        return examResultService.addExamResult(examResultDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExam(@PathVariable Integer id) {
        return examResultService.deleteExamResult(id);
    }
}
