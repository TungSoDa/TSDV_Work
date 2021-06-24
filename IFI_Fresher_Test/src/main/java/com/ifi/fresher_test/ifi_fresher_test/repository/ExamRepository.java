package com.ifi.fresher_test.ifi_fresher_test.repository;

import com.ifi.fresher_test.ifi_fresher_test.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {

}
