package com.ifi.fresher_test.ifi_fresher_test.controller.api;

import com.ifi.fresher_test.ifi_fresher_test.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam")
public class ExamController {
    ExamService examService;

    @Autowired
    public void setExamService(ExamService examService) {
        this.examService = examService;
    }
}
