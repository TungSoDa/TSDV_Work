package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.repository.ExamResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamResultService {
    ExamResultRepository examResultRepository;

    @Autowired
    public void setExamResultRepository(ExamResultRepository examResultRepository) {
        this.examResultRepository = examResultRepository;
    }
}
