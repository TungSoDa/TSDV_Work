package com.ifi.fresher_test.ifi_fresher_test.service;

import com.ifi.fresher_test.ifi_fresher_test.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {
    ExamRepository examRepository;

    @Autowired
    public void setExamRepository(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }
}
