package com.ifi.fresher_test.ifi_fresher_test.repository;

import com.ifi.fresher_test.ifi_fresher_test.model.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Integer> {
    Optional<List<ExamResult>> findAllByIsDeletedFalse();

    Optional<ExamResult> findExamResultByExamResultIDAndIsDeletedFalse(Integer id);

    Optional<ExamResult> findExamResultByExamIDAndContestantUsernameAndIsDeletedFalse(Integer examID, String contestantUsername);
}
