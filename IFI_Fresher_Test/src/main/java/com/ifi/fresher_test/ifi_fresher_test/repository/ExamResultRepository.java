package com.ifi.fresher_test.ifi_fresher_test.repository;

import com.ifi.fresher_test.ifi_fresher_test.model.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Integer> {
}
