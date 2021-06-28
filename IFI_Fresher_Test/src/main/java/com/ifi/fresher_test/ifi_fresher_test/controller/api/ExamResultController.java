package com.ifi.fresher_test.ifi_fresher_test.controller.api;

import com.ifi.fresher_test.ifi_fresher_test.service.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/examResult")
public class ExamResultController {
    ExamResultService examResultService;

    @Autowired
    public void setExamResultService(ExamResultService examResultService) {
        this.examResultService = examResultService;
    }
}
